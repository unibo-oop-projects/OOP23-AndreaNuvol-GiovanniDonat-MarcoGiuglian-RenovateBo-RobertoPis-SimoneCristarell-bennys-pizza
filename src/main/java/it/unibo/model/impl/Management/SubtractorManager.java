package it.unibo.model.impl.Management;

public class SubtractorManager extends AbstractManager{

    @Override
    public void updateBalance(final double amount) {
        if(isWithdrawAllowed(amount)){
            balance -= amount;
        }
    }

    private boolean isWithdrawAllowed(final double amount){
        return balance >= amount;
    }
}
