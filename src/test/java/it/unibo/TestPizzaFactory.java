package it.unibo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import it.unibo.model.api.PizzaFactory;
import it.unibo.model.impl.PizzaFactoryImpl;
import it.unibo.model.impl.IngredientsImpl.Dough;
import it.unibo.model.impl.IngredientsImpl.FrenchFry;
import it.unibo.model.impl.IngredientsImpl.Ham;
import it.unibo.model.impl.IngredientsImpl.Mozzarella;
import it.unibo.model.impl.IngredientsImpl.Mushroom;
import it.unibo.model.impl.IngredientsImpl.Olive;
import it.unibo.model.impl.IngredientsImpl.TomatoSauce;

public class TestPizzaFactory {
    
    @Test
    public void testAddingIngredients() {
        final PizzaFactory pizza = new PizzaFactoryImpl();
        pizza.addIngredient(new Dough());
        assertEquals(Collections.unmodifiableList(List.of(new Dough())), pizza.getAddedIngredients());
        pizza.addIngredient(new TomatoSauce());
        pizza.addIngredient(new Mozzarella());
        assertNotEquals(Collections.unmodifiableList(List.of(new TomatoSauce(), new Mozzarella())), pizza.getAddedIngredients());
        pizza.addIngredient(new FrenchFry());
        pizza.addIngredient(new FrenchFry());
        assertEquals(Collections.unmodifiableList(List.of(new Dough(), new TomatoSauce(), new Mozzarella(), new FrenchFry())), 
            pizza.getAddedIngredients());
    }

    @Test
    public void testPreparedPizzaEqualToTheRequestedOne() {
        final var ingredientsRequested = new ArrayList<>(List.of(new Dough(), new TomatoSauce(), new Mozzarella(), new Ham(), new Mushroom()));
        final PizzaFactory pizza = new PizzaFactoryImpl();
        pizza.addIngredient(new Dough());
        pizza.addIngredient(new TomatoSauce());
        pizza.addIngredient(new Mozzarella());
        assertFalse(pizza.equals(ingredientsRequested));
        pizza.addIngredient(new Mushroom());
        pizza.addIngredient(new Ham());
        assertTrue(pizza.equals(ingredientsRequested));
        pizza.addIngredient(new Mushroom());
        assertTrue(pizza.equals(ingredientsRequested));
        pizza.addIngredient(new Olive());
        assertFalse(pizza.equals(ingredientsRequested));
    }

}
