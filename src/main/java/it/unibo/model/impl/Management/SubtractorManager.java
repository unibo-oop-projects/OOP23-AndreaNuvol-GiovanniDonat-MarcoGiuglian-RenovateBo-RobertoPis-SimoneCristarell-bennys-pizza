package it.unibo.model.impl.Management;

/**
 * Class of SubtractorManager it only allows to remove quantities from the balance.
 */
public class SubtractorManager extends AbstractManager {

    @Override
    public void updateBalance(final double amount) {
        if (isWithdrawAllowed(amount)) {
            balanceDay -= amount;
        } else {
            throw new IllegalArgumentException("Can't subtract negative, null, or less value than the balance.");
        }
    }

    private boolean isWithdrawAllowed(final double amount) {
       return amount >= 0 && amount <= balanceDay;
    }

}
