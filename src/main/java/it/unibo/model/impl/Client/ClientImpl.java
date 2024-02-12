package it.unibo.model.impl.Client;

import java.util.*;
import it.unibo.model.api.Client;
import it.unibo.model.api.Menu;
import it.unibo.model.impl.MenuImpl;
import it.unibo.model.impl.Management.AbstractManager;
import it.unibo.model.impl.Management.AdderManager; // The client can only add money to the balance

public class ClientImpl implements Client{

    @Override
    public ClientImpl.Order order() {
        Random random = new Random();
        int max = MenuImpl.getNumPizzasInMenu(), min = 0;
        int indexPizza1 = random.nextInt(max + 1 - min ) + min, indexPizza2 = random.nextInt(max + 1 - min ) + min;
        List<MenuImpl.Pizza> menu = MenuImpl.generateMenu();
        List<String> pizzasToOrder = new ArrayList<>(); 
        
        menu.indexOf(indexPizza1);
        // return new ClientImpl.Order();
    }

    @Override
    public void pay() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'pay'");
    }

    public static class Order {
    
        private final int id;
        private final ArrayList<MenuImpl.Pizza> pizze;
    
        public Order(final int id, final ArrayList<MenuImpl.Pizza> pizze) {
            this.id = id;
            this.pizze = new ArrayList<MenuImpl.Pizza>(pizze);
        }
 
        public int getOrderId(){
            return this.id;
        }

        public ArrayList<MenuImpl.Pizza> getOrderPizzas(){
            return pizze;
        }
        
    }
    
}
