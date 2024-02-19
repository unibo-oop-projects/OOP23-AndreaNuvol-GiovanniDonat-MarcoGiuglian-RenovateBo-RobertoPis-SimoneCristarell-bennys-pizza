package it.unibo.view;

import it.unibo.controller.impl.ControllerImpl;
import java.lang.reflect.InvocationTargetException;

    public class App {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        new GUIHallImpl(new ControllerImpl());
    }
}
