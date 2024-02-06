package it.unibo.model.impl.IngredientsImpl;

import it.unibo.model.impl.IngredientImpl;

/**
 * Class that emulates the cheese called 'Fontina'
 */
public class Fontina extends IngredientImpl{

    private final static int QUANTITY_TO_REDUCE = 10;
    private final static double PRICE = 1;
    private final static String IMAGE_NAME = "Fontina.png";

    public Fontina() {
        super(PRICE, IMAGE_NAME);
    }

    @Override
    public void reduce() {
        super.reduceIngredient(QUANTITY_TO_REDUCE);
    }
    
}