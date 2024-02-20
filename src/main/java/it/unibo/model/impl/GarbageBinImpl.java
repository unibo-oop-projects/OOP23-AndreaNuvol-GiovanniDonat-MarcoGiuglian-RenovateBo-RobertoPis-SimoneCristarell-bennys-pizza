package it.unibo.model.impl;

import it.unibo.model.api.GarbageBin;
import it.unibo.model.api.PreparationZone;

/**
 * Implementation of the class for managing the pizzeria basket
 */
public class GarbageBinImpl implements GarbageBin {
    @Override
    public void throwPizzaInGarbageBin(final PreparationZone zone, final boolean isPizza1) {
        zone.resetPizza(isPizza1);
    }
}
