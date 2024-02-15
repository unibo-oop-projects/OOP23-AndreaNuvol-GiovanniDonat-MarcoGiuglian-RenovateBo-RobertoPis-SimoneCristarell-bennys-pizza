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

    private final SubtractorManager subtractor = new SubtractorManager();
    private final PreparationZone preparationZone;

    public ControllerImpl() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        this.preparationZone = new PreparationZoneImpl(this.subtractor);
    }

    @Override
    public PreparationZone getPreparationZone() {
        return this.preparationZone;
    }

    @Override
    public void addIngredient(final String ingredientStringToAdd, final boolean isPizza1, final boolean isASupply) {
        this.preparationZone.actionsOnIngredients(ingredientStringToAdd, isPizza1, isASupply);
    }

}
