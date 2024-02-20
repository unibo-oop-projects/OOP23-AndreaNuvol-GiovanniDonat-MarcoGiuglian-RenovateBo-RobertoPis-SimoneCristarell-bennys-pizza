package it.unibo;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import it.unibo.model.impl.OvenImpl;
import it.unibo.model.impl.OvenImpl;

/**
 * Test of the OvenImpl class.
 */
public class TestOven {

    /**
     * Test the fact that initially Oven is empty.
     */
    @Test
    public void testInitiallyEmpty() {
        OvenImpl oven = new OvenImpl();
        assertTrue(oven.isOvenEmpty());
    }

    /**
     * Test the fact that when Oven is baking a pizza, oven is not empty.
     */
    @Test
    public void testBakingPizzaSetsOvenNotEmpty() {
        OvenImpl oven = new OvenImpl();
        oven.bakingPizza();
        assertFalse(oven.isOvenEmpty());
    }
}
