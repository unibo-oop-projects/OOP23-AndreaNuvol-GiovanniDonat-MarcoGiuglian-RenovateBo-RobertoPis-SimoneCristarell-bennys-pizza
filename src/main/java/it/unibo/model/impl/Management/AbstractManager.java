package it.unibo.model.impl.Management;

import it.unibo.model.api.Management;
import it.unibo.model.impl.Time.TimeImpl;

public abstract class AbstractManager implements Management{
    private static final double minAmountToPassTheLevel = 10;
    private static double factoryMultiple = 1;
    protected static double balance = 0;

    public double getBalance(){
        return balance;
    }

    public static boolean levelPassed(){
        if(TimeImpl.getWorkingDay() != 1){
            factoryMultiple = 1.5;
        }
        return balance >= minAmountToPassTheLevel*factoryMultiple ? true : false;
    }

    public void resetBalance(){
        balance = 0;
    }

    @Override
    public abstract void updateBalance(final double amount);
    
}
