package it.unibo.model.impl;

public class AdderManager extends AbstractManager{

    @Override
    public void updateBalance(final double amount) {
        balance += amount;
    }

    public static void main(String[] args) {
        AbstractManager m1 = new AdderManager();
        m1.updateBalance(2);
        System.out.println(AbstractManager.balance);
        m1.updateBalance(4);
        System.out.println(AbstractManager.balance);
        m1.updateBalance(43);
        System.out.println(AbstractManager.balance);

        AbstractManager m2 = new SubtractorManager();
        System.out.println(AbstractManager.balance);
        m2.updateBalance(4);
        System.out.println(AbstractManager.balance);
        m2.updateBalance(97);
        System.out.println(AbstractManager.balance);
    }

}
