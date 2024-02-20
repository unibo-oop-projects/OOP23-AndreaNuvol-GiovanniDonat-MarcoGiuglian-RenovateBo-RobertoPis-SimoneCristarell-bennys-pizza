package it.unibo.model.impl;

import it.unibo.model.api.GarbageBin;
import it.unibo.model.api.PreparationZone;

/**
 * Implementation of the garbage bin's interface.
 */
public class GarbageBinImpl implements GarbageBin {
    /**
     * It throws the pizza in the bin.
     */
    @Override
    public void throwPizzaInGarbageBin(final PreparationZone zone, final boolean isPizza1) {
        zone.resetPizza(isPizza1);
    }
}
