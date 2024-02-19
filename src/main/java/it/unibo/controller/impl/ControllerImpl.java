package it.unibo.controller.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import it.unibo.controller.api.Controller;
import it.unibo.model.api.Client;
import it.unibo.model.api.PreparationZone;
import it.unibo.model.impl.PreparationZoneImpl;
import it.unibo.model.impl.Client.ClientImpl;
import it.unibo.model.impl.Management.AdderManager;
import it.unibo.model.impl.Management.SubtractorManager;
import it.unibo.model.impl.Menu.MenuImpl;
import it.unibo.model.impl.Menu.MenuImpl.Pizza;
import it.unibo.model.impl.Time.TimeImpl;

import java.beans.*;
import org.apache.commons.lang3.tuple.Pair;

/**
 * Implementation of the controller interface
 */
public class ControllerImpl implements Controller {
    
    private final SubtractorManager subtractorManager = new SubtractorManager();
    private final AdderManager adderManager = new AdderManager();
    private final PreparationZone preparationZone;
    private final Client client = new ClientImpl();
    private ClientImpl.Order order;
    private TimeImpl time = new TimeImpl();
    private final ClientThread clientThread;
    private PropertyChangeSupport support;
    private PropertyChangeSupport propertyChangeSupport;
    
    public ControllerImpl() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        this.preparationZone = new PreparationZoneImpl(this.subtractorManager);
        MenuImpl.generateMenu();
        this.propertyChangeSupport = new PropertyChangeSupport(this);
        this.clientThread = new ClientThread(this);
        this.clientThread.start();
    }

    protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
    }

    @Override
    public ClientThread getClientThread(){
        return this.clientThread;
    }

    @Override
    public PreparationZone getPreparationZone() {
        return this.preparationZone;
    }

    @Override
    public void addIngredient(final String ingredientStringToAdd, final boolean isPizza1) {
        this.preparationZone.actionsOnIngredients(ingredientStringToAdd, isPizza1, false);
    }

    @Override
    public void supply(final String ingredientStringToAdd) {
        this.preparationZone.actionsOnIngredients(ingredientStringToAdd, false, true);
    }

    @Override
    public void clean() {
        this.preparationZone.getCleaner().clean(this.preparationZone);
    }

    @Override
    public void throwPizzaInGarbageBin(final boolean isPizza1) {
        this.preparationZone.getGarbageBin().throwPizzaInGarbageBin(this.preparationZone, isPizza1);
    }

    @Override
    public void bakingPizza() {
        this.preparationZone.getOven().bakingPizza();
    }

    @Override
    public int getIngredientQuantity(String ingredientName) {
        for (final var ingredient: this.preparationZone.getIngredientsQuantities().keySet()) {
            if (ingredient.toString().equals(ingredientName)) {
                return ingredient.getQuantity();
            }
        }
        return -1;
    }

    @Override
    public double getBalanceTot() {
        return this.subtractorManager.getBalanceTot();
    }

    @Override
    public double getBalanceDay() {
        return this.subtractorManager.getBalanceDay();
    }

    @Override
    public void addToBalance(final double amount) {
        this.adderManager.updateBalance(amount);
    }

    @Override
    public void subtractToBalance(final double amount) {
        this.subtractorManager.updateBalance(amount);
    }

    @Override
    public AdderManager getAdderManagerModel() {
        return this.adderManager;
    }

    @Override
    public SubtractorManager getSubtractorManagerModel() {
        return this.subtractorManager;
    }

    @Override
    public Pair<String, Optional<String>> order() {
        Pair<String, Optional<String>> orderedPizzas;
        order = this.client.order();
        if(order.getNumberPizzasToOrder() == 1){
            orderedPizzas = Pair.of(order.getOrderPizzas().getLeft().getName(), Optional.empty());
        }else{
            orderedPizzas = Pair.of(order.getOrderPizzas().getLeft().getName(), Optional.of(order.getOrderPizzas().getRight().get().getName()));
        }
        return orderedPizzas;
    }

    @Override
    public void pay() {
        this.client.pay(this.preparationZone.getPizza1(), Optional.of(this.preparationZone.getPizza2()));
    }

    @Override
    public void generateMenu(){
        MenuImpl.generateMenu();
    }

    @Override
    public List<String> getMenu() {
        List<String> menu = new ArrayList<>();
        for(final Pizza pizza : MenuImpl.getPizzas()) {
            menu.add(pizza.toString());
        }
        return menu;
    }

    @Override
    public int getWorkingDay() {
        return TimeImpl.getWorkingDay();
    }

    @Override
    public String getHourAndMin() {
        return time.getHourAndMin();
    }

    @Override
    public TimeImpl getTimeModel() {
        return this.time;
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    @Override
    public void newDay() {
        this.time.newDay();
    }

}
