package it.unibo.model.api;

import java.util.Optional;

import it.unibo.model.impl.ClientImpl;
import it.unibo.model.impl.PizzaFactoryImpl;

/**
 * Interface of Client.
 */
public interface Client {
    /**
     * It create the order of the client.
     * @return the order made up of the list of pizzas.
     */
    ClientImpl.Order order();

    /**
     * It adds money to the balance based on the cost of the pizza and the quality with which it was made.
     * @param pizzaFactoryImpl1
     * @param pizzaFactoryImpl2
     */
    void pay(PizzaFactoryImpl pizzaFactoryImpl1, Optional<PizzaFactoryImpl> pizzaFactoryImpl2);
}
