package it.unibo.model.impl;

import java.io.File;
import java.nio.file.FileSystems;
import java.util.regex.Pattern;
import it.unibo.model.api.Ingredient;

/**
 * Abstract implementation of the 'Ingredient' interface.
 */
public abstract class IngredientImpl implements Ingredient {
    private static final String SEP = File.separator;
    private static final String PATH_TO_THE_ROOT = FileSystems.getDefault()
                                                                .getPath("")
                                                                .toAbsolutePath()
                                                                .toString();
    private static final String PATH_TO_RESOURCES = SEP
                                                    + "src"
                                                    + SEP
                                                    + "main"
                                                    + SEP
                                                    + "resources"
                                                    + SEP
                                                    + "ingredientsImages"
                                                    + SEP;
    private static final int MAX_QUANTITY = 100;
    private int quantity;
    private final double price;
    private final String imagePath;

    /**
     * Protected constructor of IngredientImpl, called by its subclasses.
     * @param price the price of the specific ingredient.
     * @param imageName the image name of the specific ingredient.
     */
    protected IngredientImpl(final double price, final String imageName) {
        this.quantity = MAX_QUANTITY;
        this.price = price;
        this.imagePath = PATH_TO_THE_ROOT + PATH_TO_RESOURCES + imageName;
    }

    /**
     * @return the quantity of the ingredient.
     */
    @Override
    public int getQuantity() {
        return this.quantity;
    }

    /**
     * @return the price of the ingredient.
     */
    @Override
    public double getPrice() {
        return this.price;
    }

    /**
     * @return the path for the image of the ingredient.
     */
    @Override
    public String getImagePath() {
        return this.imagePath;
    }

    /**
     * It supplies the ingredient.
     */
    @Override
    public final void supply() {
        this.quantity = MAX_QUANTITY;
    }

    /**
     * It reduces the quantity of the ingredient.
     */
    @Override
    public abstract void reduce();

    /**
     * Method in common among all the classes that inherit from 'IngredientImpl'.
     * It is used in the method 'reduce()' of these classes.
     * @param toReduce the quantity of the ingredient to reduce.
     */
    protected void reduceIngredient(final int toReduce) {
        this.quantity = this.quantity - toReduce;
    }

    /**
     * It checks if two objects are the same or not.
     * @return true or false.
     */
    @Override
    public boolean equals(final Object o) {
        return IngredientImpl.class.isInstance(o) && o != null && this.imagePath.equals(((IngredientImpl) o).getImagePath());
    }

    /**
     * It returns the hash code.
     */
    @Override
    public int hashCode() {
        return MAX_QUANTITY;
    }

    /**
     * It returns the name of the specific ingredient.
     */
    @Override
    public String toString() {
        final String[] strings = this.imagePath.split(Pattern.quote(SEP));
        return strings[strings.length - 1].split(Pattern.quote("."))[0];
    }
}
