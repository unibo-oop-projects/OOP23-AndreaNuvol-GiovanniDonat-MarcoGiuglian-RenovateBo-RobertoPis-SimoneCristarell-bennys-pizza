package it.unibo.controller.api;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.tuple.Pair;

import it.unibo.controller.impl.ClientThread;
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
     * It gets the total balance
     * @return the total balance
     */
    double getBalanceTot();


    /**
     * It gets the day balance
     * @return the day balance
     */
    double getBalanceDay();


    /**
     * It adds the amount to balance
     * @param amount the value to add
     */
    void addToBalance(double amount);


    /**
     * It subtracts the amount to balance
     * @param amount the value to subtract
     */
    void subtractToBalance(double amount);

    /**
     * It does the order of the client
     * @return a pair of two strings, second string can be empty if the client ordered only 1 pizza
     */
    Pair<String, Optional<String>> order();

    /**
     * It pays the amount to pay
     */
    void pay();

    /*
     * It generates the menu as list of class Pizza 
     */
    void generateMenu();

    /**
     * @return a list of string with name, ingredients and cost for each pizza in menu
     */
    public List<String> getMenu();

    
    /**
     * @return the thread of the client
     */
    public ClientThread getClientThread();
}
