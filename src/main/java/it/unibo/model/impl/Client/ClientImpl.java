package it.unibo.model.impl.Client;

import it.unibo.model.api.Client;
import it.unibo.model.impl.Management.AbstractManager;
import it.unibo.model.impl.Management.AdderManager; // The client can only add money to the balance

public class ClientImpl implements Client{

    @Override
    public void order() {
        // creare ordine prendendo 2 nomi di pizze in modo randomico dal menu
    }

    @Override
    public void pay() {
        // se la pizza va bene paga il prezzo della pizza
        AbstractManager manager = new AdderManager();
    }
    
}
