package it.unibo.model.impl.Menu;

import it.unibo.model.api.*;
import it.unibo.model.impl.IngredientImpl;

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
        for(final Pizza pizza : menu) {
            System.out.println(pizza.getName()          + " " +
                                pizza.getIngredients()  + " " +
                                pizza.getCost()         + "\n" );
        }
    }

    public static int getNumPizzasInMenu() {
        return menu.size();
    }

    @Override
    public void update() {
        
    }

    public static class Pizza {
    
        private final String name;
        private final float cost;
        private List<IngredientImpl> ingredients;
    
        public Pizza(String name, float cost, List<IngredientImpl> ingredients) {
            this.name = name;
            this.cost = cost;
            this.ingredients = ingredients;
        }
    
        public String getName() {
            return this.name;
        }
    
        public List<IngredientImpl> getIngredients() {
            return this.ingredients;
        }

        public int getNumIngredientsOfPizza() {
            return this.ingredients.size();
        }
    
        public float getCost() {
            return this.cost;
        }
        
    }
}
