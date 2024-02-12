package it.unibo.model.impl.Management;

import it.unibo.model.api.Management;

public abstract class AbstractManager implements Management{

    protected static double balance = 0;

    @Override
    public abstract void updateBalance(final double amount);
    
}
