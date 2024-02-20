package it.unibo.model.impl.IngredientsImpl;

import it.unibo.model.impl.IngredientImpl;

/**
 * Class that emulates a slice of salami.
 */
public class Salami extends IngredientImpl {
    private static final int QUANTITY_TO_REDUCE = 9;
    private static final double PRICE = 1.2;
    private static final String IMAGE_NAME = "Salami.png";

    /**
     * The constructor of the class Salami.
     */
    public Salami() {
        super(PRICE, IMAGE_NAME);
    }

    @Override
    public void reduce() {
        super.reduceIngredient(QUANTITY_TO_REDUCE);
    }
}
