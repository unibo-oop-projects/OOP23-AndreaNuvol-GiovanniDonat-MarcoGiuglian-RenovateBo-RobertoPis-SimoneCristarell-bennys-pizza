package it.unibo.model.impl;

import it.unibo.model.api.Supplier;
import it.unibo.model.api.Ingredient;

public class SupplierImpl implements Supplier {
    private static final double amountToPay = -5;

    @Override
    public final void supply(final Ingredient ingredient,final  Management balance) {
        ingredient.supply();
        SupplierImpl.payment(balance);
    }

    private static final void payment(final Management balance) {
        balance.updateBalance(amountToPay);
    }
}