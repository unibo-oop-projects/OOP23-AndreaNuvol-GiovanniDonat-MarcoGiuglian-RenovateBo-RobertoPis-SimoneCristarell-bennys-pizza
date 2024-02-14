package it.unibo.model.impl.Management;

import it.unibo.model.api.Management;
import it.unibo.model.impl.Time.TimeImpl;

public abstract class AbstractManager implements Management{
    private static final double minAmountToPassTheLevel = 10;
    private static double factoryMultiple = 1;
    protected static double balanceTot = 0;
    protected static double balanceDay = 0;

    public double getBalanceTot(){
        return balanceTot;
    }

    public static boolean levelPassed(){
        if(TimeImpl.getWorkingDay() != 1){
            factoryMultiple = 1.5;
        }
        return balanceDay >= minAmountToPassTheLevel*factoryMultiple ? true : false;
    }

    public static void resetBalanceDay(){
        balanceDay = 0;
    }

    public static void addBalanceTot(){
        balanceTot += balanceDay;
    }

    @Override
    public abstract void updateBalance(final double amount);
    
}
