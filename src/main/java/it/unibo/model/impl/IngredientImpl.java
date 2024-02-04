package it.unibo.model.impl;

import it.unibo.model.api.Ingredient;

/**
 * Implementation of the 'Ingredient' interface
 */
public class IngredientImpl implements Ingredient {

    protected static final int MAX_QUANTITY = 100;
    protected int quantity = MAX_QUANTITY;
    protected double price;
    protected String imagePath;

    public IngredientImpl(final double price, final String imagePath) {
        this.price = price;
        this.imagePath = imagePath;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public String getImagePath() {
        return this.imagePath;
    }

    @Override
    public final void supply() {
        this.quantity = MAX_QUANTITY;
    }

    @Override
    public void reduce(final int toReduce) {
        this.quantity = this.quantity - toReduce;
    }

}