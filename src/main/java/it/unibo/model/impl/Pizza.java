package it.unibo.model.impl;

import java.util.*;

public class Pizza {
    
    private String name;
    private float cost;
    private List<String> ingredients;

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
