package it.unibo.model.impl;

public class AdderManager extends AbstractManager{

    @Override
    public void updateBalance(final double amount) {
        balance += amount;
    }
}