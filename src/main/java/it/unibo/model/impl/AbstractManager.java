package it.unibo.model.impl;

import it.unibo.model.api.Management;

public abstract class AbstractManager implements Management{

    protected static double balance = 0;

    /*
    public AbstractManager(final double balance){
        this.balance = balance;
    }
    */
    
    @Override
    public double getBalance() {
        return AbstractManager.balance;
    }

    @Override
    public abstract void updateBalance(final double amount);
    
}
