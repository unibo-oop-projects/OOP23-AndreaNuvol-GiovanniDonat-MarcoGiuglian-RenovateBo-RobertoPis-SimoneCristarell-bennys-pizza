package it.unibo.model.impl.IngredientsImpl;

import it.unibo.model.impl.IngredientImpl;

/**
 * Class that emulates the cheese called 'Parmesan'
 */
public class Parmesan extends IngredientImpl{

    private final static int QUANTITY_TO_REDUCE = 10;
    private final static double PRICE = 0.8;
    private final static String IMAGE_PATH = "";

    public Parmesan() {
        super(PRICE, IMAGE_PATH);
    }

    public void reduce() {
        super.reduce(QUANTITY_TO_REDUCE);
    }
    
}