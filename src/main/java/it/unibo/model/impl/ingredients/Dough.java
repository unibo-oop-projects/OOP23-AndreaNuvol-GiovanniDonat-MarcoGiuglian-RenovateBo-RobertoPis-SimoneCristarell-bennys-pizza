package it.unibo.model.impl.ingredients;

import it.unibo.model.impl.IngredientImpl;

/**
 * Class that emulates the dough.
 */ 
public class Dough extends IngredientImpl {
    private static final int QUANTITY_TO_REDUCE = 18;
    private static final double PRICE = 0.2;
    private static final String IMAGE_NAME = "Dough.png";

    /**
     * The constructor of the class Dough.
     */
    public Dough() {
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
