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
package org.jrebirth.core.wave.checker;

import org.jrebirth.core.util.ObjectUtility;
import org.jrebirth.core.wave.Wave;
import org.jrebirth.core.wave.WaveData;
import org.jrebirth.core.wave.WaveItem;

/**
 * The class <strong>DefaultWaveChecker</strong> will check if the provided object type is equals to the wave data object defined.
 * 
 * @param <I> the generic type of the object stored into the WaveData
 * 
 * @author Sébastien Bordes
 */
public class DefaultWaveChecker<I extends Object> extends AbstractWaveChecker<I, I> {

    /**
     * Default COnstructor to call the super one.
     * 
     * @param waveItem the wave item
     * @param matchingValue the matching value
     */
    public DefaultWaveChecker(final WaveItem<I> waveItem, final I matchingValue) {
        super(waveItem, matchingValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean call(final Wave wave) {

        final WaveData<I> waveData = wave.getData(getWaveItem());
        final I currentValue = waveData == null ? null : waveData.getValue();

        return ObjectUtility.equalsOrBothNull(currentValue, getMatchingValue());
    }

}
