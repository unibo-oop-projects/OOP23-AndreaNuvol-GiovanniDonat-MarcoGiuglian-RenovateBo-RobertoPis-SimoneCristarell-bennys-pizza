package it.unibo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import it.unibo.model.api.Ingredient;
import it.unibo.model.api.Management;
import it.unibo.model.api.PreparationZone;
import it.unibo.model.impl.IngredientImpl;
import it.unibo.model.impl.PreparationZoneImpl;
import it.unibo.model.impl.Management.AdderManager;
import it.unibo.model.impl.Management.SubtractorManager;

/**
 * Test for the PreparationZoneImpl class.
 */
public class TestPreparationZone {

    private static final int MIN_PIZZAS = 1;
    private static final int BIGGER_NUMBER_OF_PIZZAS = 3;
    private static final int LOWER_NUMBER_OF_PIZZAS = 0;

    /**
     * Test unset number of pizzas to prepare.
     */
    @Test
    public void testUnsetNumberOfPizzasToPrepare() {
        try {
            final PreparationZone preparationZone = new PreparationZoneImpl(new SubtractorManager());
            preparationZone.getPizza1();
            preparationZone.getPizza2();
        } catch (Exception e) {
            assertEquals("The number of pizzas to prepare is unknown.", e.getMessage());
        }
    }

    /**
     * Test the number of pizzas.
     * @param number
     * @param string
     */
    private void testNumberOfPizzas(final int number, final String string) {
        try {
            final PreparationZone preparationZone = new PreparationZoneImpl(new SubtractorManager());
            preparationZone.setNumberOfPizzasToPrepare(number);
            preparationZone.getPizza1();
            preparationZone.getPizza2();
        } catch (Exception e) {
            assertEquals(string, e.getMessage());
        }
    }

    /**
     * Test the getter of pizzas.
     */
    @Test
    public void testPizzasGetter() {
        testNumberOfPizzas(MIN_PIZZAS, "Pizza n. 2 is not requested from this client.");
        testNumberOfPizzas(BIGGER_NUMBER_OF_PIZZAS, "The number of pizzas to prepare can be only 1 or 2.");
        testNumberOfPizzas(LOWER_NUMBER_OF_PIZZAS, "The number of pizzas to prepare can be only 1 or 2.");
    }

    /**
     * Test the ingredients quantities getter.
     */
    @Test
    public void testIngredientsQuantitiesGetter() {
        try {
            final Map<Ingredient, Integer> ingredientsQuantities = new HashMap<>();
            final List<String> ingredients = new ArrayList<>(List.of("Anchovy", "Artichoke", "CherryTomatoe",
                "Dough", "Fontina", "FrenchFry", "Gorgonzola", "Ham", "Mozzarella", "Mushroom", "Olive", "Onion",
                "Parmesan", "Salami", "Sausage", "TomatoSauce", "Tuna", "Wurstel"));

            for (final var cl: ingredients) {
                final var clazz = Class.forName(this.getClass().getPackageName() + ".IngredientsImpl." + cl);
                ingredientsQuantities.put((Ingredient) clazz.getConstructor().newInstance(), IngredientImpl.MAX_QUANTITY);
        }
            final PreparationZone preparationZone = new PreparationZoneImpl(new SubtractorManager());
            assertEquals(preparationZone.getIngredientsQuantities(), ingredientsQuantities);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Test the action of ingredients.
     * @param pz
     * @param isASupply
     * @param expectedQuantity
     */
    private void testActionIngredient(final PreparationZone pz, final boolean isASupply, final int expectedQuantity) {
        final Management adder = new AdderManager();
        adder.updateBalance(10);
        try {
            pz.actionsOnIngredients("Dough", true, isASupply);
            for (Ingredient i: pz.getIngredientsQuantities().keySet()) {
                if (i.toString().equals("Dough")) {
                    assertEquals(expectedQuantity, i.getQuantity());
                }
            }
        } catch (Exception e) {
            assertEquals("The quantity of this ingredient is already the maximum possible.", e.getMessage());
        }        
    }
    
    /**
     * Test the update of quantities of ingredients.
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws SecurityException
     */
    @Test
    public void testUpdateQuantities() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        PreparationZone p = new PreparationZoneImpl(new SubtractorManager());
        p.setNumberOfPizzasToPrepare(MIN_PIZZAS);
        testActionIngredient(p, false, 82);
        testActionIngredient(p, true, IngredientImpl.MAX_QUANTITY);
        testActionIngredient(p, true, IngredientImpl.MAX_QUANTITY);
    }
}
