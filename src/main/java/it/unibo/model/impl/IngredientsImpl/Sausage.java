package it.unibo.model.impl.IngredientsImpl;

import it.unibo.model.impl.IngredientImpl;

/**
 * Class that emulates a piece of sausage
 */
public class Sausage extends IngredientImpl{

    private final static int QUANTITY_TO_REDUCE = 14;
    private final static double PRICE = 1.4;
    private final static String IMAGE_NAME = "Sausages.png";

    public Sausage() {
        super(PRICE, IMAGE_NAME);
    }

    @Override
    public void reduce() {
        super.reduceIngredient(QUANTITY_TO_REDUCE);
    }
    
}