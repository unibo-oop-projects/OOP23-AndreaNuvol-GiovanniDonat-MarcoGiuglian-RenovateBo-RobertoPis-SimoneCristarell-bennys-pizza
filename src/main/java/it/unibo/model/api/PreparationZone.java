package it.unibo.model.api;

import java.util.Map;
import java.util.Optional;

/**
 * Interface of the zone in which the pizza is prepared.
 */
public interface PreparationZone {

    /**
     * It sets the number of pizzas to prepare, requested by the current client
     * @param numberOfPizzas the number of pizzas to prepare
     */
    void setNumberOfPizzasToPrepare(int numberOfPizzas);

    /**
     * @return pizza number 1
     */
    PizzaFactory getPizza1();

    /**
     * @return pizza number 2, only if is there
     * @throws IllegalStateException if the pizza n. 2 is not requested
     */
    PizzaFactory getPizza2() throws IllegalStateException;

    /**
     * @return a map with the Ingredient as Key and the quantity of this as Value
     */
    Map<Ingredient, Integer> getIngredientsQuantities();

    /**
     * @return the oven
     */
    Oven getOven();

    /**
     * @return the clenear 
     */
    Cleaner getCleaner();

    /**
     * @return the garbage bin
     */
    GarbageBin getGarbageBin();

    /**
     * It checks if the preparation zone is dirty
     * @return true if the preparation zone is dirty, false otherwise
     */
    boolean isDirty();

    /**
     * It adds an ingredient that is dirtying the preparation zone or it 'cleans' it
     * @param dirtyIngredient if this optional is not empty, then it will be added an ingredient to the dirty ingredients,
     * otherwise all the dirty ingredients will be removed
     */
    void manageDirtyIngredients(Optional<Ingredient> dirtyIngredient);

    /**
     * It update quantities of an ingredient in the map when this is changed
     * 
     * @param ingredientName the ingrediet of which we have to update quantities
     * @param isPizza1 true if the ingredient is in pizza1, false if is in pizza2
     * @param isASupply true if we have to supply, false if we have to reduce the quantity
     * @throws IllegalStateException when you want to supply an ingredient that is already at its maximum quantity
     */
    void actionsOnIngredients(String ingredientName, boolean isPizza1, boolean isASupply) throws IllegalStateException;

    /**
     * It resets the two pizza factories of the preparation zone
     */
    void resetPizzas();

}
