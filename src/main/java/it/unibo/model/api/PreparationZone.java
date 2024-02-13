package it.unibo.model.api;

import java.util.Map;
import java.util.Optional;

/**
 * Interface of the zone in which the pizza is prepared.
 */
public interface PreparationZone {
    /**
     * @return pizza number 1
     */
    PizzaFactory getPizza1();

    /**
     * @return pizza number 2, only if is there
     */
    PizzaFactory getPizza2();

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

}
