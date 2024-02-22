package it.unibo.model.impl.management;

/**
 * Class of AdderManager it only allows to add quantities from the balance.
 */
public class AdderManager extends AbstractManager {
    /**
     * Update the field balance adding the amount.
     */
    @Override
    public void updateBalance(final double amount) {
        if (isDepositAllowed(amount)) {
            double balanceDay = getBalanceDay();
            balanceDay += amount;
            AbstractManager.setBalanceDay(balanceDay);
        } else {
            throw new IllegalArgumentException("Can't add negative or null value to balance.");
        }
    }

    private boolean isDepositAllowed(final double amount) {
        return amount >= 0;
    }
}
