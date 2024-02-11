package it.unibo.model.impl.IngredientsImpl;

import it.unibo.model.impl.IngredientImpl;

/**
 * Class that emulates an anchovy
 */
public class Anchovy extends IngredientImpl{

    private final static int QUANTITY_TO_REDUCE = 5;
    private final static double PRICE = 1.2;
    private final static String IMAGE_NAME = "Anchovies.png";

    public Anchovy() {
        super(PRICE, IMAGE_NAME);
    }

    @Override
    public void reduce() {
        super.reduceIngredient(QUANTITY_TO_REDUCE);
    }
    
}