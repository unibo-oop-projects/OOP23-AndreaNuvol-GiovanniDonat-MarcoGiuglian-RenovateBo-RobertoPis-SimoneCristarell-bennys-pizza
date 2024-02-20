package it.unibo.test_management;

import org.junit.jupiter.api.Test;

import it.unibo.model.impl.Management.AbstractManager;
import it.unibo.model.impl.Management.AdderManager;
import it.unibo.model.impl.Management.SubtractorManager;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestSubtractorManager {

    private static final String DELTA = "0.001";
    AdderManager adderManager = new AdderManager(); // we need it to start with a balance different from 0
    AbstractManager manager = new SubtractorManager();

    // Can subtract positive amounts to balance
    @Test
    public void testSubtractPositiveNumbersToAmount() {
        adderManager.updateBalance(100);
        manager.updateBalance(10.0);
        assertEquals(90.0, manager.getBalanceDay(), DELTA);
        AbstractManager.resetBalanceDay();
    }

    // Can subtract decimal positive amounts to balance
    @Test
    public void testSubtractDecimalAmountsToBalance() {
        adderManager.updateBalance(100);
        manager.updateBalance(14.4);
        assertEquals(85.6, manager.getBalanceDay(), DELTA);
        AbstractManager.resetBalanceDay();
    }

    // Cannot subtract negative amounts to balance
    @Test
    public void testSubtractNegativeAmountsToBalance() {
        assertThrows(IllegalArgumentException.class, () -> {
            manager.updateBalance(-14.4);
        });
        AbstractManager.resetBalanceDay();
    }

    // Cannot subtract NaN to balance
    @Test
    public void testAddNanToBalance() {
        assertThrows(IllegalArgumentException.class, () -> {
            manager.updateBalance(Double.NaN);
        });
        AbstractManager.resetBalanceDay();
    }
}
