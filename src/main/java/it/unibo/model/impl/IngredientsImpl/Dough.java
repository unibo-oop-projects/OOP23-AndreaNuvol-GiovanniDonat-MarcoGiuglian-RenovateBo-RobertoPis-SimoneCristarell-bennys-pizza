package it.unibo.model.impl.IngredientsImpl;

import it.unibo.model.impl.IngredientImpl;

/**
 * Class that emulates the dough
 */
public class Dough extends IngredientImpl{

    private final static int QUANTITY_TO_REDUCE = 18;
    private final static double PRICE = 0.2;
    private final static String IMAGE_NAME = "Dough.png";

    public Dough() {
        super(PRICE, IMAGE_NAME);
    }

    @Override
    public void reduce() {
        super.reduceIngredient(QUANTITY_TO_REDUCE);
    }
    
}