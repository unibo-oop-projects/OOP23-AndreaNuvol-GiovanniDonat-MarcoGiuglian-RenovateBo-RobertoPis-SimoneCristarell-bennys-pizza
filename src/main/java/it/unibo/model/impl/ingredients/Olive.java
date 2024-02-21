package it.unibo.model.impl.ingredients;

import it.unibo.model.impl.IngredientImpl;

/**
 * Class that emulates an olive.
 */
public class Olive extends IngredientImpl {
    private static final int QUANTITY_TO_REDUCE = 15;
    private static final double PRICE = 1;
    private static final String IMAGE_NAME = "Olives.png";

    /**
     * The constructor of the class Olive.
     */
    public Olive() {
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
