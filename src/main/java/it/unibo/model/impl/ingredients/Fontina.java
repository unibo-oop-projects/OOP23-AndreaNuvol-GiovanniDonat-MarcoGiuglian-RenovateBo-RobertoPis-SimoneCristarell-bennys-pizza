package it.unibo.model.impl.ingredients;

import it.unibo.model.impl.IngredientImpl;

/**
 * Class that emulates the cheese called 'Fontina'.
 */
public class Fontina extends IngredientImpl {
    private static final int QUANTITY_TO_REDUCE = 10;
    private static final double PRICE = 1;
    private static final String IMAGE_NAME = "Fontina.png";

    /**
     * The constructor of the class Fontina.
     */
    public Fontina() {
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
