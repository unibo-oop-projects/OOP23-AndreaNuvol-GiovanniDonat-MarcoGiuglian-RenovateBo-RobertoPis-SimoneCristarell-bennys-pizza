package it.unibo.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;


public class CustomDialog extends JDialog{
    public CustomDialog(JFrame parent, String title, String message){
        super(parent, title, true);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel(message), BorderLayout.CENTER);
        getContentPane().add(panel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //pack();
        setSize(200, 100);
        setLocationRelativeTo(panel);
    }

    public void setCloseListener(WindowAdapter listener){
        addWindowListener(listener);
    }
}
