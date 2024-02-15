package it.unibo.controller.api;

import it.unibo.model.api.PreparationZone;

/**
 * Controller interface
 */
public interface Controller {
    
    /**
     * It adds an ingredient to the pizza you are preparing
     * @param ingredientStringToAdd the string of the ingredient to add
     * @param isPizza1 true if you are preparing pizza n. 1, false otherwise
     * @param isASupply true if you are supplying an ingredient, false otherwise
     */
    void addIngredient(String ingredientStringToAdd, boolean isPizza1, boolean isASupply);

    /**
     * It gets the preparation zone where you are working
     * @return the preparation zone where you are working
     */
    PreparationZone getPreparationZone();

}
