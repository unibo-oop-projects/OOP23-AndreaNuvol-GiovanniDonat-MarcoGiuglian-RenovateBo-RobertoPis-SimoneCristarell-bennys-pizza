package it.unibo.model.impl.Management;

import it.unibo.model.api.Management;

public abstract class AbstractManager implements Management{
    private final double minAmountToPassTheLevel = 30;
    protected static double balance = 0;

    public double getBalance(){
        return balance;
    }

    public boolean levelPassed(){
        return balance >= minAmountToPassTheLevel ? true : false;
    }

    public void resetBalance(){
        balance = 0;
    }

    @Override
    public abstract void updateBalance(final double amount);
    
}
