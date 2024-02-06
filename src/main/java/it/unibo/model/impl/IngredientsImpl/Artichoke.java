package it.unibo.model.impl.IngredientsImpl;

import it.unibo.model.impl.IngredientImpl;

/**
 * Class that emulates an artichoke
 */
public class Artichoke extends IngredientImpl{

    private final static int QUANTITY_TO_REDUCE = 6;
    private final static double PRICE = 1.1;
    private final static String IMAGE_NAME = "Artichokes.png";

    public Artichoke() {
        super(PRICE, IMAGE_NAME);
    }

    @Override
    public void reduce() {
        super.reduceIngredient(QUANTITY_TO_REDUCE);
    }
    
}