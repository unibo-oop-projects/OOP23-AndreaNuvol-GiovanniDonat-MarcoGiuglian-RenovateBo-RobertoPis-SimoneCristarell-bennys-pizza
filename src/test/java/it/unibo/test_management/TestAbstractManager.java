package it.unibo.test_management;

import org.junit.jupiter.api.Test;

import it.unibo.model.impl.management.AbstractManager;
import it.unibo.model.impl.management.AdderManager;
import it.unibo.model.impl.management.SubtractorManager;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test the abstract class manager.
 */
public class TestAbstractManager {
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
}
