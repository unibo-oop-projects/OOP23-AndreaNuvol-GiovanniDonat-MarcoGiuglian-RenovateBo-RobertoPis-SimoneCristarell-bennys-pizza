package it.unibo.model.api;

public interface Management {

    /**
     * It modifies the balance by adding or subtracting a certain value
     * @param amount => is the amount to add or subtract
     */
    void updateBalance(final double amount);

    /**
     * It return the actual balance of the pizzeria
     * @return the actual balance
     */
    double getBalance();

}