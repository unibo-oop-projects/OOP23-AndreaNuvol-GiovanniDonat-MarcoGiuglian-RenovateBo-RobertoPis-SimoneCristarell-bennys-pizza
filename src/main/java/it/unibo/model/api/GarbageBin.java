package it.unibo.model.api;

/**
 *  Interface of the GarbageBin
 */
public interface GarbageBin {

    /**
     * simulate throwing pizza in the bin
     * @param pizza the pizza that needs to be thrown away
     */
    void throwPizzaInGarbageBin(PizzaFactory pizza);
    
}
