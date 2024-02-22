package it.unibo.model.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import it.unibo.model.api.GarbageBin;
import it.unibo.model.api.Ingredient;
import it.unibo.model.api.Oven;
import it.unibo.model.api.PreparationZone;
import it.unibo.model.api.Supplier;
import it.unibo.model.impl.management.SubtractorManager;

/**
 * Implementation class of PreparationZone interface.
 */
public class PreparationZoneImpl implements PreparationZone {

    private static final int MIN_PIZZAS_TO_PREPARE = 1;
    private static final int MAX_PIZZAS_TO_PREPARE = 2;
    private static final int MAX_QUANTITY = 100;
    private boolean isNumberOfPizzasToPrepareSet;
    private final Supplier supplier = new SupplierImpl();
    private final SubtractorManager management;
    private PizzaFactoryImpl pizza1;
    private Optional<PizzaFactoryImpl> pizza2 = Optional.empty();
    private final Oven oven = new OvenImpl();
    private final Map<Ingredient, Integer> ingredientsQuantities;
    private final GarbageBin garbageBin = new GarbageBinImpl();

    /** 
     * Constructor of PreparationZoneImpl by a PreparationZone.
     * @param pZoneImpl
     * @param management 
     */
    public PreparationZoneImpl(final PreparationZone pZoneImpl,
                                final SubtractorManager management) {
        this.management = management;
        this.pizza1 = pZoneImpl.getPizza1();
        this.pizza2 = pZoneImpl.getPizza2();
        this.ingredientsQuantities = pZoneImpl.getIngredientsQuantities();
        this.isNumberOfPizzasToPrepareSet = pZoneImpl.isNumberOfPizzasToPrepareSet();
    }

    /**
     * Constrcutor of PreparationZoneImpl.
     * @param management the management we use to set the current balance after a supply.
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws SecurityException
     */
    public PreparationZoneImpl(final SubtractorManager management) throws ClassNotFoundException,
                                                                            InstantiationException,
                                                                            IllegalAccessException,
                                                                            InvocationTargetException,
                                                                            NoSuchMethodException {
        this.management = management;
        this.ingredientsQuantities = new HashMap<>();
        final List<String> ingredientsClassesNames = new ArrayList<>(List.of("Anchovy", "Artichoke", "CherryTomatoe",
            "Dough", "Fontina", "FrenchFry", "Gorgonzola", "Ham", "Mozzarella", "Mushroom", "Olive", "Onion",
            "Parmesan", "Salami", "Sausage", "TomatoSauce", "Tuna", "Wurstel"));

        for (final var cl: ingredientsClassesNames) {
            final var clazz = Class.forName(this.getClass().getPackageName() + ".ingredients." + cl);
            this.ingredientsQuantities.put((Ingredient) clazz.getConstructor().newInstance(), MAX_QUANTITY);
        }
    }

    /**
     * It sets the number of pizzas to prepare.
     */
    @Override
    public void setNumberOfPizzasToPrepare(final int numberOfPizzas) {
        if (numberOfPizzas < MIN_PIZZAS_TO_PREPARE || numberOfPizzas > MAX_PIZZAS_TO_PREPARE) {
            throw new IllegalArgumentException("The number of pizzas to prepare can be only 1 or 2.");
        }
        if (numberOfPizzas == MAX_PIZZAS_TO_PREPARE) {
            this.pizza2 = Optional.of(new PizzaFactoryImpl());
        }
        this.pizza1 = new PizzaFactoryImpl();
        this.isNumberOfPizzasToPrepareSet = true;
    }

    /**
     * It returns the prepared pizza n. 1.
     */
    @Override
    public PizzaFactoryImpl getPizza1() {
        ifNumOfPizzasUnsetOp();
        return new PizzaFactoryImpl(this.pizza1);
    }

    /**
     * It returns if the number of pizzas to prepare is set.
     */
    @Override
    public boolean isNumberOfPizzasToPrepareSet() {
        return this.isNumberOfPizzasToPrepareSet;
    }

    private void ifNumOfPizzasUnsetOp() {
        if (!this.isNumberOfPizzasToPrepareSet) {
            throw new IllegalStateException("The number of pizzas to prepare is unknown.");
        }
    }

    /**
     * It returns the prepared pizza n. 2.
     */
    @Override
    public Optional<PizzaFactoryImpl> getPizza2() {
        ifNumOfPizzasUnsetOp();
        return this.pizza2;
    }

    /**
     * It returns a map with the ingredient as key and its current quantity as value.
     */
    @Override
    public Map<Ingredient, Integer> getIngredientsQuantities() {
        return Collections.unmodifiableMap(this.ingredientsQuantities);
    }

    /**
     * It returns the oven.
     */
    @Override
    public Oven getOven() {
        return this.oven;
    }

    /**
     * It returns the garbage bin.
     */
    @Override
    public GarbageBin getGarbageBin() {
        return this.garbageBin;
    }

    /**
     * It adds or supplies an ingredient. This depends from 'isASupply'.
     */
    @Override
    public void actionsOnIngredients(final String ingredientName, final boolean isPizza1, final boolean isASupply) {
        ifNumOfPizzasUnsetOp();
        this.ingredientsQuantities.keySet().stream()
            .filter(ingredient -> ingredient.toString().equals(ingredientName))
            .forEach(ingredient -> {
                if (isASupply) {
                    if (ingredient.getQuantity() == MAX_QUANTITY) {
                        throw new IllegalStateException("The quantity of this ingredient is already the maximum possible.");
                    }
                    this.supplier.supply(ingredient, management);
                } else {
                        if (ingredient.getQuantity() < 0) {
                            throw new IllegalStateException("This ingredient is finished.");
                        }
                        if (isPizza1) {
                            this.pizza1.addIngredient(this, (IngredientImpl) ingredient);
                        } else {
                            this.pizza2.get().addIngredient(this, (IngredientImpl) ingredient);
                        }
                } 
                this.ingredientsQuantities.replace(ingredient, ingredient.getQuantity());
            });
    }

    /**
     * It resets a single pizza between the two.
     */
    @Override
    public void resetPizza(final boolean isPizza1) {
        if (isPizza1) {
            this.pizza1 = new PizzaFactoryImpl();
            return;
        }
        this.pizza2 = this.pizza2.isPresent() ? Optional.of(new PizzaFactoryImpl()) : Optional.empty();
    }

    /**
     * It resets the two pizzas and puts the number of pizzas to prepare at 0.
     */
    @Override
    public void resetPizzaPreparation() {
        this.pizza1 = new PizzaFactoryImpl();
        this.pizza2 = Optional.empty();
        this.isNumberOfPizzasToPrepareSet = false;
    }

}
