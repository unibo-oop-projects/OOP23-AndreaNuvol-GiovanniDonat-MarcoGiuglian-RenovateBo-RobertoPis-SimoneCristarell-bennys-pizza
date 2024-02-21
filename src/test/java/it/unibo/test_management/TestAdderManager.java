package it.unibo.test_management;

import org.junit.jupiter.api.Test;

import it.unibo.model.impl.management.AbstractManager;
import it.unibo.model.impl.management.AdderManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test the subtractor manager class.
 * Test the subtractor manager class.
 */
class TestAdderManager {
    private static final double DELTA = 0.001;
    private static final double POSITIVE_AMOUNT = 10;
    private static final double NEGATIVE_AMOUNT = -10;
    private static final double DECIMAL_AMOUNT = 3.15;
    private final AdderManager manager = new AdderManager();
    /**
     * Test if can add positive amounts to balance.
     */
    @Test
    void testAddPositiveAmountsToBalance() {
        AbstractManager.resetBalanceDay();
        manager.updateBalance(POSITIVE_AMOUNT);
        assertEquals(POSITIVE_AMOUNT, manager.getBalanceDay(), DELTA);
    }

    /**
     * Test if can add decimal amounts to balance.
     */
    @Test
    void testAddDecimalAmountsToBalance() {
        AbstractManager.resetBalanceDay();
        manager.updateBalance(DECIMAL_AMOUNT);
        assertEquals(DECIMAL_AMOUNT, manager.getBalanceDay(), DELTA);
    }

    /**
     * Test the fact that cannot add negative amounts to balance.
     */
    @Test
    void testAddNegativeAmountsToBalance() {
        AbstractManager.resetBalanceDay();
        assertThrows(IllegalArgumentException.class, () -> {
            manager.updateBalance(NEGATIVE_AMOUNT);
        });
    }

    /**
     * Test the fact that cannot add Nan to balance.
     */
    @Test
    void testAddNanToBalance() {
        AbstractManager.resetBalanceDay();
        assertThrows(IllegalArgumentException.class, () -> {
            manager.updateBalance(Double.NaN);
        });
    }
}
