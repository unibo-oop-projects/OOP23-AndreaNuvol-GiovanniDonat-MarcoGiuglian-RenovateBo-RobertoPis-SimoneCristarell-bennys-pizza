package it.unibo.model.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import it.unibo.model.api.PizzaFactory;

/**
 * Implementation of 'PizzaFactory' interface
 */
public class PizzaFactoryImpl implements PizzaFactory{

    private final List<IngredientImpl> addedIngredients = new ArrayList<>();

    @Override
    public void addIngredient(final PreparationZoneImpl zone, final IngredientImpl ingredientToAdd) {
        if (!this.addedIngredients.contains(ingredientToAdd)) {
            this.addedIngredients.add(ingredientToAdd);
            ingredientToAdd.reduce();
            if (hasMadeAMess()) {
                zone.dirtyIngredients.add(ingredientToAdd);
            }
        }
    }

    @Override
    public boolean equals(final List<IngredientImpl> requestedIngredients) {
        return this.addedIngredients.size() != requestedIngredients.size() 
            ? false 
            : requestedIngredients.stream()
                .filter(this.addedIngredients::contains)
                .count() == requestedIngredients.size();
    }

    @Override
    public List<IngredientImpl> getAddedIngredients() {
        return Collections.unmodifiableList(this.addedIngredients);
    }

    private boolean hasMadeAMess() {
        return new Random().nextInt(2) == 0 ? true : false;
    }

}
