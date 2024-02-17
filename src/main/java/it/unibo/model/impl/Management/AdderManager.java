package it.unibo.model.impl.Management;

import java.beans.*;

public class AdderManager extends AbstractManager{

    @Override
    public void updateBalance(final double amount) {
        if(isDepositAllowed(amount)){
            balanceDay += amount;
            support.firePropertyChange("balanceDay", balanceDay, balanceDay);
        }else{
            throw new IllegalArgumentException("Can't add negative or null value to balance.");
        }
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    private boolean isDepositAllowed(final double amount){
        return amount >= 0;
    }

}
