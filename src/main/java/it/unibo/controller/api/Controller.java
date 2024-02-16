package it.unibo.controller.api;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.tuple.Pair;

import it.unibo.model.api.PreparationZone;

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
     * It brings back pizza without ingredients
     */
    void throwPizzaInGarbageBin(boolean isPizza1);

    /**
     * It cooks the pizza you have prepared
     */
    void bakingPizza();

    /**
     * It get the total balance
     * @return the total balance
     */
    double getBalanceTot();


    /**
     * It get the day balance
     * @return the day balance
     */
    double getBalanceDay();


    /**
     * It do the order of the client
     * @return a pair of two strings, second string can be empty if the client ordered only 1 pizza
     */
    Pair<String, Optional<String>> order();

    /**
     * It pay the amount to pay
     */
    void pay();

}
