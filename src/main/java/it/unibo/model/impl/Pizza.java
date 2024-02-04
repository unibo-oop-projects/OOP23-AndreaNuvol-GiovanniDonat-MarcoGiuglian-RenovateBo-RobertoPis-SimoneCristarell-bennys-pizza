package it.unibo.model.impl;

import java.util.*;

public class Pizza {
    
    private String name;
    private float cost;
    private final List<String> ingredients = new ArrayList<>();

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getIngredients() {
        return this.ingredients;
    }

    public float getCost() {
        return this.cost;
    }
    
}
