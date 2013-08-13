package org.jrebirth.fxform;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.jrebirth.core.application.AbstractApplication;
import org.jrebirth.core.resource.font.FontItem;
import org.jrebirth.core.ui.Model;
import org.jrebirth.core.wave.Wave;

import java.util.List;

/**
 * User: Antoine Mischler <antoine@dooapp.com>
 * Date: 13/08/13
 * Time: 15:26
 */
public class FXFormTest extends AbstractApplication<StackPane> {

    /**
     * Application launcher.
     *
     * @param args the command line arguments
     */
    public static void main(final String... args) {
        Application.launch(FXFormTest.class, args);
    }

    @Override
    protected void customizeStage(Stage stage) {

    }

    @Override
    protected List<FontItem> getFontToPreload() {
        return null;
    }

    @Override
    protected void customizeScene(Scene scene) {

    }

    @Override
    public Class<? extends Model> getFirstModelClass() {
        return TestBeanFormModel.class;
    }

    @Override
    public List<Wave> getPreBootWaveList() {
        return null;
    }

    @Override
    public List<Wave> getPostBootWaveList() {
        return null;
    }
}
