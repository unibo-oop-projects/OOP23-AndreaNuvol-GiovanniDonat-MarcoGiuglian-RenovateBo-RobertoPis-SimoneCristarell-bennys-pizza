package it.unibo.test_management;

import org.junit.jupiter.api.Test;
import it.unibo.model.impl.Management.AdderManager;
import it.unibo.model.impl.Management.AbstractManager;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test the subtractor manager class
 */
public class TestAdderManager {
    private static final double DELTA = 0.001;
    private static final double POSITIVE_AMOUNT = 10;
    private static final double NEGATIVE_AMOUNT = -10;
    private static final double DECIMAL_AMOUNT = 3.14;
    private AdderManager manager = new AdderManager();
    
    // Can add positive amounts to balance
    @Test
    public void testAddPositiveAmountsToBalance() {
        AbstractManager.resetBalanceDay();
        manager.updateBalance(POSITIVE_AMOUNT);
        assertEquals(POSITIVE_AMOUNT, manager.getBalanceDay(), DELTA);
    }

    // Can add decimal amounts to balance
    @Test
    public void testAddDecimalAmountsToBalance() {
        AbstractManager.resetBalanceDay();
        manager.updateBalance(DECIMAL_AMOUNT);
        assertEquals(DECIMAL_AMOUNT, manager.getBalanceDay(), DELTA);
    }

    // Cannot add negative amounts to balance
    @Test
    public void testAddNegativeAmountsToBalance() {
        AbstractManager.resetBalanceDay();
        assertThrows(IllegalArgumentException.class, () -> {
            manager.updateBalance(NEGATIVE_AMOUNT);
        });

    }

    // Cannot add NaN to balance
    @Test
    public void testAddNanToBalance() {
        AbstractManager.resetBalanceDay();
        assertThrows(IllegalArgumentException.class, () -> {
            manager.updateBalance(Double.NaN);
        });
    }

}
