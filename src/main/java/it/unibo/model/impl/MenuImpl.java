package it.unibo.model.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementation of the menu's interface.
 */
public final class  MenuImpl {
    private static final String SEP = File.separator;
    private static final String PATH_TO_THE_ROOT = FileSystems.getDefault().getPath("").toAbsolutePath().toString();
    private static final String FILE_PATH = SEP + "src" + SEP + "main" + SEP + "resources" + SEP + "menu.json";
    private static List<Pizza> pizzas;
    private static Logger logger = Logger.getLogger(MenuImpl.class.getName());

    private MenuImpl() { }

    /**
     * This method generate the menu.
     */
    public static void generateMenu() {
        final ObjectMapper mapper = new ObjectMapper();
        try {
            pizzas = mapper.readValue(new File(PATH_TO_THE_ROOT + FILE_PATH), new TypeReference<List<Pizza>>() { });
        } catch (IOException e) {
            logger.log(Level.WARNING, e.toString());
        }
    }

    /**
     * It shows the generated menu.
     */
    public static void show() {
        for (final Pizza pizza : pizzas) {
            logger.log(Level.INFO, pizza.getName() + " " + pizza.getIngredients()  + " " + pizza.getCost() + "\n");
        }
    }

    /**
     * @return the number of pizzas of the menu.
     */
    public static int getNumPizzasInMenu() {
        return pizzas.size();
    }

    /**
     * @return a list which contains the pizzas of the menu.
     */
    public static List<Pizza> getPizzas() {
        return new ArrayList<>(pizzas);
    }

    /**
     * Class that emulates a pizza on the menu.
     */
    public static final class Pizza {
        private final String name;
        private final float cost;
        private final List<String> ingredients;

        private Pizza(final String name, final float cost, final List<String> ingredients) {
            this.name = name;
            this.cost = cost;
            this.ingredients = ingredients;
        }

        private Pizza() {
            this.name = "";
            this.cost = 0;
            this.ingredients = Collections.emptyList();
        }

        /**
         * @return the name of the pizza.
         */
        public String getName() {
            return this.name;
        }

        /**
         * @return a list with the ingredients' names.
         */
        public List<String> getIngredients() {
            return new ArrayList<>(this.ingredients);
        }

        /**
         * @return the number of the ingredients on a pizza.
         */
        public int getNumIngredientsOfPizza() {
            return this.ingredients.size();
        }

        /**
         * @return the cost of the pizza.
         */
        public float getCost() {
            return this.cost;
        }

        /**
         * Return a description of the pizza.
         * @return a string that contains the description of the pizza.
         */
        @Override
        public String toString() {
            String output;
            output = getName() + " " + getIngredients() + " " + getCost() + "$\n";
            return output;
        }
    }

}
