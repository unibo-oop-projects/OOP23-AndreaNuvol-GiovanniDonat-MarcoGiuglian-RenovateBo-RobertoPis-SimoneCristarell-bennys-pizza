package it.unibo.model.impl;

import it.unibo.model.api.Management;

public class SubtractorManager implements Management{
    private double balance = 0;
    @Override
    public void updateBalance(final double amount) {
        balance -= amount;
    }

    @Override
    public double getBalance() {
        return this.balance;
    }
    
}
