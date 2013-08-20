package org.jrebirth.showcase.fxml.ui.hybrid;

import org.jrebirth.core.ui.fxml.DefaultFXMLModel;

/**
 * The class <strong>StandaloneModel</strong>.
 * 
 * @author Sébastien Bordes
 */
public class HybridModel extends DefaultFXMLModel<HybridModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getFXMLPath() {
        return "org/jrebirth/showcase/fxml/ui/standalone/Standalone.fxml";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getFXMLBundlePath() {
        return "org/jrebirth/showcase/fxml/ui/standalone/Standalone";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fxmlPreInitialize() {
        // if (getModelObject() != null) {
        // this.fxmlPath = getModelObject().toString();
        // }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initModel() {
        // Nothing to do yet

    }

}
