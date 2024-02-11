package it.unibo.model.api;

/**
 * Interface of a pizza factory
 */
public interface PizzaFactory {

    /**
     * It adds an ingredient to the pizza you are preparing.
     * @param ingredientToAdd the ingredient to add
     */
    void addIngredient(Ingredient ingredientToAdd);

}