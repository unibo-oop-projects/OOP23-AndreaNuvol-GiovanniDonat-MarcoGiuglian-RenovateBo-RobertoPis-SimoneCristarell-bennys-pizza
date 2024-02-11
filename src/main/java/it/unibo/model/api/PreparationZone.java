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
    Optional<PizzaFactory> getPizza2();

    /**
     * @return a map with the Ingredient as Key and the quantity of this as Value
     */
    Map<Ingredient, Integer> getIngredientsQuantities();

    /**
     * @return the oven
     */
    Oven getOven();

}
