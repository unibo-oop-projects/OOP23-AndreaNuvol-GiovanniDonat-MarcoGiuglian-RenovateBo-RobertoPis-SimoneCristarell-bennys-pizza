package it.unibo.model.impl;

import it.unibo.model.api.GarbageBin;
import it.unibo.model.api.PizzaFactory;

public class GarbageBinImpl implements GarbageBin {

    @Override
    public void throwPizzaInGarbageBin(PizzaFactory pizza) {
        pizza = new PizzaFactoryImpl();
    }

}
