package it.unibo.view;

import it.unibo.controller.impl.ControllerImpl;
import java.lang.reflect.InvocationTargetException;

/**
 * Class to start the program.
 */
public final class App {

    private App() { }
    /**
     * main method to start the program.
     * @param args
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws SecurityException
     */
    public static void main(final String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException,
     IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        new GUIHallImpl(new ControllerImpl());
    }
}
