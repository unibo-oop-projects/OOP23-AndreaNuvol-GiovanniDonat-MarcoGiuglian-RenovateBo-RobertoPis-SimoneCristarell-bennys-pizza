package it.unibo.model.api;

import it.unibo.model.impl.management.SubtractorManager;

/**
 * Interface of ingredient supply.
 */
public interface Supplier {
    /**
     * It increases the quantity of the ingredient that you choose.
     * @param ingredient
     * @param balance
     */
    void supply(Ingredient ingredient, SubtractorManager balance);

    /**
     * It reduce money like a payment for a supply.
     * @param balance
     */
    void payment(SubtractorManager balance);
}
