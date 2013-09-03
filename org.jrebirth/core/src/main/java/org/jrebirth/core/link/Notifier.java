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
package org.jrebirth.core.link;

import org.jrebirth.core.exception.JRebirthThreadException;
import org.jrebirth.core.facade.WaveReady;
import org.jrebirth.core.wave.Wave;
import org.jrebirth.core.wave.WaveChecker;
import org.jrebirth.core.wave.WaveType;

/**
 * The interface <strong>Notifier</strong>.
 * 
 * Use to propagate waves through application layers.
 * 
 * @author Sébastien Bordes
 */
public interface Notifier {

    /**
     * Send wave to all facade.
     * 
     * MUST BE CALLED into the JRebirthThread.
     * 
     * @param wave the object that information data
     * 
     * @throws JRebirthThreadException if called outside the JRebirthThread
     */
    void sendWave(Wave wave) throws JRebirthThreadException;

    /**
     * Start to listen a defined type of wave.
     * 
     * MUST BE CALLED into the JRebirthThread.
     * 
     * @param linkedObject an object that can process the content of a wave
     * @param waveTypes the type(s) of wave that interests the object (one or many)
     * 
     * @throws JRebirthThreadException if called outside the JRebirthThread
     */
    void listen(WaveReady linkedObject, WaveType... waveTypes) throws JRebirthThreadException;

    /**
     * Start to listen a defined type of wave.
     * 
     * The WaveChecker allow to route wave only to component that are concerned.
     * 
     * MUST BE CALLED into the JRebirthThread.
     * 
     * @param linkedObject an object that can process the content of a wave
     * @param waveChecker the wave checker to filter unwanted wave to be processed by other components
     * @param waveTypes the type(s) of wave that interests the object (one or many)
     * 
     * @throws JRebirthThreadException if called outside the JRebirthThread
     */
    void listen(WaveReady linkedObject, WaveChecker waveChecker, WaveType... waveTypes) throws JRebirthThreadException;

    /**
     * Stop to listen a defined type of wave.
     * 
     * MUST BE CALLED into the JRebirthThread.
     * 
     * @param linkedObject an object that can process the content of a wave
     * @param waveType the type of wave that doesn't interest the object anymore (one or many)
     * 
     * @throws JRebirthThreadException if called outside the JRebirthThread
     */
    void unlisten(WaveReady linkedObject, WaveType... waveType) throws JRebirthThreadException;

}
