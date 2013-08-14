package org.jrebirth.core.application.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.jrebirth.core.application.AbstractApplication;
import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.facade.FacadeReady;
import org.jrebirth.core.facade.Factory;
import org.jrebirth.core.resource.font.FontItem;
import org.jrebirth.core.ui.Model;
import org.jrebirth.core.wave.Wave;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

/**
 * User: Antoine Mischler <antoine@dooapp.com>
 * Date: 14/08/13
 * Time: 17:47
 */
public class GuiceApplication extends AbstractApplication<StackPane> {

    /**
     * The famous {@link org.slf4j.Logger}
     */
    private static final Logger logger = LoggerFactory.getLogger(GuiceApplication.class);

    @Override
    protected void customizeStage(Stage stage) {
        stage.setFullScreen(false);
    }

    @Override
    protected List<FontItem> getFontToPreload() {
        return Collections.emptyList();
    }

    @Override
    protected void customizeScene(Scene scene) {
    }

    @Override
    public Class<? extends Model> getFirstModelClass() {
        return IMyModel.class;
    }

    @Override
    public List<Wave> getPreBootWaveList() {
        return Collections.emptyList();
    }

    @Override
    public List<Wave> getPostBootWaveList() {
        return Collections.emptyList();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public Factory getFactory() {
        return new Factory() {

            private final Injector injector = Guice.createInjector(new Module1());

            @Override
            public <R extends FacadeReady> R call(Class<R> clazz) throws CoreException {
                return injector.getInstance(clazz);
            }
        };
    }
}
