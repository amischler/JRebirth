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
package org.jrebirth.analyzer.ui.properties;

import org.jrebirth.analyzer.ui.editor.EditorWaves;
import org.jrebirth.core.facade.JRebirthEvent;
import org.jrebirth.core.ui.DefaultModel;
import org.jrebirth.core.wave.Wave;

/**
 * The class <strong>PropertiesModel</strong>.
 * 
 * @author Sébastien Bordes
 */
public final class PropertiesModel extends DefaultModel<PropertiesModel, PropertiesView> {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initModel() {
        listen(EditorWaves.DO_SELECT_EVENT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initInnerModels() {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void processWave(final Wave wave) {
        if (EditorWaves.DO_SELECT_EVENT == wave.getWaveType()) {

            final JRebirthEvent event = wave.get(PropertiesWaves.EVENT_OBJECT);

            getView().getNodeName().setText(event.getTarget().getSimpleName());
        }
    }

    // @OnWave(EditorWaves.DO_SELECT_EVENT.getAction())
    // public void totot() {
    //
    // }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void showView() {
        // Nothing to do yet

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void hideView() {
        // Nothing to do yet

    }
}
