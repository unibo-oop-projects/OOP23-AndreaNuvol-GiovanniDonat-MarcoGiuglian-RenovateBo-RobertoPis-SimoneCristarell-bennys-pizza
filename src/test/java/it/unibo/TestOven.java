package it.unibo;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import it.unibo.model.impl.OvenImpl;

/**
 * Test Oven class.
 */
public class TestOven {
    /**
     *  Oven initially empty.
     */
    @Test
    public void testInitiallyEmpty() {
        OvenImpl oven = new OvenImpl();
        assertTrue(oven.isOvenEmpty());
    }
    /**
     * Test if baking a pizza sets oven to not empty.
     */
    @Test
    public void testBakingPizzaSetsOvenNotEmpty() {
        OvenImpl oven = new OvenImpl();
        oven.bakingPizza();
        assertFalse(oven.isOvenEmpty());
    }
}
