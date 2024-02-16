package it.unibo;

import org.junit.jupiter.api.Test;

import it.unibo.model.api.PizzaFactory;
import it.unibo.model.impl.GarbageBinImpl;
import it.unibo.model.impl.PizzaFactoryImpl;

public class TestGarbageBin {
    
        // can throw a pizza in the garbage bin
    @Test
    public void testThrowPizzaInGarbageBin() {
        GarbageBinImpl garbageBin = new GarbageBinImpl();
        PizzaFactory pizzaFactory = new PizzaFactoryImpl();
        garbageBin.throwPizzaInGarbageBin(pizzaFactory);
    }
}
