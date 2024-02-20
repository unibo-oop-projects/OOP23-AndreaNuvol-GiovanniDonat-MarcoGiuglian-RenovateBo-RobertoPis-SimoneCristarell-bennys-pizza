package it.unibo.view;

import javax.swing.SwingUtilities;

import it.unibo.controller.api.Controller;

/**
 * Thread to update balance labels in the GUI.
 */
public class UpdateThread extends Thread {
    private final GUIHallImpl gui;
    private final Controller controller;

    /**
     * cunstroctor of the update thread.
     * @param gui the gui to update
     * @param controller the controller
     */
    public UpdateThread(final GUIHallImpl gui, final Controller controller) {
        this.gui = gui;
        this.controller = controller;
    }

    /**
     * Implementation of the thread updateThread.
     */
    @Override
    public void run() {
        while (true) {
            double balanceTot = controller.getBalanceTot();
            double balanceDay = controller.getBalanceDay();

            SwingUtilities.invokeLater(() -> {
                gui.updateBalanceLabels(balanceTot, balanceDay);
            });

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
