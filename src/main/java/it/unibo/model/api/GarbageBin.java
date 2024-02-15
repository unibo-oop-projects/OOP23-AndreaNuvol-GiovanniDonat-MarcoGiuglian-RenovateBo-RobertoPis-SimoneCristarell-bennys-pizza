package it.unibo.model.api;

import it.unibo.model.impl.PizzaFactoryImpl;

/**
 *  Interface of the GarbageBin
 */
public interface GarbageBin {

    void throwPizzaInGarbageBin(PizzaFactoryImpl pizza);
    
}
