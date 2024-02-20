package it.unibo.model.impl.IngredientsImpl;

import it.unibo.model.impl.IngredientImpl;

/**
 * Class that emulates the tuna.
 */
public class Tuna extends IngredientImpl {
    private static final int QUANTITY_TO_REDUCE = 11;
    private static final double PRICE = 1.1;
    private static final String IMAGE_NAME = "Tuna.png";

    /**
     * The constructor of the class Tuna.
     */
    public Tuna() {
        super(PRICE, IMAGE_NAME);
    }

    @Override
    public void reduce() {
        super.reduceIngredient(QUANTITY_TO_REDUCE);
    }
}
