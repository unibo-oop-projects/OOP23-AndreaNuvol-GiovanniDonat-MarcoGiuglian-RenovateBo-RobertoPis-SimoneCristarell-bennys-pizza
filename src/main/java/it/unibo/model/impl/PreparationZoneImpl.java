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
    private static final int MAX_DIRTY_INGREDIENTS = 4;
    private boolean isNumberOfPizzasToPrepareSet = false;
    private final Supplier supplier = new SupplierImpl();
    private SubtractorManager management;
    private PizzaFactory pizza1;
    private Optional<PizzaFactory> pizza2 = Optional.empty();
    private final Oven oven = new OvenImpl();
    private final Map<Ingredient, Integer> ingredientsQuantities = new HashMap<>();
    private final List<Ingredient> dirtyIngredients = new ArrayList<>();
    private final Cleaner cleaner = new CleanerImpl();
    
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
    public PizzaFactory getPizza2() throws IllegalStateException {
        ifNumOfPizzasUnsetOp();
        if (this.pizza2.isEmpty()) {
            throw new IllegalStateException("Pizza n. 2 is not requested from this client.");
        }
        return this.pizza2.get();
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
    public Cleaner getCleaner() {
        return this.cleaner;
    }

    @Override
    public boolean isDirty() {
        return !this.dirtyIngredients.isEmpty();
    }

    @Override
    public void manageDirtyIngredients(final Optional<Ingredient> dirtyIngredient) {
        if (dirtyIngredient.isEmpty()) {
            this.dirtyIngredients.clear();
        } else if (this.dirtyIngredients.size() < MAX_DIRTY_INGREDIENTS) {
            this.dirtyIngredients.add(dirtyIngredient.get());
        }
    }

    public List<String> getDirtyImagePath() {
        if (!isDirty()) {
            throw new IllegalStateException("The preparation zone is not dirty.");
        }
        final List<String> output = new ArrayList<>();
        this.dirtyIngredients.forEach(i -> output.add(i.getImagePath()));
        return output;
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
    public void resetPizzas() {
        this.pizza1 = new PizzaFactoryImpl();
        this.pizza2 = Optional.empty();
        this.isNumberOfPizzasToPrepareSet = false;
    }

}
