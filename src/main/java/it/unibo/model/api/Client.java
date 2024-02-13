package it.unibo.model.api;

import java.util.Optional;

import it.unibo.model.impl.PizzaFactoryImpl;
import it.unibo.model.impl.Client.ClientImpl;

public interface Client {
    
    
    /**
     * It create the order of the client
     * @return the order made up of the list of pizzas
     */
    ClientImpl.Order order();

    /**
     * It adds money to the balance based on the cost of the pizza and the quality with which it was made
     */
    void pay(final PizzaFactoryImpl pizzaFactoryImpl1, final Optional<PizzaFactoryImpl> pizzaFactoryImpl2);

}
