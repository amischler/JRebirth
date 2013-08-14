package org.jrebirth.core.application.guice;

import com.google.inject.AbstractModule;

/**
 * User: Antoine Mischler <antoine@dooapp.com>
 * Date: 14/08/13
 * Time: 17:52
 */
public class Module2 extends AbstractModule {
    @Override
    protected void configure() {
        bind(IMyModel.class).to(MyModel2.class);
    }
}
