package it.unibo.controller.impl;

import java.lang.reflect.InvocationTargetException;

import it.unibo.controller.api.Controller;
import it.unibo.model.api.PreparationZone;
import it.unibo.model.impl.PreparationZoneImpl;
import it.unibo.model.impl.Management.SubtractorManager;

/**
 * Implementation of the controller interface
 */
public class ControllerImpl implements Controller {
    
    private final SubtractorManager subtractorManager = new SubtractorManager();
    private final PreparationZone preparationZone;
    

    public ControllerImpl() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        this.preparationZone = new PreparationZoneImpl(this.subtractorManager);
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

}
