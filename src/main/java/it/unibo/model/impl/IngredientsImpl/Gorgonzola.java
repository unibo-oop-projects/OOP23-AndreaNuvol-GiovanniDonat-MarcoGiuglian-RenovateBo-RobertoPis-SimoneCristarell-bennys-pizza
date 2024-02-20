package it.unibo.model.impl.IngredientsImpl;

import it.unibo.model.impl.IngredientImpl;

/**
 * Class that emulates the cheese called 'Gorgonzola'.
 */
public class Gorgonzola extends IngredientImpl {
    private static final int QUANTITY_TO_REDUCE = 10;
    private static final double PRICE = 1.2;
    private static final String IMAGE_NAME = "Gorgonzola.png";

    /**
     * The constructor of the class Gorgonzola.
     */
    public Gorgonzola() {
        super(PRICE, IMAGE_NAME);
    }

    @Override
    public void reduce() {
        super.reduceIngredient(QUANTITY_TO_REDUCE);
    }
}
