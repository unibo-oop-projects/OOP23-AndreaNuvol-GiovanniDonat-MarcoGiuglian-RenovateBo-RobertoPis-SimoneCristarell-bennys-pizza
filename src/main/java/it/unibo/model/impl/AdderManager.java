package it.unibo.model.impl;

public class AdderManager extends AbstractManager{

    public AdderManager(final double actualAmount){
        super(actualAmount);
    }

    @Override
    public void updateBalance(final double amount) {
        balance += amount;
    }

    public static void main(String[] args) {
        AbstractManager m1 = new AdderManager(0);
        m1.updateBalance(2);
        System.out.println(m1.getBalance());
        m1.updateBalance(4);
        System.out.println(m1.getBalance());
        m1.updateBalance(43);
        System.out.println(m1.getBalance());

        AbstractManager m2 = new SubtractorManager(100);
        System.out.println(m2.getBalance());
        m2.updateBalance(4);
        System.out.println(m2.getBalance());
        m2.updateBalance(97);
        System.out.println(m2.getBalance());
    }

}
