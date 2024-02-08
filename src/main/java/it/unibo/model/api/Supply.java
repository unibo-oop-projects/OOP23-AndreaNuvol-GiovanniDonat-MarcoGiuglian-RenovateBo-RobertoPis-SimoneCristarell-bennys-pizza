package it.unibo.model.api

/**
 * Interface of ingredient supply
 */

public interface Supply {
    /**
     * It increases the quantity of the ingredient that you choose.
     */
    void supply(Ingredient ingredient);

    /**
     * It reduce money like a payment for a supply.
     */
    void payment()

}