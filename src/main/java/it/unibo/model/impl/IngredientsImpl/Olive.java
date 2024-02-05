package it.unibo.model.impl.IngredientsImpl;

import it.unibo.model.impl.IngredientImpl;

/**
 * Class that emulates an olive
 */
public class Olive extends IngredientImpl{

    private final static int QUANTITY_TO_REDUCE = 15;
    private final static double PRICE = 1;
    private final static String IMAGE_PATH = "";

    public Olive() {
        super(PRICE, IMAGE_PATH);
    }

    @Override
    public void reduce() {
        super.reduceIngredient(QUANTITY_TO_REDUCE);
    }
    
}