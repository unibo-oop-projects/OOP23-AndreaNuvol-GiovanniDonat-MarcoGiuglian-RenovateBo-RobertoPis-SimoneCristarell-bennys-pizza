package it.unibo.model.api;

public interface Management {

    /**
     * It modifies the balance by adding or subtracting a certain value
     * @param amount => is the amount to add or subtract
     */
    void updateBalance(double amount);

}