package it.unibo.test_management;

import org.junit.jupiter.api.Test;

import it.unibo.model.impl.Management.AbstractManager;
import it.unibo.model.impl.Management.AdderManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestAdderManager {

    AdderManager manager = new AdderManager();
    
    // Can add positive amounts to balance
    @Test
    public void test_add_positive_amounts_to_balance() {
        manager.resetBalance();
        manager.updateBalance(10);
        assertEquals(10, manager.getBalance(), 0.001);
    }

    // Can add decimal amounts to balance
    @Test
    public void test_add_decimal_amounts_to_balance() {
        manager.resetBalance();
        manager.updateBalance(3.14);
        assertEquals(3.14, manager.getBalance(), 0.001);
    }

    // Can add negative amounts to balance (should not be allowed)
    @Test
    public void test_add_negative_amounts_to_balance() {
        manager.resetBalance();
        manager.updateBalance(-10);
        assertEquals(0, manager.getBalance(), 0.001);
    }

    // Can add NaN to balance (should not be allowed)
    @Test
    public void test_add_nan_to_balance() {
        manager.resetBalance();
        manager.updateBalance(Double.NaN);
        assertEquals(0, manager.getBalance(), 0.001);
    }

}
