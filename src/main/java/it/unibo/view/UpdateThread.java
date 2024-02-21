package it.unibo.view;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.SwingUtilities;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
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
    @SuppressFBWarnings(
        value = { "EI_EXPOSE_REP2"},
        justification = "trying to resolve the warning, we noticed that the game was"
            + " causing several problems, for example labels etc. were not shown"
    )
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
            final double balanceDay = controller.getBalanceDay();
            SwingUtilities.invokeLater(() -> {
                gui.updateBalanceLabels(balanceDay);
            });

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                final Logger logger = Logger.getLogger(UpdateThread.class.getName());
                logger.log(Level.WARNING, e.toString());
            }
        }
    }
}
