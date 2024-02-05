package it.unibo.model.impl;

import it.unibo.model.api.Ingredient;

/**
 * Abstract implementation of the 'Ingredient' interface
 */
public abstract class IngredientImpl implements Ingredient {

    protected static final int MAX_QUANTITY = 100;
    protected int quantity;
    protected double price;
    protected String imagePath;

    protected IngredientImpl(final double price, final String imagePath) {
        this.quantity = MAX_QUANTITY;
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

    public abstract void reduce();

    /**
     * Method in common among all the classes that inherit from 'IngredientImpl'.
     * It is used in the method 'reduce()' of these classes
     * @param toReduce the quantity of the ingredient to reduce
     */
    protected void reduceIngredient(final int toReduce) {
        this.quantity = this.quantity - toReduce;
    }

}