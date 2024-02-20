package it.unibo.test_management;

import org.junit.jupiter.api.Test;
import it.unibo.model.impl.Management.AbstractManager;
import it.unibo.model.impl.Management.AdderManager;
import it.unibo.model.impl.Management.SubtractorManager;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test the abstract class manager.
 */
public class TestAbstractManager {
    private static final double DELTA = 0;
    private AbstractManager manager = new AdderManager();
    private AbstractManager manager2 = new SubtractorManager();
    
    /**
     * Test the instatation of manager.
     */
    @Test
    public void testInstantiation() {
        assertNotNull(manager);
        assertNotNull(manager2);
    }

    /**
     * Test if balance is initialized to 0.
     */
    @Test
    public void testBalanceInitialization() {
        assertEquals(DELTA, manager.getBalanceTot(), DELTA);
    }
}
