package it.unibo.model.impl.ingredients;

import it.unibo.model.impl.IngredientImpl; 

/**
 * Class that emulates a piece of wurstel.
 */
public class Wurstel extends IngredientImpl {
    private static final int QUANTITY_TO_REDUCE = 15;
    private static final double PRICE = 1.1;
    private static final String IMAGE_NAME = "Wurstel.png";

    /**
     * The constructor of the class Wurstel.
     */
    public Wurstel() {
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
