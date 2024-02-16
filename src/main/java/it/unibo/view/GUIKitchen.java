package it.unibo.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.FileSystems;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unibo.controller.api.Controller;
import it.unibo.controller.impl.ControllerImpl;

public class GUIKitchen {

    private static final String SEP = File.separator;
    private static final String PATH_TO_THE_ROOT = FileSystems.getDefault().getPath(new String()).toAbsolutePath().toString();
    private static final String PATH_TO_RESOURCES = SEP + "src" + SEP + "main" + SEP + "resources" + SEP;

    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final int screenWidth = (int)screenSize.getWidth();
    private final int screenHeight = (int)screenSize.getHeight();

    private final JFrame frame = new JFrame("KITCHEN");
    
    public GUIKitchen(final Controller controller) {
        final Image background = Toolkit.getDefaultToolkit().getImage(PATH_TO_THE_ROOT + PATH_TO_RESOURCES + 
            "Preparation_Zone.png");
        ImagePanel imagePanel = new ImagePanel(background);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(imagePanel, BorderLayout.CENTER);
        imagePanel.setLayout(new BorderLayout());
        final JPanel highPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, (int)(screenWidth / 35), 10));

        final JLabel lblDay = new JLabel("Day: 16/02/2024");
        highPanel.add(lblDay);
        highPanel.add(new JLabel("18:15"));
        highPanel.add(new JLabel("Balance: 50€"));
        imagePanel.add(highPanel, BorderLayout.NORTH);

        frame.setSize(screenWidth, screenHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void start() {
        frame.setVisible(true);
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        new GUIKitchen(new ControllerImpl()).start();;
    }

}
