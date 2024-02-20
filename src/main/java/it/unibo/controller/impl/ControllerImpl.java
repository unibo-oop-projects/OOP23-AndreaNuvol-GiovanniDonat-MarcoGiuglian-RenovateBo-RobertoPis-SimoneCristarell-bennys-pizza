package it.unibo.controller.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import it.unibo.controller.api.Controller;
import it.unibo.model.api.Client;
import it.unibo.model.api.PreparationZone;
import it.unibo.model.impl.OvenImpl;
import it.unibo.model.impl.PreparationZoneImpl;
import it.unibo.model.impl.Client.ClientImpl;
import it.unibo.model.impl.Management.AdderManager;
import it.unibo.model.impl.Management.SubtractorManager;
import it.unibo.model.impl.Menu.MenuImpl;
import it.unibo.model.impl.Menu.MenuImpl.Pizza;
import it.unibo.model.impl.Time.TimeImpl;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import org.apache.commons.lang3.tuple.Pair;

/**
 * Implementation of the controller interface.
 */
public class ControllerImpl implements Controller {
    private final SubtractorManager subtractorManager = new SubtractorManager();
    private final AdderManager adderManager = new AdderManager();
    private final PreparationZone preparationZone;
    private final Client client = new ClientImpl();
    private ClientImpl.Order order;
    private final TimeImpl time = new TimeImpl();
    private final ClientThread clientThread;
    private PropertyChangeSupport support;
    private PropertyChangeSupport propertyChangeSupport;
    private final OvenImpl oven = new OvenImpl();
    /**
     * The constructor of the controller.
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws SecurityException
     */
    public ControllerImpl() throws ClassNotFoundException,
                                    InstantiationException,
                                    IllegalAccessException,
                                    IllegalArgumentException,
                                    InvocationTargetException,
                                    NoSuchMethodException,
                                    SecurityException {
        this.preparationZone = new PreparationZoneImpl(this.subtractorManager);
        MenuImpl.generateMenu();
        this.propertyChangeSupport = new PropertyChangeSupport(this);
        this.clientThread = new ClientThread(this);
        this.clientThread.start();
    }

    /**
     * It receives a signal with these properties.
     * @param propertyName the name of the signal.
     * @param oldValue
     * @param newValue
     */
    protected void firePropertyChange(final String propertyName, final Object oldValue, final Object newValue) {
        this.propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
    }

    /**
     * It returns the client thread.
     */
    @Override
    public ClientThread getClientThread() {
        return this.clientThread;
    }

    /**
     * It returns the preparation zone.
     */
    @Override
    public PreparationZone getPreparationZone() {
        return this.preparationZone;
    }

    /**
     * It adds an ingredient on the pizza.
     */
    @Override
    public void addIngredient(final String ingredientStringToAdd, final boolean isPizza1) {
        this.preparationZone.actionsOnIngredients(ingredientStringToAdd,
                                                    isPizza1,
                                                    false);
    }

    /**
     * It supplies an ingredient.
     */
    @Override
    public void supply(final String ingredientStringToAdd) {
        this.preparationZone.actionsOnIngredients(ingredientStringToAdd,
                                                    false,
                                                    true);
    }

    /**
     * It throws a pizza in the garbage bin.
     */
    @Override
    public void throwPizzaInGarbageBin(final boolean isPizza1) {
        this.preparationZone.getGarbageBin().throwPizzaInGarbageBin(this.preparationZone, isPizza1);
    }

    /**
     * It bakes a pizza in the oven.
     */
    @Override
    public void bakingPizza() {
        this.preparationZone.getOven().bakingPizza();
    }

    /**
     * It gets an ingredient quantity.
     */
    @Override
    public int getIngredientQuantity(final String ingredientName) {
        for (final var ingredient: this.preparationZone.getIngredientsQuantities().keySet()) {
            if (ingredient.toString().equals(ingredientName)) {
                return ingredient.getQuantity();
            }
        }
        return -1;
    }

    /**
     * It returns the oven.
     */
    @Override
    public OvenImpl getOvenModel() {
        return this.oven;
    }
    
    /**
     * It returns the total balance.
     */
    @Override
    public double getBalanceTot() {
        return this.subtractorManager.getBalanceTot();
    }

    /**
     * It returns the daily balance.
     */
    @Override
    public double getBalanceDay() {
        return this.subtractorManager.getBalanceDay();
    }

    /**
     * It adds an amount to the balance.
     */
    @Override
    public void addToBalance(final double amount) {
        this.adderManager.updateBalance(amount);
    }

    /**
     * It subtracts an amount to the balance.
     */
    @Override
    public void subtractToBalance(final double amount) {
        this.subtractorManager.updateBalance(amount);
    }

    /**
     * It returns the adder manager.
     */
    @Override
    public AdderManager getAdderManagerModel() {
        return this.adderManager;
    }

    /**
     * It returns the subtractor manager.
     */
    @Override
    public SubtractorManager getSubtractorManagerModel() {
        return this.subtractorManager;
    }

    /**
     * It returns the ordered pizzas.
     */
    @Override
    public Pair<Pizza, Optional<Pizza>> order() {
        Pair<Pizza, Optional<Pizza>> orderedPizzas;
        this.order = this.client.order();
        this.preparationZone.setNumberOfPizzasToPrepare(order.getNumberPizzasToOrder());
        if (this.order.getNumberPizzasToOrder() == 1) {
            orderedPizzas = Pair.of(this.order.getOrderPizzas().getLeft(),
                                    Optional.empty()
                                    );
        } else {
            orderedPizzas = Pair.of(this.order.getOrderPizzas().getLeft(),
                                    Optional.of(order.getOrderPizzas().getRight().get())
                                    );
        }
        return orderedPizzas;
    }

    /**
     * The client pays.
     */
    @Override
    public void pay() {
        this.client.pay(this.preparationZone.getPizza1(), this.preparationZone.getPizza2());
    }

    /**
     * It generates the menu.
     */
    @Override
    public void generateMenu() {
        MenuImpl.generateMenu();
    }

    /**
     * It returns the menu.
     */
    @Override
    public List<String> getMenu() {
        final List<String> menu = new ArrayList<>();
        for (final Pizza pizza : MenuImpl.getPizzas()) {
            menu.add(pizza.toString());
        }
        return menu;
    }

    /**
     * It returns the working day.
     */
    @Override
    public int getWorkingDay() {
        return TimeImpl.getWorkingDay();
    }

    /**
     * It returns the current hour and minute.
     */
    @Override
    public String getHourAndMin() {
        return this.time.getHourAndMin();
    }

    /**
     * It returns the time object.
     */
    @Override
    public TimeImpl getTimeModel() {
        return this.time;
    }

    /**
     * It calls the addPropertyChangeListener of the support.
     */
    @Override
    public void addPropertyChangeListener(final PropertyChangeListener pcl) {
        this.support.addPropertyChangeListener(pcl);
    }

    /**
     * It starts a new day.
     */
    @Override
    public void newDay() {
        this.time.newDay();
    }

    /**
     * It resets the two pizza factories after the deliver of the order.
     */
    @Override
    public void resetPizzas() {
        this.preparationZone.resetPizzaPreparation();
    }
}
