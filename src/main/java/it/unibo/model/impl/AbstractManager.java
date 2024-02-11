package it.unibo.model.impl;

import it.unibo.model.api.Management;

public abstract class AbstractManager implements Management{

    protected double balance = 0;
    
    @Override
    public double getBalance() {
        return this.balance;
    }

    @Override
    public abstract void updateBalance(final double amount);
    
<<<<<<< HEAD
}
=======
}
>>>>>>> origin/Management
