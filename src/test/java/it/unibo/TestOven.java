package it.unibo;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import it.unibo.model.impl.OvenImpl;

/**
 * Test of the OvenImpl class.
 */
class TestOven {

    /**
     * Test the fact that initially Oven is empty.
     */
    @Test
    void testInitiallyEmpty() {
        final OvenImpl oven = new OvenImpl();
        assertTrue(oven.isOvenEmpty());
    }

    /**
     * Test the fact that when Oven is baking a pizza, oven is not empty.
     */
    @Test
    void testBakingPizzaSetsOvenNotEmpty() {
        final OvenImpl oven = new OvenImpl();
        oven.bakingPizza();
        assertFalse(oven.isOvenEmpty());
    }
}
