package it.unibo.view;

import javax.swing.SwingUtilities;

import it.unibo.controller.api.Controller;

public class UpdateThread extends Thread {
    private final GUIHallImpl gui;
    private final Controller controller;

    public UpdateThread(GUIHallImpl gui, Controller controller) {
        this.gui = gui;
        this.controller = controller;
    }

    @Override
    public void run() {
        while (true) {
            // Consulta il controller e aggiorna le label
            double balanceTot = controller.getBalanceTot();
            double balanceDay = controller.getBalanceDay();

            // Aggiorna le label sulla GUI
            SwingUtilities.invokeLater(() -> {
                gui.updateBalanceLabels(balanceTot, balanceDay);
            });

            try {
                Thread.sleep(1000); // Attendi un secondo prima di aggiornare di nuovo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}