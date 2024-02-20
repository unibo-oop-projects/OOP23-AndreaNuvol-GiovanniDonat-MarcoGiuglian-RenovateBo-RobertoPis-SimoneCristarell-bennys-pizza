package it.unibo.model.impl.IngredientsImpl;

import it.unibo.model.impl.IngredientImpl;

/**
 * Class that emulates the tomato sauce.
 */
public class TomatoSauce extends IngredientImpl {
    private static final int QUANTITY_TO_REDUCE = 12;
    private static final double PRICE = 0.4;
    private static final String IMAGE_NAME = "TomatoeSauce.png";

    /**
     * The constructor of the class TomatoSauce.
     */
    public TomatoSauce() {
        super(PRICE, IMAGE_NAME);
    }

    @Override
    public void reduce() {
        super.reduceIngredient(QUANTITY_TO_REDUCE);
    }
}
