package org.jrebirth.core.application.guice;

import org.jrebirth.core.ui.DefaultModel;

/**
 * User: Antoine Mischler <antoine@dooapp.com>
 * Date: 14/08/13
 * Time: 17:50
 */
public class MyModel1 extends DefaultModel<MyModel1, MyView1> implements IMyModel {

    @Override
    protected void bind() {
        getView().getRootNode().setText("Model 1");
    }

}
