package it.unibo.model.impl.Management;

import it.unibo.model.api.Management;
import it.unibo.model.impl.Time.TimeImpl;
import java.beans.*;

public abstract class AbstractManager implements Management{
    private static final double minAmountToPassTheLevel = 10;
    private static double factoryMultiple = 1;
    protected static double balanceTot = 0;
    protected static double balanceDay = 0;
    protected static PropertyChangeSupport support;

    public AbstractManager(){
        support = new PropertyChangeSupport(this);
    }

    public double getBalanceTot(){
        return balanceTot;
    }

    public double getBalanceDay(){
        return balanceDay;
    }

    public static boolean levelPassed(){
        if(TimeImpl.getWorkingDay() != 1){
            factoryMultiple = 1.5;
        }
        return balanceDay >= minAmountToPassTheLevel*factoryMultiple ? true : false;
    }

    public static void resetBalanceDay(){
        balanceDay = 0;
        support.firePropertyChange("balanceDay", null, balanceDay);
    }

    public static void addBalanceTot(){
        balanceTot += balanceDay;
        support.firePropertyChange("balanceTot", null, balanceTot);
    }

    @Override
    public abstract void updateBalance(final double amount);
    
}
