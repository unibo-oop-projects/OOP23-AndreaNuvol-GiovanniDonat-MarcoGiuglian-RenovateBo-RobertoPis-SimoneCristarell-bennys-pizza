package it.unibo.model.impl.Management;

import java.beans.*;

public class SubtractorManager extends AbstractManager{

    @Override
    public void updateBalance(final double amount) {
        if(isWithdrawAllowed(amount)){
            balanceDay -= amount;
            support.firePropertyChange("balanceDay", null, balanceDay);
        }else{
            throw new IllegalArgumentException("Can't subtract negative, null, or less value than the balance.");
        }
    }

    private boolean isWithdrawAllowed(final double amount){
       return amount >= 0 && amount <= balanceDay ? true : false;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
