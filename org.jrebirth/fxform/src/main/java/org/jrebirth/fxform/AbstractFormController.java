package org.jrebirth.fxform;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.ui.DefaultController;

/**
 * User: Antoine Mischler <antoine@dooapp.com>
 * Date: 13/08/13
 * Time: 11:19
 */
public class AbstractFormController<T> extends DefaultController<AbstractFormModel<T>, AbstractFormView<T>> {

    /**
     * Default Constructor.
     *
     * @param view the view controlled
     * @throws org.jrebirth.core.exception.CoreException
     *          if an error occurred while initialization
     */
    public AbstractFormController(AbstractFormView view) throws CoreException {
        super(view);
    }

}
