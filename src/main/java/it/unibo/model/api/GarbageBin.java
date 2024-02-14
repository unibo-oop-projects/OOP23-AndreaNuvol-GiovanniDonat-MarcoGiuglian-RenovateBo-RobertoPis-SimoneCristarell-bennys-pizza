package it.unibo.model.api;

import it.unibo.model.impl.PreparationZoneImpl;

/**
 *  Interface of the GarbageBin
 */
public interface GarbageBin {

    void throwPizzaInGarbageBin(PreparationZoneImpl resetPizzas);
    
}
