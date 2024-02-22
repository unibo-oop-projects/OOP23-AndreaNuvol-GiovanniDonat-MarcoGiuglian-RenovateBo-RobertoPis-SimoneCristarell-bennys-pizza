package it.unibo.model.impl.management;

/**
 * Class of SubtractorManager it only allows to remove quantities from the balance.
 */
public class SubtractorManager extends AbstractManager {
    /**
     * Update the field balance subtracting the amount.
     */
    @Override
    public void updateBalance(final double amount) {
        if (isWithdrawAllowed(amount)) {
            double balanceDay = getBalanceDay();
            balanceDay -= amount;
            AbstractManager.setBalanceDay(balanceDay);
        } else {
            throw new IllegalArgumentException("Can't subtract negative, null, or less value than the balance.");
        }
    }

    private boolean isWithdrawAllowed(final double amount) {
       return amount >= 0 && amount <= getBalanceDay();
    }
}
