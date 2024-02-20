package it.unibo.model.impl.Menu;

import java.io.*;
import java.util.List;
import java.nio.file.FileSystems;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MenuImpl {
    final static String SEP = File.separator;
    private static final String PATH_TO_THE_ROOT = FileSystems.getDefault().getPath(new String()).toAbsolutePath().toString();
    private static final String FILE_PATH = SEP         +
                                            "src"       + SEP +
                                            "main"      + SEP +
                                            "resources" + SEP +
                                            "menu.json";

    private static List<Pizza> pizzas;

    public static void generateMenu() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            pizzas = mapper.readValue(new File(PATH_TO_THE_ROOT + FILE_PATH), new TypeReference<List<Pizza>>() {});   
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

    public static void show() {
        for(final Pizza pizza : pizzas) {
            System.out.println(pizza.getName()          + " " +
                                pizza.getIngredients()  + " " +
                                pizza.getCost()         + "\n");
        }
    }

    public static int getNumPizzasInMenu() {
        return pizzas.size();
    }

    public static List<Pizza> getPizzas() {
        return pizzas;
    }

    public static class Pizza {    
        private final String name;
        private final float cost;
        private List<String> ingredients;

        public Pizza(final String name, final float cost, final List<String> ingredients) {
            this.name = name;
            this.cost = cost;
            this.ingredients = ingredients;
        }
        
        public Pizza() {
            this.name = "";
            this.cost = 0;
        }

        public String getName() {
            return this.name;
        }

        public List<String> getIngredients() {
            return this.ingredients;
        }

        public int getNumIngredientsOfPizza() {
            return this.ingredients.size();
        }
            
        public float getCost() {
            return this.cost;
        }

        public String toString() {
            String output;
            output = getName() + " " + getIngredients() + " " + getCost() + "$\n";
            return output;
        }        
    }

}
