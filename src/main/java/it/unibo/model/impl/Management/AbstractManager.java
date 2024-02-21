package it.unibo.model.impl.management;

import it.unibo.model.api.Management;

/**
 * Abstract class of Management that implement the interface Management.
 */
public abstract class AbstractManager implements Management {
    private static final double MIN_AMOUNT_TO_PASS_LEVEL = 10;
    private static double balanceDay = 0;

    /**
     * @param newBalanceDay is the new balance day to set.
     */
    public static void setBalanceDay(final double newBalanceDay) {
        balanceDay = newBalanceDay;
    }

    /**
     * @return the balance of the day.
     */
    public double getBalanceDay() {
        return balanceDay;
    }

    /**
     * @return true if the player passed the level.
     */
    public static boolean levelPassed() {
        return balanceDay >= MIN_AMOUNT_TO_PASS_LEVEL;
    }

    /**
     * reset to 0 the balance day.
     */
    public static void resetBalanceDay() {
        balanceDay = 0;
    }

    @Override
    public abstract void updateBalance(double amount);
}
