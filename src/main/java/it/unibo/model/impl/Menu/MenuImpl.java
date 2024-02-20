package it.unibo.model.impl.Menu;

import java.util.Collections;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Implementation of the menu's interface.
 */
public class MenuImpl {
    private static final String SEP = File.separator;
    private static final String PATH_TO_THE_ROOT = FileSystems.getDefault().getPath(new String()).toAbsolutePath().toString();
    private static final String FILE_PATH = SEP + "src" + SEP + "main" + SEP + "resources" + SEP + "menu.json";
    private static List<Pizza> pizzas;

    /**
     * This method generate the menu.
     */
    public static void generateMenu() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            pizzas = mapper.readValue(new File(PATH_TO_THE_ROOT + FILE_PATH), new TypeReference<List<Pizza>>() { });
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
    }

    /**
     * It shows the generated menu.
     */
    public static void show() {
        for (final Pizza pizza : pizzas) {
            System.out.println(pizza.getName() + " " + pizza.getIngredients()  + " " + pizza.getCost() + "\n");
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
        return pizzas;
    }

    /**
     * Class that emulates a pizza on the menu.
     */
    public static class Pizza {
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
            return this.ingredients;
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
         */
        public String toString() {
            String output;
            output = getName() + " " + getIngredients() + " " + getCost() + "$\n";
            return output;
        }        
    }

}
