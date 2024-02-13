package it.unibo.model.impl.IngredientsImpl;

import it.unibo.model.impl.IngredientImpl;

/**
 * Class that emulates an onion
 */
public class Onion extends IngredientImpl{

    private final static int QUANTITY_TO_REDUCE = 8;
    private final static double PRICE = 1;
    private final static String IMAGE_NAME = "Onions.png";

    public Onion() {
        super(PRICE, IMAGE_NAME);
    }

    @Override
    public void reduce() {
        super.reduceIngredient(QUANTITY_TO_REDUCE);
    }
    
}