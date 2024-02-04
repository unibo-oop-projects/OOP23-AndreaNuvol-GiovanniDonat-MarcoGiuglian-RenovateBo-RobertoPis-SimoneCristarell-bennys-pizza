package it.unibo.model.impl;

import it.unibo.model.api.*;
import java.io.*;
import java.util.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MenuImpl implements Menu {

    private final static String SEP = File.separator;
    private static final String FILE_PATH = System.getProperty("user.dir") + SEP +
                                            "src" + SEP +
                                            "main" + SEP +
                                            "resources" + SEP +
                                            "menu.json";
    private List<Pizza> menu;

    public void createMenu() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            this.menu = mapper.readValue(new File(FILE_PATH), new TypeReference<ArrayList<Pizza>>(){} );
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void show() {
        System.out.println(this.menu.toString());
    }

    /*public void readPath() {
        System.out.println(FILE_PATH);
    }*/

    @Override
    public void update() {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    public static void main(String[] args) {
        MenuImpl menu = new MenuImpl();
        menu.createMenu(); // file not found
        menu.show();
        // menu.readPath();
    }
    
}
