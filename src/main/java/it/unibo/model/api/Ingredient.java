package it.unibo.model.api;

public interface Ingredient {
    /**
     * Increase the quantity of the ingredient to the maximum available
     */
    void supply();

    /**
     * Decrease the quantity of the ingredient when it is put on a pizza
     * NOTE: the quantity is different for each ingredient
     */
    void reduce();
}