package it.unibo.model.impl.Client;

import java.util.*;
import it.unibo.model.api.Client;
import it.unibo.model.api.Menu;
import it.unibo.model.impl.MenuImpl;
import it.unibo.model.impl.Management.AbstractManager;
import it.unibo.model.impl.Management.AdderManager; // The client can only add money to the balance

public class ClientImpl implements Client{
    private int orderId = 0;
    private Random random = new Random();

    @Override
    public ClientImpl.Order order() {
        final int max = MenuImpl.getNumPizzasInMenu() - 1, min = 0;
        final List<MenuImpl.Pizza> menu = MenuImpl.generateMenu();
        List<String> pizzasToOrder = new ArrayList<>();
        for(int i = 0; i < nPizzeToOrder(); i++){
            final int indexPizza = random.nextInt(max + 1 - min ) + min;
            pizzasToOrder.add(menu.get(indexPizza).getName());
        }
        return new Order(orderId++, pizzasToOrder);
    }

    @Override
    public void pay() {
        
    }

    private int nPizzeToOrder(){
        final int min = 1, max = 2;
        return random.nextInt(max + 1 - min) + min;
    }

    public static class Order {
    
        private final int id;
        private final ArrayList<String> pizze;
    
        public Order(final int id, final List<String> pizze) {
            this.id = id;
            this.pizze = new ArrayList<String>(pizze);
        }
 
        public int getOrderId(){
            return this.id;
        }

        public ArrayList<String> getOrderPizzas(){
            return pizze;
        }
        
    }
    
}
