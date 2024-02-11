package it.unibo.model.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import it.unibo.model.api.*;
import it.unibo.model.impl.IngredientsImpl.*;
public class PreparationZoneImpl implements PreparationZone {

    private final PizzaFactory pizza1 = new PizzaFactoryImpl();
    private final Optional<PizzaFactory> pizza2 = Optional.of(new PizzaFactoryImpl());
    private final Oven oven = new OvenImpl();
    private final Map<Ingredient, Integer> ingredientsQuantities = new HashMap<>();

    public PreparationZoneImpl() {
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
    public Optional<PizzaFactory> getPizza2() {
        return this.pizza2;
    }

    @Override
    public Map<Ingredient, Integer> getIngredientsQuantities() {
        return this.ingredientsQuantities;
    }

    @Override
    public Oven getOven() {
        return this.oven;
    }

}
