package it.unibo.model.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import it.unibo.model.api.*;
import it.unibo.model.impl.IngredientsImpl.*;
public class PreparationZoneImpl implements PreparationZone {

    private static final int MIN_PIZZE_TO_PREPARE = 1;
    private static final int MAX_PIZZE_TO_PREPARE = 2;
    private final PizzaFactory pizza1 = new PizzaFactoryImpl();
    private Optional<PizzaFactory> pizza2 = Optional.empty();
    private final Oven oven = new OvenImpl();
    private final Map<Ingredient, Integer> ingredientsQuantities = new HashMap<>();

    public PreparationZoneImpl(final int numPizzeToPrepare) {
        if (numPizzeToPrepare < MIN_PIZZE_TO_PREPARE || numPizzeToPrepare > MAX_PIZZE_TO_PREPARE) {
            throw new IllegalArgumentException("The number of pizzas to prepare can be only 1 or 2.");
        } else if (numPizzeToPrepare == 2) {
            this.pizza2 = Optional.of(new PizzaFactoryImpl());
        }
        ingredientsQuantities.put(new Anchovy(), IngredientImpl.MAX_QUANTITY);
        ingredientsQuantities.put(new Artichoke(), IngredientImpl.MAX_QUANTITY);
        ingredientsQuantities.put(new CherryTomatoe(), IngredientImpl.MAX_QUANTITY);
        ingredientsQuantities.put(new Dough(), IngredientImpl.MAX_QUANTITY);
        ingredientsQuantities.put(new Fontina(), IngredientImpl.MAX_QUANTITY);
        ingredientsQuantities.put(new FrenchFry(), IngredientImpl.MAX_QUANTITY);
        ingredientsQuantities.put(new Gorgonzola(), IngredientImpl.MAX_QUANTITY);
        ingredientsQuantities.put(new Ham(), IngredientImpl.MAX_QUANTITY);
        ingredientsQuantities.put(new Mozzarella(), IngredientImpl.MAX_QUANTITY);
        ingredientsQuantities.put(new Mushroom(), IngredientImpl.MAX_QUANTITY);
        ingredientsQuantities.put(new Olive(), IngredientImpl.MAX_QUANTITY);
        ingredientsQuantities.put(new Onion(), IngredientImpl.MAX_QUANTITY);
        ingredientsQuantities.put(new Parmesan(), IngredientImpl.MAX_QUANTITY);
        ingredientsQuantities.put(new Salami(), IngredientImpl.MAX_QUANTITY);
        ingredientsQuantities.put(new Sausage(), IngredientImpl.MAX_QUANTITY);
        ingredientsQuantities.put(new TomatoSauce(), IngredientImpl.MAX_QUANTITY);
        ingredientsQuantities.put(new Tuna(), IngredientImpl.MAX_QUANTITY);
        ingredientsQuantities.put(new Wurstel(), IngredientImpl.MAX_QUANTITY);
    }

    @Override
    public PizzaFactory getPizza1() {
        return this.pizza1;
    }

    @Override
    public PizzaFactory getPizza2() {
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

}
