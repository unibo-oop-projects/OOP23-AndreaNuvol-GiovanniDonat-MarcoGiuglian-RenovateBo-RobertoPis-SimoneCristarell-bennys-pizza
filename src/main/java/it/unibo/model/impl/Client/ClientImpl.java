package it.unibo.model.impl.Client;

import java.util.*;
import it.unibo.model.api.Client;
import it.unibo.model.api.Ingredient;
import it.unibo.model.api.Menu;
import it.unibo.model.impl.Menu.MenuImpl;
import it.unibo.model.impl.Menu.MenuImpl.Pizza;
import it.unibo.model.impl.PizzaFactoryImpl;
import it.unibo.model.impl.Management.AbstractManager;
import it.unibo.model.impl.Management.AdderManager; // The client can only add money to the balance

import org.apache.commons.lang3.tuple.Pair;

public class ClientImpl implements Client{

    private int orderId = 0;
    private Random random = new Random();
    private Order order;

    @Override
    public ClientImpl.Order order() {
        final Pair<MenuImpl.Pizza, Optional<MenuImpl.Pizza>> pizzasToOrder;
        final int max = MenuImpl.getNumPizzasInMenu() - 1, min = 0;
        final List<MenuImpl.Pizza> menu = MenuImpl.generateMenu();
        MenuImpl.Pizza pizza1 = null;
        Optional<MenuImpl.Pizza> pizza2 = null;
        for(int i = 0; i < nPizzeToOrder(); i++){
            final int indexPizza = random.nextInt(max + 1 - min ) + min;
            pizza1 = menu.get(indexPizza);
            if(nPizzeToOrder() == 1){
                pizza2 = Optional.empty();
            }else{
                pizza2 = Optional.of(menu.get(indexPizza));
            }
        }
        pizzasToOrder = Pair.of(pizza1, pizza2);
        this.order = new Order(orderId++, pizzasToOrder);
        return this.order;
    }

    @Override
    public void pay(final PizzaFactoryImpl pizzaFactoryImpl1, final Optional<PizzaFactoryImpl> pizzaFactoryImpl2) {
        final Pair<MenuImpl.Pizza, Optional<MenuImpl.Pizza>> pizzas = this.order.getOrderPizzas();
        final AbstractManager manager = new AdderManager();
        double amountToAdd = 0;
        // controllo prima pizza
        if(pizzaFactoryImpl1.equals(pizzas.getLeft().getIngredients())){
            amountToAdd = pizzas.getLeft().getCost();
        }else{
            // se le pizze non sono esattemente uguali mi faccio pagare gli ingredienti che sono corretti
            for(final Ingredient ingredient : pizzaFactoryImpl1.getAddedIngredients()){
                if(pizzas.getLeft().getIngredients().contains(ingredient)){
                    amountToAdd += ingredient.getPrice();
                }
            }
        }
        manager.updateBalance(amountToAdd);
        amountToAdd = 0;
        // controllo seconda pizza se c'è
        if(pizzas.getRight().isPresent()){
            if(pizzaFactoryImpl2.equals(pizzas.getRight().get().getIngredients())){
                amountToAdd = pizzas.getRight().get().getCost();
            }else{
                for(final Ingredient ingredient : pizzaFactoryImpl2.get().getAddedIngredients()){
                    if(pizzas.getRight().get().getIngredients().contains(ingredient)){
                        amountToAdd += ingredient.getPrice();
                    }
                }
            }
        }
        manager.updateBalance(amountToAdd);
    }

    private int nPizzeToOrder(){
        final int min = 1, max = 2;
        return random.nextInt(max + 1 - min) + min;
    }

    public static class Order {
    
        private final int id;
        private Pair<MenuImpl.Pizza, Optional<MenuImpl.Pizza>> pizze;
    
        public Order(final int id, final Pair<MenuImpl.Pizza, Optional<MenuImpl.Pizza>> pizze) {
            this.id = id;
            this.pizze = pizze;
        }
 
        public int getOrderId(){
            return this.id;
        }

        public Pair<MenuImpl.Pizza, Optional<MenuImpl.Pizza>> getOrderPizzas(){
            return this.pizze;
        }
        
    }
    
}
