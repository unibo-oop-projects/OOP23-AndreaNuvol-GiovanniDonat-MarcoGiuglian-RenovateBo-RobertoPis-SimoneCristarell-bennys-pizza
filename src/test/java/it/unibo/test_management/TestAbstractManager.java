package it.unibo.test_management;

import org.junit.jupiter.api.Test;

import it.unibo.model.impl.Management.AbstractManager;
import it.unibo.model.impl.Management.AdderManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test the abstract class manager
 */
public class TestAbstractManager {
    private static final double DELTA = 0;
    private AbstractManager manager = new AdderManager();
     // AbstractManager can be instantiated
    @Test
    public void testInstantiation() {
        assertNotNull(manager);
    }

    // balance is initialized to 0
    @Test
    public void testBalanceInitialization() {
        assertEquals(DELTA, manager.getBalanceTot(), DELTA);
    }
}
