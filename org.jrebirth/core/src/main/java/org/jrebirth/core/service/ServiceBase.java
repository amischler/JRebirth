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
package org.jrebirth.core.service;

import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import org.jrebirth.core.concurrent.JRebirth;
import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.facade.JRebirthEventType;
import org.jrebirth.core.link.AbstractWaveReady;
import org.jrebirth.core.util.ClassUtility;
import org.jrebirth.core.wave.Wave;
import org.jrebirth.core.wave.WaveData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * The class <strong>ServiceBase</strong>.
 *
 * Base implementation of the service.
 *
 * @author Sébastien Bordes
 */
public class ServiceBase<V> extends AbstractWaveReady<Service> implements Service {

    /** The class logger. */
    static final Logger LOGGER = LoggerFactory.getLogger(ServiceBase.class);

    private final ObjectProperty<Throwable> exception = new SimpleObjectProperty<Throwable>();

    private final StringProperty message = new SimpleStringProperty();

    private final DoubleProperty progress = new SimpleDoubleProperty();

    private final BooleanProperty running = new SimpleBooleanProperty();

    private final ObjectProperty<Worker.State> state = new SimpleObjectProperty<Worker.State>();

    private final StringProperty title = new SimpleStringProperty();

    private final DoubleProperty totalWork = new SimpleDoubleProperty();

    private final DoubleProperty workDone = new SimpleDoubleProperty();

    private final ObjectProperty<V> value = new SimpleObjectProperty<V>();

    private ServiceTask<V> task;

    /**
     * {@inheritDoc}
     */
    @Override
    public void ready() throws CoreException {
        // Initialize Service with private method

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void processWave(final Wave wave) {
        // Must be overridden to manage action handling by service
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void finalize() throws Throwable {
        getLocalFacade().getGlobalFacade().trackEvent(JRebirthEventType.DESTROY_SERVICE, null, this.getClass());
        super.finalize();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void returnData(final Wave sourceWave) {

        try {
            // Build parameter list of the searched method
            final List<Object> parameterValues = new ArrayList<>();
            for (final WaveData<?> wd : sourceWave.getWaveItems()) {
                parameterValues.add(wd.getValue());
            }
            // Add the current wave to process
            // parameterValues.add(wave);

            if (sourceWave.getWaveType() == null) {
                LOGGER.error("Wave processed without any wave type for service  " + this.getClass().getSimpleName());
            }

            // Search the wave handler method
            final Method method = ClassUtility.getMethodByName(this.getClass(), ClassUtility.underscoreToCamelCase(sourceWave.getWaveType().toString()));
            if (method != null) {

                // final Class<T> returnClass = (Class<T>) method.getReturnType();

                runTask(sourceWave, method, parameterValues.toArray());

            }
        } catch (final NoSuchMethodException e) {
            // If no method was found, call the default method
            processWave(sourceWave);
        }

    }

    /**
     * Run the wave type method.
     *
     * @param sourceWave the source wave
     * @param parameterValues values to pass to the method
     * @param method method to call
     *
     */
    private void runTask(final Wave sourceWave, final Method method, final Object[] parameterValues) {

        final AbstractWaveReady<Service> localService = this;
        task = new ServiceTask(localService, method, parameterValues, sourceWave);
        // bind Worker properties into JAT
        JRebirth.runIntoJAT(new Runnable() {
            @Override
            public void run() {
                exception.bind(task.exceptionProperty());
                message.bind(task.messageProperty());
                progress.bind(task.progressProperty());
                running.bind(task.runningProperty());
                state.bind(task.stateProperty());
                title.bind(task.titleProperty());
                totalWork.bind(task.totalWorkProperty());
                workDone.bind(task.workDoneProperty());
                value.bind(task.valueProperty());
                // listener used to unbind worker properties once the Task is in an ending state
                task.stateProperty().addListener(new ChangeListener<Worker.State>() {
                    @Override
                    public void changed(ObservableValue<? extends Worker.State> observableValue, Worker.State state1, Worker.State state2) {
                        // check if we are in an ending state
                        if (state2 == Worker.State.FAILED || state2 == Worker.State.SUCCEEDED || state2 == Worker.State.CANCELLED) {
                            exception.unbind();
                            message.unbind();
                            progress.unbind();
                            running.unbind();
                            state.unbind();
                            title.unbind();
                            totalWork.unbind();
                            workDone.unbind();
                            value.unbind();
                            task.stateProperty().removeListener(this);
                            task = null;
                        }
                    }
                });
            }
        });
        // Call the task into the JRebirth Thread Pool
        JRebirth.runIntoJTP(task);
    }

    public Throwable getException() {
        return exception.get();
    }

    public ReadOnlyObjectProperty<Throwable> exceptionProperty() {
        return exception;
    }

    public String getMessage() {
        return message.get();
    }

    public ReadOnlyStringProperty messageProperty() {
        return message;
    }

    public double getProgress() {
        return progress.get();
    }

    public ReadOnlyDoubleProperty progressProperty() {
        return progress;
    }

    public boolean isRunning() {
        return running.get();
    }

    public ReadOnlyBooleanProperty runningProperty() {
        return running;
    }

    public Worker.State getState() {
        return state.get();
    }

    public ReadOnlyObjectProperty<Worker.State> stateProperty() {
        return state;
    }

    public String getTitle() {
        return title.get();
    }

    public ReadOnlyStringProperty titleProperty() {
        return title;
    }

    public double getTotalWork() {
        return totalWork.get();
    }

    public ReadOnlyDoubleProperty totalWorkProperty() {
        return totalWork;
    }

    public double getWorkDone() {
        return workDone.get();
    }

    public ReadOnlyDoubleProperty workDoneProperty() {
        return workDone;
    }

    public V getValue() {
        return value.get();
    }

    public ReadOnlyObjectProperty<V> valueProperty() {
        return value;
    }

    @Override
    public boolean cancel() {
        return task.cancel();
    }

    protected void updateMessage(java.lang.String message) {
        task.updateMessage(message);
    }

    protected void updateProgress(double workDone, double max) {
        task.updateProgress(workDone, max);
    }

    protected void updateProgress(long workDone, long max) {
        task.updateProgress(workDone, max);
    }

    protected void updateTitle(java.lang.String title) {
        task.updateTitle(title);
    }

}
