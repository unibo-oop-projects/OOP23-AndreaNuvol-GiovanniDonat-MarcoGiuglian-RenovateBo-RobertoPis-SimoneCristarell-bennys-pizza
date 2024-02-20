package it.unibo.test_management;

import org.junit.jupiter.api.Test;
import it.unibo.model.impl.Management.AbstractManager;
import it.unibo.model.impl.Management.AdderManager;
import it.unibo.model.impl.Management.SubtractorManager;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test the subtractor manager class.
 */
public class TestSubtractorManager {

    private static final String DELTA = "0.001";
    private static final double POSITIVE_AMOUNT = 10.0;
    private static final double DECIMAL_AMOUNT = 14.4;
    private static final double NEGATIVE_AMOUNT = -10;
    private static final double STARTING_AMOUNT = 100;
    private AdderManager adderManager = new AdderManager();
    private AbstractManager manager = new SubtractorManager();

    /**
     * Test that can subtract positive amounts to balance.
     */
    @Test
    public void testSubtractPositiveNumbersToAmount() {
        adderManager.updateBalance(STARTING_AMOUNT);
        manager.updateBalance(POSITIVE_AMOUNT);
        assertEquals(STARTING_AMOUNT - POSITIVE_AMOUNT, manager.getBalanceDay(), DELTA);
        AbstractManager.resetBalanceDay();
    }

    /**
     * Test that can subtract decimal positive amounts to balance.
     */
    @Test
    public void testSubtractDecimalAmountsToBalance() {
        adderManager.updateBalance(STARTING_AMOUNT);
        manager.updateBalance(DECIMAL_AMOUNT);
        assertEquals(STARTING_AMOUNT - DECIMAL_AMOUNT, manager.getBalanceDay(), DELTA);
        AbstractManager.resetBalanceDay();
    }

    /**
     * Test cannot subtract negative amounts to balance.
     */
    @Test
    public void testSubtractNegativeAmountsToBalance() {
        assertThrows(IllegalArgumentException.class, () -> {
            manager.updateBalance(NEGATIVE_AMOUNT);
        });
        AbstractManager.resetBalanceDay();
    }

    /**
     * Test the fact that cannot subtract NaN to balance.
     */
    @Test
    public void testAddNanToBalance() {
        assertThrows(IllegalArgumentException.class, () -> {
            manager.updateBalance(Double.NaN);
        });
        AbstractManager.resetBalanceDay();
    }
}
