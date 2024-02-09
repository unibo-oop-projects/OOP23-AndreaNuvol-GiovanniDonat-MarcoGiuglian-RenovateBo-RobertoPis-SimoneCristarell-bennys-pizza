package it.unibo.model.impl.IngredientsImpl;

import it.unibo.model.impl.IngredientImpl;

/**
 * Class that emulates a mushroom
 */
public class Mushroom extends IngredientImpl{

    private final static int QUANTITY_TO_REDUCE = 13;
    private final static double PRICE = 1.4;
    private final static String IMAGE_NAME = "Mushrooms.png";

    public Mushroom() {
        super(PRICE, IMAGE_NAME);
    }

    @Override
    public void reduce() {
        super.reduceIngredient(QUANTITY_TO_REDUCE);
    }
    
}