package it.unibo.controller.api;

import it.unibo.model.api.PreparationZone;
import it.unibo.model.impl.PizzaFactoryImpl;

/**
 * Controller interface
 */
public interface Controller {
    
    /**
     * It adds an ingredient to the pizza you are preparing
     * @param ingredientStringToAdd the string of the ingredient to add
     * @param isPizza1 true if you are preparing pizza n. 1, false otherwise
     */
    void addIngredient(String ingredientStringToAdd, boolean isPizza1);

    /**
     * It supplies an ingredient
     * @param ingredientStringToAdd the ingredient to supply
     */
    void supply(String ingredientStringToAdd);

    /**
     * It cleans the preparation zone
     */
    void clean();

    /**
     * It gets the preparation zone where you are working
     * @return the preparation zone where you are working
     */
    PreparationZone getPreparationZone();

    /**
     * @param ingredientName the ingredient of which we want the quantity 
     * @return the quantity of the ingredient requested
     */
    int getIngredientQuantity(String ingredientName);

    /**
     * It brings back pizza without ingredients
     */
    void throwPizzaInGarbageBin(PizzaFactoryImpl PizzaFactory);

    /**
     * It cooks the pizza you have prepared
     */
    void bakingPizza();

}
