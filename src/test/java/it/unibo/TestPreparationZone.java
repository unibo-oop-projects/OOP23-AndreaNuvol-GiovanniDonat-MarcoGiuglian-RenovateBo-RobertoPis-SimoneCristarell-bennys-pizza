package it.unibo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.lang.reflect.InvocationTargetException;
import org.junit.jupiter.api.Test;
import it.unibo.model.api.Ingredient;
import java.util.Optional;
import it.unibo.model.api.Management;
import it.unibo.model.api.PreparationZone;
import it.unibo.model.impl.PreparationZoneImpl;
import it.unibo.model.impl.management.AdderManager;
import it.unibo.model.impl.management.SubtractorManager;

/**
 * Test for the PreparationZoneImpl class.
 */
class TestPreparationZone {

    private static final int MIN_PIZZAS = 1;
    private static final int MAX_QUANTITY = 100;
    private static final int BIGGER_NUMBER_OF_PIZZAS = 3;
    private static final int LOWER_NUMBER_OF_PIZZAS = 0;
    private static final int EXPTECTED_VALUE = 82;

    /**
     * Test unset number of pizzas to prepare.
     */
    @Test
    void testUnsetNumberOfPizzasToPrepare() {
        try {
            final PreparationZone preparationZone = new PreparationZoneImpl(new SubtractorManager());
            preparationZone.getPizza1();
            preparationZone.getPizza2();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
        | InvocationTargetException | NoSuchMethodException | IllegalStateException e) {
            assertEquals("The number of pizzas to prepare is unknown.", e.getMessage());
        }
    }

    /**
     * Test the number of pizzas.
     * @param number the number passed to check.
     * @throws NoSuchMethodException 
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     * @throws ClassNotFoundException 
     */
    private void testWrongNumberOfPizzas(final int number) throws ClassNotFoundException, InstantiationException,
     IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        final PreparationZone preparationZone = new PreparationZoneImpl(new SubtractorManager());
        assertThrows(IllegalArgumentException.class, () -> {
            preparationZone.setNumberOfPizzasToPrepare(number);
        });
    }

    private void testCorrectNumberOfPizzas(final int number) throws ClassNotFoundException, InstantiationException,
     IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        final PreparationZone preparationZone = new PreparationZoneImpl(new SubtractorManager());
        preparationZone.setNumberOfPizzasToPrepare(number);
        preparationZone.getPizza1();
        assertEquals(Optional.empty(), preparationZone.getPizza2());
    }

    /**
     * Test the getter of pizzas.
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws ClassNotFoundException
     */
    @Test
    void testPizzasGetter() throws ClassNotFoundException, InstantiationException, IllegalAccessException,
     InvocationTargetException, NoSuchMethodException {
        testCorrectNumberOfPizzas(MIN_PIZZAS);
        testWrongNumberOfPizzas(BIGGER_NUMBER_OF_PIZZAS);
        testWrongNumberOfPizzas(LOWER_NUMBER_OF_PIZZAS);
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
            for (final Ingredient i: pz.getIngredientsQuantities().keySet()) {
                if ("Dough".equals(i.toString())) {
                    assertEquals(expectedQuantity, i.getQuantity());
                }
            }
        } catch (IllegalStateException e) {
            assertEquals("The quantity of this ingredient is already the maximum possible.", e.getMessage());
        }
    }

    /**
     * Test the update of quantities of ingredients.
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     */
    @Test
    void testUpdateQuantities() throws ClassNotFoundException,
                                                InstantiationException,
                                                IllegalAccessException,
                                                InvocationTargetException,
                                                NoSuchMethodException {
        final PreparationZone p = new PreparationZoneImpl(new SubtractorManager());
        p.setNumberOfPizzasToPrepare(MIN_PIZZAS);
        testActionIngredient(p, false, EXPTECTED_VALUE);
        testActionIngredient(p, true, MAX_QUANTITY);
        testActionIngredient(p, true, MAX_QUANTITY);
    }
}
