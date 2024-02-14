package it.unibo.model.impl.Management;

public class SubtractorManager extends AbstractManager{

    @Override
    public void updateBalance(final double amount) {
        if(isWithdrawAllowed(amount)){
            balance -= amount;
        }else{
            throw new IllegalArgumentException("Can't subtract negative, null, or less value than the balance.");
        }
    }

    private boolean isWithdrawAllowed(final double amount){
       return amount >= 0 && amount <= balance ? true : false;
    }
}
