package it.unibo.model.api;

/**
 * Interface of the Oven.
 */
public interface Oven {
    /**
     * this method check if Oven is empty.
     * @return true if oven is empty.
     */
    boolean isOvenEmpty();

    /**
     * simulate the pizza baking time in the oven.
     */
    void bakingPizza();

    /** 
     * this method check if pizza is cooked.
     * @return ture if pizza is cooked.
     */
    boolean isPizzaCooked();
}
