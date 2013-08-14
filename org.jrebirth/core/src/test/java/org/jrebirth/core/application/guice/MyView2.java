package org.jrebirth.core.application.guice;

import javafx.scene.control.Button;
import org.jrebirth.core.ui.DefaultController;
import org.jrebirth.core.ui.DefaultView;

/**
 * User: Antoine Mischler <antoine@dooapp.com>
 * Date: 14/08/13
 * Time: 17:50
 */
public class MyView2 extends DefaultView<MyModel2, Button, DefaultController<MyModel2, MyView2>> {
    /**
     * Default Constructor.
     *
     * @param model the model of the view
     */
    public MyView2(MyModel2 model) {
        super(model);
    }

}
