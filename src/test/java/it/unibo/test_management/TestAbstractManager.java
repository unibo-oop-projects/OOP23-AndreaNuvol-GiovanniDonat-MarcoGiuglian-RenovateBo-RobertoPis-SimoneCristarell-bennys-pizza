package it.unibo.test_management;

import org.junit.jupiter.api.Test;
import it.unibo.model.impl.Management.AbstractManager;
import it.unibo.model.impl.Management.AdderManager;
import it.unibo.model.impl.Management.SubtractorManager;
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
