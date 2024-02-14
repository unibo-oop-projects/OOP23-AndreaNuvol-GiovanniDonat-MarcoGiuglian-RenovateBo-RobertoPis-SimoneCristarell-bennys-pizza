package it.unibo.model.api;

public interface Management {

    /**
     * It modifies the balance by adding or subtracting a certain value
     * @param amount => is the amount to add or subtract
     */
    void updateBalance(double amount);


    /**
     * It check if the player pass the level
     * @return true if player pass the level, false otherwise
     */
    boolean levelPassed();
}