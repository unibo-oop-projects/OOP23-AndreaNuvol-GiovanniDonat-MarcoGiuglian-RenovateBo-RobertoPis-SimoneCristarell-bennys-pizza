package it.unibo.model.impl;

import it.unibo.model.api.*;

public class SupplierImpl implements Supplier {
    private static final double amountToPay = 5;

    @Override
    public final void supply(final Ingredient ingredient, final SubtractorManager balance) {
        ingredient.supply();
        payment(balance);
    }

    public final void payment(final SubtractorManager balance) {
        balance.updateBalance(amountToPay);
    }
}