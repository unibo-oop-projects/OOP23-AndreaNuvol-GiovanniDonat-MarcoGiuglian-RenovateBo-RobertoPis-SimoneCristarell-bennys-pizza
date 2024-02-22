package it.unibo.model.impl.ingredients;

import it.unibo.model.impl.IngredientImpl;

/**
 * Class that emulates a slice of ham.
 */
public class Ham extends IngredientImpl {
    private static final int QUANTITY_TO_REDUCE = 6;
    private static final double PRICE = 0.8;
    private static final String IMAGE_NAME = "Ham.png";

    /**
     * The constructor of the class Ham.
     */
    public Ham() {
        super(PRICE, IMAGE_NAME);
    }

    /**
     * It reduces this ingredient's quantity with the given number.
     */
    @Override
    public void reduce() {
        super.reduceIngredient(QUANTITY_TO_REDUCE);
    }
}
