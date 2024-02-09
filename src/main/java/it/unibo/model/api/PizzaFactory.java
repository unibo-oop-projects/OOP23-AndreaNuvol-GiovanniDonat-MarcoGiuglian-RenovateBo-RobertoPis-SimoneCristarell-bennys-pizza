package it.unibo.model.api;

import java.util.List;

import it.unibo.model.impl.IngredientImpl;

/**
 * Interface of a pizza factory
 */
public interface PizzaFactory {

    /**
     * It adds an ingredient to the pizza you are preparing.
     * @param ingredientToAdd the ingredient to add
     */
    void addIngredient(IngredientImpl ingredientToAdd);

    /**
     * @param requestedIngredients the ingredients of the requested pizza
     * @return true if the ingredients of the prepared pizza are the same of the requested one's (i.e. the pizzas 
     * are equal), false otherwise
     */
    boolean equals(List<IngredientImpl> requestedIngredients);

    /**
     * @return a list of the current ingredients added to the pizza you are preparing 
     */
    List<IngredientImpl> getAddedIngredients();

}