package it.unibo.model.impl.IngredientsImpl;

import it.unibo.model.impl.IngredientImpl;

/**
 * Class that emulates a cherry tomatoe
 */
public class CherryTomatoe extends IngredientImpl{

    private final static int QUANTITY_TO_REDUCE = 8;
    private final static double PRICE = 0.9;
    private final static String IMAGE_PATH = "";

    public CherryTomatoe() {
        super(PRICE, IMAGE_PATH);
    }

    public void reduce() {
        super.reduce(QUANTITY_TO_REDUCE);
    }
    
}