package org.jrebirth.core.application.guice;

import javafx.scene.control.Button;
import org.jrebirth.core.ui.DefaultController;
import org.jrebirth.core.ui.DefaultView;

/**
 * User: Antoine Mischler <antoine@dooapp.com>
 * Date: 14/08/13
 * Time: 17:52
 */
public class MyView1 extends DefaultView<MyModel1, Button, DefaultController<MyModel1, MyView1>> {
    /**
     * Default Constructor.
     *
     * @param model the model of the view
     */
    public MyView1(MyModel1 model) {
        super(model);
    }

}
