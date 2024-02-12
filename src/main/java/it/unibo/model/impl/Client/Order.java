package it.unibo.model.impl.Client;

import java.util.ArrayList;

import it.unibo.model.impl.Pizza;

public class Order {
    private int id = 0;
    private ArrayList<Pizza> pizzeToOrder;

    public Order(final ArrayList<Pizza> pizzeToOrder){
        this.pizzeToOrder = new ArrayList<>(pizzeToOrder);
        id++;
    }

    public int getOrderId(){
        return this.id;
    }
}
