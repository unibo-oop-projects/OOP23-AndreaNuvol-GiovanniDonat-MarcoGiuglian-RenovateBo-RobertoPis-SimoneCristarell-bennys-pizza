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
    private final ClientThread clientThread;
    
    public ControllerImpl() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        this.preparationZone = new PreparationZoneImpl(this.subtractorManager);
        this.clientThread = new ClientThread(this);
        this.clientThread.start();
        MenuImpl.generateMenu();
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
}
