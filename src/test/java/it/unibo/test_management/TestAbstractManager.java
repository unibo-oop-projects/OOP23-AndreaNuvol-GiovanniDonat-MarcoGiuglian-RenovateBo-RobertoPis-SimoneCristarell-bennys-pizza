package it.unibo.test_management;

import org.junit.jupiter.api.Test;

import it.unibo.model.impl.management.AbstractManager;
import it.unibo.model.impl.management.AdderManager;
import it.unibo.model.impl.management.SubtractorManager;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test the abstract class manager.
 */
class TestAbstractManager {
    private final AbstractManager manager = new AdderManager();
    private final AbstractManager manager2 = new SubtractorManager();
    /**
     * Test the instatation of manager.
     */
    @Test
    void testInstantiation() {
        assertNotNull(manager);
        assertNotNull(manager2);
    }
}
