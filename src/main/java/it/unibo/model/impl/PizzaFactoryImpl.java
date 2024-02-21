package it.unibo.model.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.unibo.model.api.PizzaFactory;
import it.unibo.model.impl.ingredients.Dough;

/**
 * Implementation of 'PizzaFactory' interface.
 */
public class PizzaFactoryImpl implements PizzaFactory {

    private final List<IngredientImpl> addedIngredients;

    /**
     * Constructor of a pizzaFactoryImpl taking another pizza factory as parameter.
     * @param p the other pizza factory.
     */
    public PizzaFactoryImpl(final PizzaFactoryImpl p) {
        this.addedIngredients = new ArrayList<>(p.getAddedIngredients());
    }

    /**
     * Default constructor of pizzaFactoryImpl.
     */
    public PizzaFactoryImpl() {
        this.addedIngredients = new ArrayList<>();
    }

    /**
     * It adds an ingredient on the pizza.
     */
    @Override
    public void addIngredient(final PreparationZoneImpl zone, final IngredientImpl ingredientToAdd) {
        if (!this.addedIngredients.contains(new Dough()) && isNotDough(ingredientToAdd)) {
            throw new IllegalStateException("You have to put the dough before putting the ingredients.");
        }
        if (!this.addedIngredients.contains(ingredientToAdd)) {
            this.addedIngredients.add(ingredientToAdd);
            ingredientToAdd.reduce();
        }
    }

    private boolean isNotDough(final IngredientImpl ingredientToAdd) {
        return !Dough.class.isInstance(ingredientToAdd);
    }

    /**
     * It returns if two pizzas have the same ingredients.
     */
    @Override
    public boolean isEqual(final List<String> requestedIngredients) {
        final var ingredientsStrings = new ArrayList<String>();
        this.addedIngredients.forEach(i -> ingredientsStrings.add(i.toString()));
        if (this.addedIngredients.size() != requestedIngredients.size()) {
            return false;
        }
        return requestedIngredients.stream()
            .filter(ingredientsStrings::contains)
            .count() == requestedIngredients.size();
    }

    /**
     * It returns a list of the current added ingredients.
     */
    @Override
    public List<IngredientImpl> getAddedIngredients() {
        return Collections.unmodifiableList(this.addedIngredients);
    }
}
