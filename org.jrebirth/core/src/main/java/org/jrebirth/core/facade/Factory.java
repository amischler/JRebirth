package org.jrebirth.core.facade;

import org.jrebirth.core.exception.CoreException;

/**
 * The Factory class is used to define how components are instantiated<br>
 * <br>
 * Created at 14/08/13 11:46.<br>
 *
 * @author Bastien
 */
public interface Factory {

    /**
     * Create a new instance of the component of the given class.
     *
     * @param clazz the class of the component to instantiate
     * @return A new instance of the class
     * @throws CoreException when the instantiation failed
     */
    <R extends FacadeReady> R call(Class<R> clazz) throws CoreException;
}
