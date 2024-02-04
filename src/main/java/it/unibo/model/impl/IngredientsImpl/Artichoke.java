package it.unibo.model.impl.IngredientsImpl;

import it.unibo.model.impl.IngredientImpl;

/**
 * Class that emulates an artichoke
 */
public class Artichoke extends IngredientImpl{

    private final static int QUANTITY_TO_REDUCE = 6;
    private final static double PRICE = 1.1;
    private final static String IMAGE_PATH = "";

    public Artichoke() {
        super(PRICE, IMAGE_PATH);
    }

    public void reduce() {
        super.reduce(QUANTITY_TO_REDUCE);
    }
    
}