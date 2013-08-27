/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2013
 * Contact : sebastien.bordes@jrebirth.org
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jrebirth.showcase.undoredo.ui;

import javafx.scene.input.MouseEvent;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.ui.DefaultController;
import org.jrebirth.core.wave.WaveData;
import org.jrebirth.showcase.undoredo.beans.ShapeType;
import org.jrebirth.showcase.undoredo.beans.UndoAppWaves;
import org.jrebirth.showcase.undoredo.command.CreateShapeCommand;
import org.jrebirth.undoredo.command.RedoCommand;
import org.jrebirth.undoredo.command.UndoCommand;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <strong>UndoController</strong>.
 * 
 * @author Sébastien Bordes
 */
public final class UndoController extends DefaultController<UndoModel, UndoView> {

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(UndoController.class);

    /**
     * Default Constructor.
     * 
     * @param view the view to control
     * 
     * @throws CoreException if an error occurred while creating event handlers
     */
    public UndoController(final UndoView view) throws CoreException {
        super(view);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initEventAdapters() throws CoreException {

        // Manage Ui Command Button
        linkCommand(getView().getUndoButton(), MouseEvent.MOUSE_CLICKED, UndoCommand.class, UndoModel.stackName);
        linkCommand(getView().getRedoButton(), MouseEvent.MOUSE_CLICKED, RedoCommand.class, UndoModel.stackName);

        linkCommand(getView().getAddCircleButton(), MouseEvent.MOUSE_CLICKED, CreateShapeCommand.class, WaveData.build(UndoAppWaves.SHAPE_TYPE, ShapeType.Circle), UndoModel.stackName);
        linkCommand(getView().getAddSquareButton(), MouseEvent.MOUSE_CLICKED, CreateShapeCommand.class, WaveData.build(UndoAppWaves.SHAPE_TYPE, ShapeType.Square), UndoModel.stackName);
        linkCommand(getView().getAddRectangleButton(), MouseEvent.MOUSE_CLICKED, CreateShapeCommand.class, WaveData.build(UndoAppWaves.SHAPE_TYPE, ShapeType.Rectangle), UndoModel.stackName);

    }

}