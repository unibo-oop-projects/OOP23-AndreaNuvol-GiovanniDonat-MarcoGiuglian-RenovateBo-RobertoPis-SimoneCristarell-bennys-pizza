package it.unibo.model.impl.Management;

import it.unibo.model.api.Management;
import it.unibo.model.impl.Time.TimeImpl;

/**
 * Abstract class of Management that implement the interface Management.
 */
public abstract class AbstractManager implements Management {
    private static final double MIN_AMOUNT_TO_PASS_LEVEL = 10;
    private static final double FACTORY_FOR_DAY_NOT_ONE = 1.5;
    private static double factoryMultiple = 1;
    private static double balanceTot = 0;
    private static double balanceDay = 0;

    /**
     * @return the total balance.
     */
    public double getBalanceTot() {
        return balanceTot;
    }

    /**
     * @param newBalanceTot is the new balance tot to set.
     */
    public static void setBalanceTot(final double newBalanceTot) {
        balanceTot = newBalanceTot;
    }

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
        if (TimeImpl.getWorkingDay() != 1) {
            factoryMultiple = FACTORY_FOR_DAY_NOT_ONE;
        }
        return balanceDay >= MIN_AMOUNT_TO_PASS_LEVEL * factoryMultiple;
    }

    /**
     * reset to 0 the balance day.
     */
    public static void resetBalanceDay() {
        balanceDay = 0;
    }

    /**
     * adds the daily balance into the total balance.
     */
    public static void addBalanceTot() {
        balanceTot += balanceDay;
    }

    @Override
    public abstract void updateBalance(double amount);
}
