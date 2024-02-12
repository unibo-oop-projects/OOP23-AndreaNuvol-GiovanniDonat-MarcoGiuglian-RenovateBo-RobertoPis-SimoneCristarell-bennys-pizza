package it.unibo.model.impl;

import it.unibo.model.api.*;
import java.io.*;
import java.util.*;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MenuImpl implements Menu {

    private final static String SEP = File.separator;
    private static final String FILE_PATH = "src" + SEP +
                                            "main" + SEP +
                                            "resources" + SEP +
                                            "menu.json";

    private static List<Pizza> menu;

    public static List<Pizza> generateMenu() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            menu = mapper.readValue(new File(FILE_PATH), new TypeReference<List<Pizza>>() {});
        } catch (StreamReadException e) {
            System.out.println(e.toString());
            System.exit(1);
        } catch (DatabindException e) {
            System.out.println(e.toString());
            System.exit(1);
        } catch (IOException e) {
            System.out.println(e.toString());
            System.exit(1);
        }
        return menu;
    }

    @Override
    public void show() {
        for(final Pizza pizza : MenuImpl.menu) {
            System.out.println(pizza.getName()          + " " +
                                pizza.getIngredients()  + " " +
                                pizza.getCost()         + "\n" );
        }
    }

    @Override
    public void update() {
        
    }
}
