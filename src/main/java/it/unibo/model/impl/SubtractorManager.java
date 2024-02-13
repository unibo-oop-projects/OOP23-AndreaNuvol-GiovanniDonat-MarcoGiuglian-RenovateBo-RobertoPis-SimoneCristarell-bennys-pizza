package it.unibo.model.impl;

public class SubtractorManager extends AbstractManager{

    public SubtractorManager(final double actualAmount){
        super(actualAmount);
    }

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
