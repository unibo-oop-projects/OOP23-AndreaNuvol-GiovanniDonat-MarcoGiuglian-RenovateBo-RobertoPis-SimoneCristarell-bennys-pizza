package it.unibo.model.impl;

import it.unibo.model.api.GarbageBin;
import it.unibo.model.api.PreparationZone;

public class GarbageBinImpl implements GarbageBin {

    @Override
    public void throwPizzaInGarbageBin(final PreparationZone zone, final boolean isPizza1) {
        zone.resetPizza(isPizza1);
    }

}
