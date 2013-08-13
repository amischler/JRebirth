package org.jrebirth.fxform;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.exception.CoreRuntimeException;
import org.jrebirth.core.ui.object.DefaultObjectModel;
import org.jrebirth.core.util.ClassUtility;

/**
 * User: Antoine Mischler <antoine@dooapp.com>
 * Date: 13/08/13
 * Time: 11:17
 */
public class AbstractFormModel<T> extends DefaultObjectModel<AbstractFormModel<T>, AbstractFormView<T>, T> {

    @Override
    protected void buildView() {
        this.view = new AbstractFormView<>(this);
    }

    @Override
    protected void buildObject() {
        // Build the current view by reflection
        try {
            this.object = (T) ClassUtility.buildGenericType(this.getClass(), 0);
        } catch (final CoreException e) {
            throw new CoreRuntimeException("Failure while building the bindable object for model " + getClass(), e);
        }
    }

    @Override
    protected void bind() {
        super.bind();
        getView().getRootNode().setSource(getObject());
    }
}
