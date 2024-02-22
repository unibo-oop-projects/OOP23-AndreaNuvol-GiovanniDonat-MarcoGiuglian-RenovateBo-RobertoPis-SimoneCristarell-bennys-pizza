package it.unibo.model.impl.ingredients;

import it.unibo.model.impl.IngredientImpl;

/**
 * Class that emulates an artichoke.
 */
public class Artichoke extends IngredientImpl {
    private static final int QUANTITY_TO_REDUCE = 6;
    private static final double PRICE = 1.1;
    private static final String IMAGE_NAME = "Artichokes.png";

    /**
     * The constructor of the class Artichokes.
     */
    public Artichoke() {
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
