package it.unibo.model.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import it.unibo.model.api.*;
import it.unibo.model.impl.Management.SubtractorManager;

public class PreparationZoneImpl implements PreparationZone {

    private static final int MIN_PIZZAS_TO_PREPARE = 1;
    private static final int MAX_PIZZAS_TO_PREPARE = 2;
    private boolean isNumberOfPizzasToPrepareSet = false;
    private final Supplier supplier = new SupplierImpl();
    private final SubtractorManager management;
    private PizzaFactory pizza1;
    private Optional<PizzaFactory> pizza2 = Optional.empty();
    private final Oven oven = new OvenImpl();
    private final Map<Ingredient, Integer> ingredientsQuantities = new HashMap<>();
    private final GarbageBin garbageBin = new GarbageBinImpl();
    
    public PreparationZoneImpl(final SubtractorManager management) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        this.management = management;

        final List<String> ingredientsClassesNames = new ArrayList<>(List.of("Anchovy", "Artichoke", "CherryTomatoe", 
            "Dough", "Fontina", "FrenchFry", "Gorgonzola", "Ham", "Mozzarella", "Mushroom", "Olive", "Onion", 
            "Parmesan", "Salami", "Sausage", "TomatoSauce", "Tuna", "Wurstel"));

        for (final var cl: ingredientsClassesNames) {
            final var clazz = Class.forName(this.getClass().getPackageName() + ".IngredientsImpl." + cl);
            this.ingredientsQuantities.put((Ingredient)clazz.getConstructor().newInstance(), IngredientImpl.MAX_QUANTITY);
        }

    }

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

    @Override
    public PizzaFactory getPizza1() {
        ifNumOfPizzasUnsetOp();
        return this.pizza1;
    }

    private void ifNumOfPizzasUnsetOp() {
        if (!this.isNumberOfPizzasToPrepareSet) {
            throw new IllegalStateException("The number of pizzas to prepare is unknown.");
        }
    }

    @Override
    public Optional<PizzaFactory> getPizza2() throws IllegalStateException {
        ifNumOfPizzasUnsetOp();
        return this.pizza2;
    }

    @Override
    public Map<Ingredient, Integer> getIngredientsQuantities() {
        return Collections.unmodifiableMap(this.ingredientsQuantities);
    }

    @Override
    public Oven getOven() {
        return this.oven;
    }

    @Override
    public GarbageBin getGarbageBin() {
        return this.garbageBin;
    }

    @Override
    public void actionsOnIngredients(final String ingredientName, final boolean isPizza1, final boolean isASupply) {
        ifNumOfPizzasUnsetOp();
        this.ingredientsQuantities.keySet().stream()
            .filter(ingredient -> ingredient.toString().equals(ingredientName))
            .forEach(ingredient -> {
                if (isASupply) {
                    if (ingredient.getQuantity() == IngredientImpl.MAX_QUANTITY) {
                        throw new IllegalStateException("The quantity of this ingredient is already the maximum possible.");
                    }
                    this.supplier.supply(ingredient, management);
                } else {
                        if (ingredient.getQuantity() < 0) {
                            throw new IllegalStateException("This ingredient is finished.");
                        }
                        if (isPizza1) {
                            this.pizza1.addIngredient(this, (IngredientImpl)ingredient);
                        } else {
                            this.pizza2.get().addIngredient(this, (IngredientImpl)ingredient);
                        }
                } 
                this.ingredientsQuantities.replace(ingredient, ingredient.getQuantity());
            });
    }

    @Override
    public void resetPizza(final boolean isPizza1) {
        if (isPizza1) {
            this.pizza1 = new PizzaFactoryImpl();
            return;
        }
        this.pizza2 = this.pizza2.isPresent() ? Optional.of(new PizzaFactoryImpl()) : Optional.empty();
    }

    @Override
    public void resetPizzaPreparation() {
        resetPizza(true);
        resetPizza(false);
        this.isNumberOfPizzasToPrepareSet = false;
    }

}
