package it.unibo.model.impl.IngredientsImpl;

import it.unibo.model.impl.IngredientImpl;

/**
 * Class that emulates a french fry
 */
public class FrenchFry extends IngredientImpl{

    private final static int QUANTITY_TO_REDUCE = 17;
    private final static double PRICE = 1.5;
    private final static String IMAGE_NAME = "FrenchFries.png";

    public FrenchFry() {
        super(PRICE, IMAGE_NAME);
    }

    @Override
    public void reduce() {
        super.reduceIngredient(QUANTITY_TO_REDUCE);
    }
    
}