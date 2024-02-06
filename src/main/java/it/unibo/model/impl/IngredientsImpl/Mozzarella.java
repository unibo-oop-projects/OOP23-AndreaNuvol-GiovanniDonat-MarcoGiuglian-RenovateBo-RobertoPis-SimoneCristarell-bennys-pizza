package it.unibo.model.impl.IngredientsImpl;

import it.unibo.model.impl.IngredientImpl;

/**
 * Class that emulates the mozzarella cheese
 */
public class Mozzarella extends IngredientImpl{

    private final static int QUANTITY_TO_REDUCE = 10;
    private final static double PRICE = 0.6;
    private final static String IMAGE_NAME = "Mozzarella.png";

    public Mozzarella() {
        super(PRICE, IMAGE_NAME);
    }

    @Override
    public void reduce() {
        super.reduceIngredient(QUANTITY_TO_REDUCE);
    }
    
}