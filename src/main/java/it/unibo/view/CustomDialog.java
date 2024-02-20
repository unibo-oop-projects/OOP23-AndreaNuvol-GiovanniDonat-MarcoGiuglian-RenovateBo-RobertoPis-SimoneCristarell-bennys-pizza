package it.unibo.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;

/**
 * Modified JDialog to change some features.
 */
public class CustomDialog extends JDialog {
    private static final int WIDTH = 200;
    private static final int HEIGHT = 100;

    /**
     * it create a custom dialog to show the order
     * @param parent is the father frame
     * @param title is the title of the customDialog
     * @param message is the message to show
     */
    public CustomDialog(final JFrame parent, final String title, final String message) {
        super(parent, title, false);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel(message), BorderLayout.CENTER);
        getContentPane().add(panel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(panel);
        setAlwaysOnTop(true);
        setResizable(false);
    }

    public void setCloseListener(WindowAdapter listener) {
        addWindowListener(listener);
    }
}
