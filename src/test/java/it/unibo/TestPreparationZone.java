package it.unibo;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import it.unibo.model.api.Ingredient;
import it.unibo.model.api.PreparationZone;
import it.unibo.model.impl.IngredientImpl;
import it.unibo.model.impl.PreparationZoneImpl;
import it.unibo.model.impl.SubtractorManager;

public class TestPreparationZone {

    private static final int MIN_PIZZAS = 1;
    private static final int BIGGER_NUMBER_OF_PIZZAS = 3;
    private static final int LOWER_NUMBER_OF_PIZZAS = 0;

    private void testNumberOfPizzas(final int number, final String string) {
        try {
            final PreparationZone preparationZone = new PreparationZoneImpl(number, new SubtractorManager());
            preparationZone.getPizza1();
            preparationZone.getPizza2();
        } catch (Exception e) {
            assertEquals(string, e.getMessage());
        }
    }

    
    @Test
    public void testPizzasGetter() {
        testNumberOfPizzas(MIN_PIZZAS, "Pizza n. 2 is not requested from this client.");
        testNumberOfPizzas(BIGGER_NUMBER_OF_PIZZAS, "The number of pizzas to prepare can be only 1 or 2.");
        testNumberOfPizzas(LOWER_NUMBER_OF_PIZZAS, "The number of pizzas to prepare can be only 1 or 2.");
    }

    @Test
    public void testIngredientsQuantitiesGetter() {
        try {
            final Map<Ingredient, Integer> ingredientsQuantities = new HashMap<>();
            final List<String> ingredients = new ArrayList<>(List.of("Anchovy", "Artichoke", "CherryTomatoe",
                "Dough", "Fontina", "FrenchFry", "Gorgonzola", "Ham", "Mozzarella", "Mushroom", "Olive", "Onion",
                "Parmesan", "Salami", "Sausage", "TomatoSauce", "Tuna", "Wurstel"));

            for (final var cl: ingredients) {
                final var clazz = Class.forName(this.getClass().getPackageName() + ".IngredientsImpl." + cl);
                ingredientsQuantities.put((Ingredient)clazz.getConstructor().newInstance(), IngredientImpl.MAX_QUANTITY);
        }
            final PreparationZone preparationZone = new PreparationZoneImpl(MIN_PIZZAS, new SubtractorManager());
            assertEquals(preparationZone.getIngredientsQuantities(), ingredientsQuantities);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void testActionIngredient(final PreparationZone pz, final boolean isASupply, final int expectedQuantity) {
        pz.actionsOnIngredients("Wurstel", true, isASupply);
        for (Ingredient i: pz.getIngredientsQuantities().keySet()) {
            if (i.toString().equals("Wurstel")) {
                assertEquals(expectedQuantity, i.getQuantity());
            }
        }
    }

    @Test
    public void testUpdateQuantities() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        PreparationZone p = new PreparationZoneImpl(MIN_PIZZAS, new SubtractorManager());
        testActionIngredient(p, false, 85);
        testActionIngredient(p, true, IngredientImpl.MAX_QUANTITY);
    }
}
