package it.unibo.model.impl.IngredientsImpl;

import it.unibo.model.impl.IngredientImpl;

/**
 * Class that emulates the cheese called 'Parmesan'
 */
public class Parmesan extends IngredientImpl{

    private final static int QUANTITY_TO_REDUCE = 10;
    private final static double PRICE = 0.8;
    private final static String IMAGE_NAME = "Parmesan.png";

    public Parmesan() {
        super(PRICE, IMAGE_NAME);
    }

    @Override
    public void reduce() {
        super.reduceIngredient(QUANTITY_TO_REDUCE);
    }
    
}