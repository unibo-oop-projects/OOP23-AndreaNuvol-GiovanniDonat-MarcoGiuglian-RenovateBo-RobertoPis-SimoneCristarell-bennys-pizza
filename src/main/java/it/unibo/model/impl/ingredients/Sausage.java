package it.unibo.model.impl.ingredients;

import it.unibo.model.impl.IngredientImpl;

/**
 * Class that emulates a piece of sausage.
 */
public class Sausage extends IngredientImpl {
    private static final int QUANTITY_TO_REDUCE = 14;
    private static final double PRICE = 1.4;
    private static final String IMAGE_NAME = "Sausages.png";

    /**
     * The constructor of the class Sausage.
     */
    public Sausage() {
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
