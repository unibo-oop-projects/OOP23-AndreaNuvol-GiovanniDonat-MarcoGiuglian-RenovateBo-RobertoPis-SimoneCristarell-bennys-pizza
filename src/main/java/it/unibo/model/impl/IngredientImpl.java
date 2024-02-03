package it.unibo.model.impl;

import it.unibo.model.api.Ingredient;

public abstract class IngredientImpl implements Ingredient {

    protected static final int MAX_QUANTITY = 100;
    protected int quantity = MAX_QUANTITY;

    @Override
    public final void supply() {
        this.quantity = MAX_QUANTITY;
    }

    @Override
    public abstract void reduce();
}