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
package org.jrebirth.core.resource.i18n;

import java.text.MessageFormat;

import org.jrebirth.core.exception.CoreRuntimeException;
import org.jrebirth.core.log.JRebirthMarkers;
import org.jrebirth.core.resource.ResourceBuilders;
import org.jrebirth.core.resource.provided.JRebirthParameters;

import org.slf4j.Marker;

/**
 * The class <strong>MessageItemBase</strong> is used to build i18n Message.
 * 
 * @author Sébastien Bordes
 */
public final class MessageItemBase implements MessageItem {

    /** The unique identifier of the color item. */
    private int uid;

    /**
     * Default Constructor.
     * 
     * You should not use this constructor, see #{@link org.jrebirth.core.resource.Resources}
     * 
     * @param messageParams the params for i18n message
     */
    public MessageItemBase(final MessageParams messageParams) {
        builder().storeParams(this, messageParams);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String get() {
        return builder().get(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String get(final Object... stringParameters) {
        String res = get();
        if (stringParameters.length > 0) {
            try {
                res = MessageFormat.format(res, stringParameters);
            } catch (final IllegalArgumentException e) {
                res = "<!!" + builder().getParam(this).toString() + "!!>";
                if (JRebirthParameters.DEVELOPER_MODE.get()) {
                    throw new CoreRuntimeException("Invalid key : " + res);
                }
            }
        }
        return res;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void define(final String forcedValue) {
        // The default programmatic value (stored into ObjectParameter) is not updated but overridden into the local map
        builder().define(this, forcedValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MessageBuilder builder() {
        return ResourceBuilders.MESSAGE_BUILDER;
    }

    /**
     * Gets the uid.
     * 
     * @return Returns the uid.
     */
    public int getUid() {
        return this.uid;
    }

    /**
     * Sets the uid.
     * 
     * @param uid The uid to set.
     */
    public void setUid(final int uid) {
        this.uid = uid;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void persist() {
        throw new CoreRuntimeException("Not Implemented yet");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Marker getMarker() {
        return builder().getParam(this) instanceof LogMessageParams ? ((LogMessageParams) builder().getParam(this)).marker() : JRebirthMarkers.EMPTY;
    }

}
