package it.unibo.model.impl;

import it.unibo.model.api.GarbageBin;

public class GarbageBinImpl implements GarbageBin {

    @Override
    public void throwPizzaInGarbageBin(PizzaFactoryImpl pizza) {
        pizza = new PizzaFactoryImpl();
    }

}
