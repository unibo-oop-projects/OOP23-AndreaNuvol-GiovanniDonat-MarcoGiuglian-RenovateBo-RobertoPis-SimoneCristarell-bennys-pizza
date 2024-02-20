package it.unibo.model.api;

/**
 * Interface of an ingredient.
 */
public interface Ingredient {
    /**
     * It increases the quantity of the ingredient to the maximum available.
     */
    void supply();

    /**
     * It decreases the quantity of the ingredient when it is put on a pizza.
     * NOTE: the quantity is different for each ingredient.
     */
    void reduce();

    /**
     * @return the price of the ingredient.
     */ 
    double getPrice();

    /**
     * @return the path of the image of the ingredient.
     */
    String getImagePath();

    /**
     * @return the current quantity of the ingredient.
     */
    int getQuantity();
}
