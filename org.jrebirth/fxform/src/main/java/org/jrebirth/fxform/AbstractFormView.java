package org.jrebirth.fxform;

import com.dooapp.fxform.FXForm;
import org.jrebirth.core.ui.DefaultView;

/**
 * User: Antoine Mischler <antoine@dooapp.com>
 * Date: 13/08/13
 * Time: 11:21
 */
public class AbstractFormView<T> extends DefaultView<AbstractFormModel<T>, FXForm<T>, AbstractFormController<T>> {

    /**
     * Default Constructor.
     *
     * @param model the model of the view
     */
    public AbstractFormView(AbstractFormModel model) {
        super(model);
    }

}
