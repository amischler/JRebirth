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
package org.jrebirth.core.resource.provided;

import static org.jrebirth.core.resource.Resources.create;

import org.jrebirth.core.facade.DefaultComponentFactory;
import org.jrebirth.core.link.DefaultUnprocessedWaveHandler;
import org.jrebirth.core.resource.color.WebColor;
import org.jrebirth.core.resource.image.ImageExtension;
import org.jrebirth.core.resource.image.LocalImage;
import org.jrebirth.core.resource.parameter.ParameterItem;
import org.jrebirth.core.resource.style.StyleSheet;

/**
 * The class <strong>JRebirthParameters</strong>.
 * 
 * Parameters used by JRebirth Application Framework itself
 * 
 * @author Sébastien Bordes
 */
public interface JRebirthParameters {

    /** The name of the AUTO_REEFRESH parameter which is quite special because it modify how other parameters will be processed. */
    String AUTO_REFRESH_NAME = "autoRefreshResource";

    /**************************************************************************************/
    /** __________________________Application Core Parameters.___________________________ */
    /**************************************************************************************/

    /** Allow to auto refresh resource when resource params is updated. */
    ParameterItem<Boolean> AUTO_REFRESH_RESOURCE = create(AUTO_REFRESH_NAME, false);

    /** Developer provides more information when dynamic API is broken (Wave Contract). */
    ParameterItem<Boolean> DEVELOPER_MODE = create("developerMode", false);

    /** The handler used while running in developer mode to manage unprocessed wave. */
    ParameterItem<Boolean> FOLLOW_UP_SERVICE_TASKS = create("followUpServiceTasks", false);

    /**
     * When true log code will be resolved according to Message_rb properties files. <br />
     * Disable it to improve performances, log could be translated later.
     */
    ParameterItem<Boolean> LOG_RESOLUTION = create("logResolution", true);

    /** The handler used while running in developer mode to manage unprocessed wave. */
    ParameterItem<Class> UNPROCESSED_WAVE_HANDLER = create("unprocessedWaveHandler", (Class) DefaultUnprocessedWaveHandler.class);

    /** The factory used to create components. */
    ParameterItem<Class> COMPONENT_FACTORY = create("componentFactory", (Class) DefaultComponentFactory.class);

    /** Fir Close Retry Delay in milliseconds, time to wait when application try to close the first time. */
    ParameterItem<Integer> CLOSE_RETRY_DELAY_FIRST = create("closeRetryDelayFirst", 4000);

    /** Close Retry Delay in milliseconds, time to wait when application try to close all other time. */
    ParameterItem<Integer> CLOSE_RETRY_DELAY_OTHER = create("closeRetryDelayOther", 1000);

    /** Pool size of JRebirth Thread Pool. */
    ParameterItem<Integer> THREAD_POOL_SIZE = create("threadPoolSize", 4);

    /** Fonts default folder, Multiple folder can be managed by separating them with a comma ','. */
    ParameterItem<String> FONT_FOLDER = create("fontsFolder", "fonts");

    /** Images default folder, Multiple folder can be managed by separating them with a comma ','. */
    ParameterItem<String> IMAGE_FOLDER = create("imagesFolder", "images");

    /** Styles default folder, Multiple folder can be managed by separating them with a comma ','. */
    ParameterItem<String> STYLE_FOLDER = create("stylesFolder", "styles");

    /**
     * The <code>DEFAULT_CSS</code> field is used to parameterize the name of the default style sheet.
     */
    ParameterItem<StyleSheet> DEFAULT_CSS = create("defaultStyleSheet", new StyleSheet("default"));

    /**
     * The <code>TRUE_TYPE_FONT_EXTENSION</code> field is used to define the font file extension .
     */
    ParameterItem<String> TRUE_TYPE_FONT_EXTENSION = create("trueTypeFontExtension", ".ttf");

    /**
     * The <code>WAVE_HANDLER_PREFIX</code> field is used to add a prefix to custom wave handler method of JRebirth components. They will be named like this : doMyAction(Wave) after being renamed in
     * camel case.
     */
    ParameterItem<String> WAVE_HANDLER_PREFIX = create("waveHandlerPrefix", "DO_");

    /**
     * The <code>NOT_AVAILABLE_IMAGE</code> field is used to define the image to use when an image is missing.
     */
    ParameterItem<LocalImage> NOT_AVAILABLE_IMAGE = create("notAvailableImage", new LocalImage("NotAvailableImage", ImageExtension.PNG));

    /**
     * The <code>APPLICATION_NAME</code> field is used to define title of the application displayed by stage window.<br />
     * The default value contains a <b>{}</b> that will be replaced by application class simple name.
     */
    ParameterItem<String> APPLICATION_NAME = create("applicationName", "{} powered by JRebirth");

    /**
     * The <code>APPLICATION_VERSION</code> field is used to define the application version.
     */
    ParameterItem<String> APPLICATION_VERSION = create("applicationVersion", "0.0.0");

    /**************************************************************************************/
    /** _________________________Application Stage Parameters.___________________________ */
    /**************************************************************************************/

    /** The application scene width. */
    ParameterItem<Integer> APPLICATION_SCENE_WIDTH = create("applicationSceneWidth", 800);

    /** The application scene height. */
    ParameterItem<Integer> APPLICATION_SCENE_HEIGHT = create("applicationSceneHeight", 600);

    /** The application scene background color. */
    ParameterItem<WebColor> APPLICATION_SCENE_BG_COLOR = create("applicationSceneBgColor", new WebColor("000000", 0.0));
}
