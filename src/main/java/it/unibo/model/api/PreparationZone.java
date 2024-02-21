package it.unibo.model.api;

import java.util.Map;
import java.util.Optional;

import it.unibo.model.impl.PizzaFactoryImpl;

/**
 * Interface of the zone in which the pizza is prepared.
 */
public interface PreparationZone {
    /**
     * It sets the number of pizzas to prepare, requested by the current client.
     * @param numberOfPizzas the number of pizzas to prepare.
     */
    void setNumberOfPizzasToPrepare(int numberOfPizzas);

    /** 
     * @return if number of pizzas to prepare is set.
     */
    boolean isNumberOfPizzasToPrepareSet();

    /**
     * @return pizza number 1.
     */
    PizzaFactoryImpl getPizza1();

    /**
     * @return pizza number 2, only if is there.
     */
    Optional<PizzaFactoryImpl> getPizza2();

    /**
     * @return a map with the Ingredient as Key and the quantity of this as Value.
     */
    Map<Ingredient, Integer> getIngredientsQuantities();

    /**
     * @return the oven.
     */
    Oven getOven();

     /**
     * @return the garbage bin.
     */
    GarbageBin getGarbageBin();

    /**
     * It update quantities of an ingredient in the map when this is changed.
     * 
     * @param ingredientName the ingrediet of which we have to update quantities.
     * @param isPizza1 true if the ingredient is in pizza1, false if is in pizza2.
     * @param isASupply true if we have to supply, false if we have to reduce the quantity.
     */
    void actionsOnIngredients(String ingredientName, boolean isPizza1, boolean isASupply);

    /**
     * It resets the two pizza factories of the preparation zone.
     * @param isPizza1 true if the pizza to reset is the n. 1.
     */
    void resetPizza(boolean isPizza1);

    /**
     * It is called when you have finished to prepare a cient's order.
     */
    void resetPizzaPreparation();
}
