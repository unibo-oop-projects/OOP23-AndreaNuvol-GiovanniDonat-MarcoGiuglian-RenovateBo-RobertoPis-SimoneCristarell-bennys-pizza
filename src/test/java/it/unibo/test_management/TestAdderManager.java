package it.unibo.test_management;

import org.junit.jupiter.api.Test;
import it.unibo.model.impl.Management.AdderManager;
import it.unibo.model.impl.Management.AbstractManager;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestAdderManager {
    private static final double DELTA = 0.001;
    AdderManager manager = new AdderManager();
    
    // Can add positive amounts to balance
    @Test
    public void testAddPositiveAmountsToBalance() {
        AbstractManager.resetBalanceDay();
        manager.updateBalance(10);
        assertEquals(10, manager.getBalanceDay(), DELTA);
    }

    // Can add decimal amounts to balance
    @Test
    public void testAddDecimalAmountsToBalance() {
        AbstractManager.resetBalanceDay();
        manager.updateBalance(3.14);
        assertEquals(3.14, manager.getBalanceDay(), DELTA);
    }

    // Cannot add negative amounts to balance
    @Test
    public void testAddNegativeAmountsToBalance() {
        AbstractManager.resetBalanceDay();
        assertThrows(IllegalArgumentException.class, () -> {
            manager.updateBalance(-10);
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
