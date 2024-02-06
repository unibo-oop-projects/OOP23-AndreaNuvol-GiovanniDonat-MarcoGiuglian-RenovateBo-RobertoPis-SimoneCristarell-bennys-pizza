package it.unibo.model.impl.IngredientsImpl;

import it.unibo.model.impl.IngredientImpl;

/**
 * Class that emulates a slice of salami
 */
public class Salami extends IngredientImpl{

    private final static int QUANTITY_TO_REDUCE = 9;
    private final static double PRICE = 1.2;
    private final static String IMAGE_NAME = "Salami.png";

    public Salami() {
        super(PRICE, IMAGE_NAME);
    }

    @Override
    public void reduce() {
        super.reduceIngredient(QUANTITY_TO_REDUCE);
    }
    
}