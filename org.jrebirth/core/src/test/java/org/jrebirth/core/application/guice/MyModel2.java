package org.jrebirth.core.application.guice;

import org.jrebirth.core.ui.DefaultModel;

/**
 * User: Antoine Mischler <antoine@dooapp.com>
 * Date: 14/08/13
 * Time: 17:50
 */
public class MyModel2 extends DefaultModel<MyModel2, MyView2> implements IMyModel {

    @Override
    protected void bind() {
        getView().getRootNode().setText("Model 2");
    }

}
