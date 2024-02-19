package it.unibo.model.impl.Management;

public class AdderManager extends AbstractManager{

    @Override
    public void updateBalance(final double amount) {
        if(isDepositAllowed(amount)){
            balanceDay += amount;
        }else{
            throw new IllegalArgumentException("Can't add negative or null value to balance.");
        }
    }

    private boolean isDepositAllowed(final double amount){
        return amount >= 0;
    }

}
