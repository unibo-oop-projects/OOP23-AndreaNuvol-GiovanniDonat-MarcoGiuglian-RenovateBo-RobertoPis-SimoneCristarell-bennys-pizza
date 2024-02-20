package it.unibo.model.impl.Management;

/**
 * Class of AdderManager it only allows to add quantities from the balance.
 */
public class AdderManager extends AbstractManager {

    /**
     * update the field balance adding the amount
     */
    @Override
    public void updateBalance(final double amount) {
        if (isDepositAllowed(amount)) {
            balanceDay += amount;
        } else {
            throw new IllegalArgumentException("Can't add negative or null value to balance.");
        }
    }

    private boolean isDepositAllowed(final double amount) {
        return amount >= 0;
    }

}