package it.unibo.controller.api;

import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.tuple.Pair;

import it.unibo.controller.impl.ClientThread;
import it.unibo.model.api.PreparationZone;
import it.unibo.model.impl.TimeImpl;
import it.unibo.model.impl.MenuImpl.Pizza;
import it.unibo.model.impl.management.AdderManager;
import it.unibo.model.impl.management.SubtractorManager;

/**
 * Controller interface.
 */
public interface Controller {

    /**
     * It adds an ingredient to the pizza you are preparing.
     * @param ingredientStringToAdd the string of the ingredient to add.
     * @param isPizza1 true if you are preparing pizza n. 1, false otherwise.
     */
    void addIngredient(String ingredientStringToAdd, boolean isPizza1);

    /**
     * It supplies an ingredient.
     * @param ingredientStringToAdd the ingredient to supply.
     */
    void supply(String ingredientStringToAdd);

    /**
     * It gets the preparation zone where you are working.
     * @return the preparation zone where you are working.
     */
    PreparationZone getPreparationZone();

    /**
     * @param ingredientName the ingredient of which we want the quantity.
     * @return the quantity of the ingredient requested.
     */
    int getIngredientQuantity(String ingredientName);

    /**
     * It brings back pizza without ingredients.
     * @param isPizza1 true if the pizza to throw is the n. 1, false otherwise
     */
    void throwPizzaInGarbageBin(boolean isPizza1);

    /**
     * It start the thread of the client.
     */
    void startClientThread();

    /**
     * It cooks the pizza you have prepared.
     */
    void bakingPizza();

    /**
     * It resets the two pizzas before you can prepare others.
     */
    void resetPizzas();

    /**
     * It gets the day balance.
     * @return the day balance.
     */
    double getBalanceDay();

    /**
     * It adds the amount to balance.
     * @param amount the value to add.
     */
    void addToBalance(double amount);

    /**
     * It subtracts the amount to balance.
     * @param amount the value to subtract.
     */
    void subtractToBalance(double amount);

    /**
     * @return the adderManager to add money to balance.
     */
    AdderManager getAdderManagerModel();

    /**
     * @return the subtractorManager to subtract money to balance.
     */
    SubtractorManager getSubtractorManagerModel();

    /**
     * It does the order of the client.
     * @return a pair of two strings, second string can be empty if the client ordered only 1 pizza.
     */
    Pair<Pizza, Optional<Pizza>> order();

    /**
     * It pays the amount to pay.
     */
    void pay();

    /**
     * It generates the menu as list of class Pizza.
     */
    void generateMenu();

    /**
     * @return a list of string with name, ingredients and cost for each pizza in menu.
     */
    List<String> getMenu();

    /**
     * @return the thread of the client.
     */
    ClientThread getClientThread();

    /**
     * @return a string to show hour and minutes of the day.
     */
    String getHourAndMin();

    /**
     * @return the model of time.
     */
    TimeImpl getTimeModel();

    /**
     * It allows to update the view when the model changes.
     * @param pcl the property change listener
     */
    void addPropertyChangeListener(PropertyChangeListener pcl);

    /**
     * It sets everything for a new day.
     */
    void newDay();

    /**
     * @return a string that describe the result of the game.
     */
    String getResult();
}
