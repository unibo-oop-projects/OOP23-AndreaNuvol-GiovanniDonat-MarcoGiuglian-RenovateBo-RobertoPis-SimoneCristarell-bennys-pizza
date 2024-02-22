package it.unibo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import java.io.File;
import java.nio.file.FileSystems;
import org.junit.jupiter.api.Test;
import it.unibo.model.api.Ingredient;
import it.unibo.model.impl.ingredients.CherryTomatoe;
import it.unibo.model.impl.ingredients.Gorgonzola;
import it.unibo.model.impl.ingredients.Onion;
import it.unibo.model.impl.ingredients.Wurstel;

/**
 * Test for the IngredientImpl class.
 */
class TestIngredient {

    private static final String SEP = File.separator;
    private static final int MAX_QUANTITY = 100;
    private static final int EXPECTED_ONE = 92;
    private static final int EXPECTED_TWO = 76;
    private static final String PATH_TO_THE_ROOT = FileSystems.getDefault().getPath("").toAbsolutePath().toString();
    private static final String PATH_TO_RESOURCES = SEP
                                                    + "src"
                                                    + SEP
                                                    + "main"
                                                    + SEP
                                                    + "resources"
                                                    + SEP
                                                    + "ingredientsImages"
                                                    + SEP;

    /**
     * Test the fields of the Ingredient.
     */
    @Test
    void testFields() {
        final Ingredient wurstel = new Wurstel();
        assertEquals(MAX_QUANTITY, wurstel.getQuantity());
        final double wurstelQuantityToReduce = 1.1;
        assertEquals(wurstelQuantityToReduce, wurstel.getPrice());
        final String wurstelImagePath = PATH_TO_THE_ROOT + PATH_TO_RESOURCES + "Wurstel.png";
        assertEquals(wurstelImagePath, wurstel.getImagePath());
    }

    /**
     * Test the reduction and the supply of Ingredients.
     */
    @Test
    void testReductionAndSupply() {
        final Ingredient cherryTomatoes = new CherryTomatoe();
        assertEquals(MAX_QUANTITY, cherryTomatoes.getQuantity());
        cherryTomatoes.reduce();
        assertEquals(EXPECTED_ONE, cherryTomatoes.getQuantity());
        cherryTomatoes.supply();
        assertEquals(MAX_QUANTITY, cherryTomatoes.getQuantity());
        cherryTomatoes.reduce();
        cherryTomatoes.reduce();
        cherryTomatoes.reduce();
        assertEquals(EXPECTED_TWO, cherryTomatoes.getQuantity());
    }

    /**
     * Test the methods equals and toString of Ingredient.
     */
    @Test
    void testEqualsAndToString() {
        final Ingredient onions = new Onion();
        final Ingredient gorgonzola = new Gorgonzola();
        assertNotEquals(onions, gorgonzola);
        assertEquals("Onions", onions.toString());
        assertEquals("Gorgonzola", gorgonzola.toString());
    }
}
