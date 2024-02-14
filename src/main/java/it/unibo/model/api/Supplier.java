package it.unibo.model.api;

import it.unibo.model.impl.SubtractorManager;

/**
 * Interface of ingredient supply
 */

public interface Supplier {
    /**
     * It increases the quantity of the ingredient that you choose.
     */
    void supply(Ingredient ingredient, SubtractorManager balance);

    /**
     * It reduce money like a payment for a supply.
     */
    void payment(SubtractorManager balance);

}