package org.jrebirth.core.wave;

/**
 * The class <strong>WaveItems</strong>.
 * 
 * @author Sébastien Bordes
 */
public enum WaveItems implements IWaveItem {

    /** . */
    resourceName(new WaveItem<String>() {
    }),

    /** . */
    fieldName(new WaveItem<String>() {
    }),

    /** . */
    name(new WaveItem<String>() {
    });

    /**
     * Private constructor.
     * 
     * @param wi the wave item to initialize
     */
    private WaveItems(final WaveItem<?> wi) {
        WaveItem.init(this, wi);
    }

}
