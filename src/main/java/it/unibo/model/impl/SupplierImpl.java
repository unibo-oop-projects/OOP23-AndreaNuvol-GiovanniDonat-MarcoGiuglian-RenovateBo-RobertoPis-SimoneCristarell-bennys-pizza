package it.unibo.model.impl;

import it.unibo.model.api.Supplier;
import it.unibo.model.impl.management.SubtractorManager;
import it.unibo.model.api.Ingredient;

/**
 * Class that implement the supplier interface to manage the supply of ingredients.
 */
public class SupplierImpl implements Supplier {
    private static final double AMOUNT_TO_PAY = 4;

    /**
     * method to supply the ingredient.
     */
    @Override
    public final void supply(final Ingredient ingredient, final SubtractorManager balance) {
        ingredient.supply();
        payment(balance);
    }

    /**
     * method to pay for the supply.
     * @param balance the current balance of the pizzeria.
     */
    @Override
    public final void payment(final SubtractorManager balance) {
        balance.updateBalance(AMOUNT_TO_PAY);
    }
}
