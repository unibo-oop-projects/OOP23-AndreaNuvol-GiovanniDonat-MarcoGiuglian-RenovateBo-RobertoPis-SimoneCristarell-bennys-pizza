package it.unibo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import it.unibo.model.api.Ingredient;
import it.unibo.model.api.PizzaFactory;
import it.unibo.model.api.PreparationZone;
import it.unibo.model.impl.IngredientImpl;
import it.unibo.model.impl.PizzaFactoryImpl;
import it.unibo.model.impl.PreparationZoneImpl;

public class TestPreparationZone {

    private static final int MIN_PIZZE = 1;
    private static final int MAX_PIZZE = 2;
    private static final int BIGGER_NUMBER_OF_PIZZE = 3;
    private static final int LOWER_NUMBER_OF_PIZZE = 0;

    
    @Test
    public void TestPizzasGetter() {
        try {
            final PreparationZone preparationZone = new PreparationZoneImpl(MIN_PIZZE);
            assertTrue(Optional.of(preparationZone.getPizza1()).isPresent());
            assertFalse(Optional.of(preparationZone.getPizza2()).isPresent());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            final PreparationZone preparationZone2 = new PreparationZoneImpl(MAX_PIZZE);
            assertTrue(Optional.of(preparationZone2.getPizza1()).isPresent());
            assertTrue(Optional.of(preparationZone2.getPizza2()).isPresent());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            final PreparationZone preparationZone3 = new PreparationZoneImpl(BIGGER_NUMBER_OF_PIZZE);
            assertFalse(Optional.of(preparationZone3.getPizza1()).isPresent());
            assertFalse(Optional.of(preparationZone3.getPizza2()).isPresent());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            final PreparationZone preparationZone4 = new PreparationZoneImpl(LOWER_NUMBER_OF_PIZZE);
            assertFalse(Optional.of(preparationZone4.getPizza1()).isPresent());
            assertFalse(Optional.of(preparationZone4.getPizza2()).isPresent());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void TestIngredientsQuantitiesGetter() {
        try {
            final Map<Ingredient, Integer> ingredientsQuantities = new HashMap<>();
            final List<String> ingredients = new ArrayList<>(List.of("Anchovy", "Artichoke", "CherryTomatoe",
                "Dough", "Fontina", "FrenchFry", "Gorgonzola", "Ham", "Mozzarella", "Mushroom", "Olive", "Onion",
                "Parmesan", "Salami", "Sausage", "TomatoSauce", "Tuna", "Wurstel"));

            for (final var cl: ingredients) {
                final var clazz = Class.forName(this.getClass().getPackageName() + ".IngredientsImpl." + cl);
                ingredientsQuantities.put((Ingredient)clazz.getConstructor().newInstance(), IngredientImpl.MAX_QUANTITY);
        }
            final PreparationZone preparationZone = new PreparationZoneImpl(MIN_PIZZE);
            assertEquals(preparationZone.getIngredientsQuantities(), ingredientsQuantities);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void TestOvenGetter() {
        try {
            final PreparationZone preparationZone = new PreparationZoneImpl(MIN_PIZZE);
            assertTrue(Optional.of(preparationZone.getOven()).isPresent());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
