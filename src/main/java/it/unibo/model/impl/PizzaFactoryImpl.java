package it.unibo.model.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import it.unibo.model.api.PizzaFactory;
import it.unibo.model.impl.IngredientsImpl.Dough;

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
            if (hasMadeAMess() && !Dough.class.isInstance(ingredientToAdd)) {
                zone.manageDirtyIngredients(Optional.of(ingredientToAdd));
            }
        }
    }

    @Override
    public boolean equals(final List<String> requestedIngredients) {
        final var ingredientsStrings = new ArrayList<String>();
        this.addedIngredients.forEach(i -> ingredientsStrings.add(i.toString()));
        return this.addedIngredients.size() != requestedIngredients.size()
            ? false 
            : requestedIngredients.stream()
                .filter(ingredientsStrings::contains)
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
