package it.unibo.model.impl.Client;

import it.unibo.model.api.Client;
import it.unibo.model.api.Menu;
import it.unibo.model.impl.MenuImpl;
import it.unibo.model.impl.Management.AbstractManager;
import it.unibo.model.impl.Management.AdderManager; // The client can only add money to the balance

public class ClientImpl implements Client{

    @Override
    public void order() {
        // creare ordine prendendo 2 nomi di pizze in modo randomico dal menu
        // prendo il menu
        Menu menu = new MenuImpl();
        
        
    }

    @Override
    public void pay() {
        // se la pizza va bene paga il prezzo della pizza
        AbstractManager manager = new AdderManager();
    }

    public static void main(String[] args) {
        MenuImpl menu = new MenuImpl();
        // menu.generateMenu();
        menu.show();
    }
    
}
