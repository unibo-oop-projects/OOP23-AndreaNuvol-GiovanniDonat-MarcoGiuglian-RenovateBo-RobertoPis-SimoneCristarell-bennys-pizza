package it.unibo.model.impl;

import java.util.Random;
import java.util.Optional;
import java.util.List;
import it.unibo.model.api.Client;
import it.unibo.model.api.Ingredient;
import it.unibo.model.impl.management.AdderManager;

import org.apache.commons.lang3.tuple.Pair;

/**
 * The implementation of a normal client.
 */
public class ClientImpl implements Client {
    private final Random random = new Random();
    private Order order;
    /**
     * Contructor of ClientImpl.
     */
    public ClientImpl() {
        order = null;
    }

    /**
     * This method makes the client's order.
     */
    @Override
    public ClientImpl.Order order() {
        final Pair<MenuImpl.Pizza, Optional<MenuImpl.Pizza>> pizzasToOrder;
        final int max = MenuImpl.getNumPizzasInMenu() - 1, min = 0;
        final List<MenuImpl.Pizza> menu = MenuImpl.getPizzas();
        MenuImpl.Pizza pizza1 = null;
        Optional<MenuImpl.Pizza> pizza2 = Optional.empty();
        for (int i = 0; i < nPizzeToOrder(); i++) {
            int indexPizza = random.nextInt(max + 1 - min) + min;
            pizza1 = menu.get(indexPizza);
            if (i == 1) {
                indexPizza = random.nextInt(max + 1 - min) + min;
                pizza2 = Optional.of(menu.get(indexPizza));
            }
        }
        pizzasToOrder = Pair.of(pizza1, pizza2);
        this.order = new Order(pizzasToOrder);
        return this.order;
    }

    /**
     * The client pays.
     */
    @Override
    public void pay(final PizzaFactoryImpl pizzaFactoryImpl1, final Optional<PizzaFactoryImpl> pizzaFactoryImpl2) {
        final Pair<MenuImpl.Pizza, Optional<MenuImpl.Pizza>> pizzas = this.order.getOrderPizzas();
        final AdderManager manager = new AdderManager();
        double amountToAdd = 0;
        if (pizzaFactoryImpl1.isEqual(pizzas.getLeft().getIngredients())) {
            amountToAdd = pizzas.getLeft().getCost();
        } else if (pizzas.getRight().isPresent() && pizzaFactoryImpl1.isEqual(pizzas.getRight().get().getIngredients())) {
            amountToAdd = pizzas.getRight().get().getCost();
        } else {
            for (final Ingredient ingredient : pizzaFactoryImpl1.getAddedIngredients()) {
                if (pizzas.getLeft().getIngredients().contains(ingredient.toString())) {
                    amountToAdd += ingredient.getPrice();
                }
            }
        }
        manager.updateBalance(amountToAdd);
        amountToAdd = 0;
        if (pizzas.getRight().isPresent()) {
            if (pizzaFactoryImpl2.get().isEqual(pizzas.getLeft().getIngredients())) {
                amountToAdd = pizzas.getLeft().getCost();
            } else if (pizzas.getRight().isPresent() 
                    && pizzaFactoryImpl2.get().isEqual(pizzas.getRight().get().getIngredients())) {
                amountToAdd = pizzas.getRight().get().getCost();
            } else {
                for (final Ingredient ingredient : pizzaFactoryImpl2.get().getAddedIngredients()) {
                    if (pizzas.getRight().get().getIngredients().contains(ingredient.toString())) {
                        amountToAdd += ingredient.getPrice();
                    }
                }
            }
        }
        manager.updateBalance(amountToAdd);
    }

    /**
     * @return the number of pizzas in the order.
     */
    private int nPizzeToOrder() {
        final int min = 1, max = 2;
        return random.nextInt(max + 1 - min) + min;
    }

    /**
     * The class for the orders.
     */
    public static class Order {
        private final Pair<MenuImpl.Pizza, Optional<MenuImpl.Pizza>> pizze;

        /**
         * The constructor of the class Order.
         * @param pizze
         */
        public Order(final Pair<MenuImpl.Pizza, Optional<MenuImpl.Pizza>> pizze) {
            this.pizze = pizze;
        }

        /**
         * @return the number of pizzas ordered by the client.
         */
        public int getNumberPizzasToOrder() {
            if (pizze.getRight().isPresent()) {
                return 2;
            }
            return 1;
        }

        /**
         * @return one or two pizzas ordered by the client.
         */
        public Pair<MenuImpl.Pizza, Optional<MenuImpl.Pizza>> getOrderPizzas() {
            return this.pizze;
        }
    }
}
