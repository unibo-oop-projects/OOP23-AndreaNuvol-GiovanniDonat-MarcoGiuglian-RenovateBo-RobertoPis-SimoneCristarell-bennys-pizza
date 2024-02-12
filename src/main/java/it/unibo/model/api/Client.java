package it.unibo.model.api;

import it.unibo.model.impl.Client.ClientImpl;

public interface Client {
    
    ClientImpl.Order order();

    void pay();

}
