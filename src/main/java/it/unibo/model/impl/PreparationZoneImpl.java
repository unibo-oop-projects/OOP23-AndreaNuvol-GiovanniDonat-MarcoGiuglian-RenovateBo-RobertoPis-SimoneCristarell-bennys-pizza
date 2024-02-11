package it.unibo.model.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import it.unibo.model.api.*;
// import it.unibo.model.impl.IngredientsImpl.*;
public class PreparationZoneImpl implements PreparationZone {

    private static final int MIN_PIZZE_TO_PREPARE = 1;
    private static final int MAX_PIZZE_TO_PREPARE = 2;
    private final PizzaFactory pizza1 = new PizzaFactoryImpl();
    private Optional<PizzaFactory> pizza2 = Optional.empty();
    private final Oven oven = new OvenImpl();
    private final Map<Ingredient, Integer> ingredientsQuantities = new HashMap<>();
    

    public PreparationZoneImpl(final int numPizzeToPrepare) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        if (numPizzeToPrepare < MIN_PIZZE_TO_PREPARE || numPizzeToPrepare > MAX_PIZZE_TO_PREPARE) {
            throw new IllegalArgumentException("The number of pizzas to prepare can be only 1 or 2.");
        } else if (numPizzeToPrepare == 2) {
            this.pizza2 = Optional.of(new PizzaFactoryImpl());
        }

        List<String> classes = new ArrayList<>(List.of("Anchovy", "Artichoke", "CherryTomatoe", "Dough", 
            "Fontina", "FrenchFry", "Gorgonzola", "Ham", "Mozzarella", "Mushroom", "Olive", "Onion", "Parmesan", 
            "Salami", "Sausage", "TomatoSauce", "Tuna", "Wurstel"));

        for (final var cl: classes) {
            final var clazz = Class.forName(this.getClass().getPackageName() + ".IngredientsImpl." + cl);
            this.ingredientsQuantities.put((Ingredient)clazz.getConstructor().newInstance(), IngredientImpl.MAX_QUANTITY);
        }
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
