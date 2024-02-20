package it.unibo.model.impl.IngredientsImpl;

import it.unibo.model.impl.IngredientImpl;

/**
 * Class that emulates an anchovy.
 */
public class Anchovy extends IngredientImpl {
    private static final int QUANTITY_TO_REDUCE = 5;
    private static final double PRICE = 1.2;
    private static final String IMAGE_NAME = "Anchovies.png";

    /**
     * The constructor of the class Anchovy.
     */
    public Anchovy() {
        super(PRICE, IMAGE_NAME);
    }

    @Override
    public void reduce() {
        super.reduceIngredient(QUANTITY_TO_REDUCE);
    }  
}
