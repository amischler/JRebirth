/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2013
 * Contact : sebastien.bordes@jrebirth.org
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jrebirth.core.ui;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.Node;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.facade.JRebirthEventType;
import org.jrebirth.core.key.UniqueKey;
import org.jrebirth.core.link.AbstractWaveReady;
import org.jrebirth.core.wave.Wave;

/**
 * 
 * The interface <strong>AbstractBaseModel</strong>.
 * 
 * Base implementation of the model.
 * 
 * @author Sébastien Bordes
 * 
 * @param <M> the class type of the current model
 */
public abstract class AbstractBaseModel<M extends Model> extends AbstractWaveReady<Model> implements Model {

    /** The root model not null for inner model. */
    private Model rootModel;

    /** The map that store inner models loaded. */
    private final Map<InnerModels, Model> innerModelSingletonMap = new HashMap<>();

    /** The map that store inner models loaded. */
    private final Map<InnerModels, Map<UniqueKey, Model>> innerModelMultitonMap = new HashMap<>();

    /** Flag used to determine if a view has been already displayed, useful to manage first time animation. */
    private boolean viewDisplayed;

    /**
     * {@inheritDoc}
     */
    @Override
    public final void ready() throws CoreException {

        // Initialize the current model
        initInternalModel();

        // Initialize inner models (if any)
        initInternalInnerModels();

        // Model and InnerModels are OK, let's prepare the view
        if (getView() != null) {
            getView().prepare();
        }

        // Bind Object properties to view widget ones
        bindInternal();
    }

    /**
     * Initialize the model.
     * 
     * @throws CoreException if the creation of the view fails
     */
    protected abstract void initInternalModel() throws CoreException;

    /**
     * Initialize method to implement for adding custom processes.
     * 
     * This method is a hook to manage generic code before initializing current model.
     * 
     * You must implement the {@link #initModel()} method to setup your model.
     */
    protected abstract void initModel();

    /**
     * Initialize the included models.
     * 
     * This method is a hook to manage generic code before initializing inner models.
     * 
     * You must implement the {@link #initInnerModels()} method to setup your inner models.
     */
    protected final void initInternalInnerModels() {
        // Do generic stuff

        // Do custom stuff
        initInnerModels();
    }

    /**
     * Initialize method for inner models to implement for adding custom processes.
     */
    protected abstract void initInnerModels();

    /**
     * Bind current object to view's widget.
     * 
     * This method is a hook to manage generic code before binding model's object.
     * 
     * You must implement the {@link #bind()} method to add your bindings.
     */
    protected abstract void bindInternal();

    /**
     * Bind method to implement for adding custom bindings.
     */
    protected abstract void bind();

    /**
     * Show the view.<br />
     * In example : start the show transition
     * 
     * This method is a hook to manage generic code before initializing the view's node tree. It will call {@link #org.jrebirth.core.ui.View.start()} or {@link #org.jrebirth.core.ui.View.reload()}
     * method
     * 
     * You must implement the {@link #showView()} method to setup your view.
     */
    protected final void showInternalView() {

        // Call user code
        showView();

        // Sometimes view can be null
        if (getView() != null) {
            if (this.viewDisplayed) {
                // Relaod the view
                getView().reload();
            } else {
                // Start the view for the first time
                getView().start();
                this.viewDisplayed = true;
            }
        }

    }

    /**
     * Perform custom user action <b>before</b> showing the view.
     */
    protected abstract void showView();

    /**
     * Hide the view.<br />
     * In example : start the hide transition
     * 
     * Will call the {@link #org.jrebirth.core.ui.View.hide()} method
     */
    protected final void hideInternalView() {

        // Call user code
        hideView();

        // Sometimes view can be null
        if (getView() != null) {
            // hide the view
            getView().hide();
        }
    }

    /**
     * Perform custom action <b>before</b> hiding the view.
     */
    protected abstract void hideView();

    /**
     * {@inheritDoc}
     */
    @Override
    public Node getRootNode() {
        return getView().getRootNode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Model getRootModel() {
        return this.rootModel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRootModel(final Model rootModel) {
        this.rootModel = rootModel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Model getInnerModel(final InnerModels innerModel, final UniqueKey... innerModelKey) {

        // The model to return
        Model model;

        UniqueKey key = null; // TODO Check priority
        if (innerModelKey != null && innerModelKey.length == 1) {
            key = innerModelKey[0];
        } else if (innerModel.getKey() != null) {
            key = innerModel.getKey();
        }

        if (key == null) {
            // Check if the inner model is registered
            if (!this.innerModelSingletonMap.containsKey(innerModel)) {

                // retrieve and attache the inner model into the dedicated map
                this.innerModelSingletonMap.put(innerModel, getLocalFacade().retrieve(innerModel.getModelClass()));
                // Link the current root model
                this.innerModelSingletonMap.get(innerModel).setRootModel(this);
            }

            // Return the registered inner model
            model = this.innerModelSingletonMap.get(innerModel);

        } else {

            // For Multiton Components
            // Check if the MultitonKey map exists for this component class
            if (!this.innerModelMultitonMap.containsKey(innerModel)) {
                this.innerModelMultitonMap.put(innerModel, new HashMap<UniqueKey, Model>());
            }
            // Check if the class of the object is already stored into the
            // multitonKey map
            if (!this.innerModelMultitonMap.get(innerModel).containsKey(key)) {

                // Store the component into the multitonKey map
                this.innerModelMultitonMap.get(innerModel).put(key, getLocalFacade().retrieve(innerModel.getModelClass(), key));

                // Link the current root model
                this.innerModelMultitonMap.get(innerModel).get(key).setRootModel(this);
            }
            model = this.innerModelMultitonMap.get(innerModel).get(key);
        }
        return model;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected abstract void processWave(final Wave wave);

    /**
     * {@inheritDoc}
     */
    @Override
    protected void finalize() throws Throwable {
        getLocalFacade().getGlobalFacade().trackEvent(JRebirthEventType.DESTROY_MODEL, null, this.getClass());
        super.finalize();
    }

}
