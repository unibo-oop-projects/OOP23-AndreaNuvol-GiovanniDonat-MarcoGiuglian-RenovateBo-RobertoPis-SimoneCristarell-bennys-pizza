package it.unibo.model.api;

/**
 *  Interface of the GarbageBin.
 */
public interface GarbageBin {
    /**
     * simulate throwing pizza in the bin.
     * @param zone the zone where you are working.
     * @param isPizza1 true if the pizza to throw in the bin is the n.1 , false otherwise.
     */
    void throwPizzaInGarbageBin(PreparationZone zone, boolean isPizza1);
}
