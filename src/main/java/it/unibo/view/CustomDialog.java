package it.unibo.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;


public class CustomDialog extends JDialog{
    public CustomDialog(JFrame parent, String title, String message){
        super(parent, title, false);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel(message), BorderLayout.CENTER);
        getContentPane().add(panel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(200, 100);
        setLocationRelativeTo(panel);
        setAlwaysOnTop(true);
        setResizable(false);
    }

    public void setCloseListener(WindowAdapter listener){
        addWindowListener(listener);
    }
}
