package it.unibo.model.api;

import java.util.Map;

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
     * @return a list of the ingredients that are dirtying the preparation zone
     */
    // List<Ingredient> getDirtyIngredients();

    /**
     * It checks if the preparation zone is dirty
     * @return true if the preparation zone is dirty, false otherwise
     */
    boolean isDirty();

}
