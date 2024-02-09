package it.unibo.model.api;

import java.util.Collection;

/**
 * Interface of a pizza factory
 */
public interface PizzaFactory {

    /**
     * It adds an ingredient to the pizza you are preparing.
     * @param ingredientToAdd the ingredient to add
     */
    void addIngredient(Ingredient ingredientToAdd);

    /**
     * @param ingredients the ingredients of the requested pizza
     * @return true if the ingredients of the prepared pizza are the same of the requested one's (i.e. the pizzas 
     * are equal), false otherwise
     */
    boolean equals(Collection<? extends Ingredient> ingredients);

}