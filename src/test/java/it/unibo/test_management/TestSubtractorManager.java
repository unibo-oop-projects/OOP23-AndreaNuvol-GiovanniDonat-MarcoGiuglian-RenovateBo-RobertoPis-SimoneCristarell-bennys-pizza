package it.unibo.test_management;

import org.junit.jupiter.api.Test;

import it.unibo.model.impl.Management.AdderManager;
import it.unibo.model.impl.Management.SubtractorManager;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestSubtractorManager {
    AdderManager adderManager = new AdderManager(); // we need it to start with a balance different from 0
    SubtractorManager manager = new SubtractorManager();

    // Can subtract positive amounts to balance
    @Test
    public void test_subtract_positive_numbers_to_amount() {
        adderManager.updateBalance(100);
        manager.updateBalance(10.0);
        assertEquals(90.0, manager.getBalance(), 0.001);
        manager.resetBalance();
    }

    // Can subtract decimal positive amounts to balance
    @Test
    public void test_subtract_decimal_amounts_to_balance(){
        adderManager.updateBalance(100);
        manager.updateBalance(14.4);
        assertEquals(85.6, manager.getBalance(), 0.001);
        manager.resetBalance();
    }

    // Cannot subtract negative amounts to balance
    @Test
    public void test_subtract_negative_amounts_to_balance(){
        assertThrows(IllegalArgumentException.class, () -> {
            manager.updateBalance(-14.4);
        });
        manager.resetBalance();
    }

    // Cannot subtract NaN to balance
    @Test
    public void test_add_nan_to_balance() {
        assertThrows(IllegalArgumentException.class, () -> {
            manager.updateBalance(Double.NaN);
        });
        manager.resetBalance();
    }
}
