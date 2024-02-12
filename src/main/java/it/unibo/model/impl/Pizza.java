package it.unibo.model.impl;

import java.util.*;

public class Pizza {
    
    private final String name;
    private final float cost;
    private List<String> ingredients;

    public Pizza(String name, float cost, List<String> ingredients) {
        this.name = name;
        this.cost = cost;
        this.ingredients = ingredients;
    }

    public String getName() {
        return this.name;
    }

    public List<String> getIngredients() {
        return this.ingredients;
    }

    public float getCost() {
        return this.cost;
    }
    
}
