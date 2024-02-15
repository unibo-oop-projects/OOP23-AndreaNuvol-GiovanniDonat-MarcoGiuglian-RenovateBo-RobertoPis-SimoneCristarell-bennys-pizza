package it.unibo.view;

import java.awt.*;
import java.io.File;
import java.nio.file.FileSystems;
import javax.swing.*;

public class GUIHall {

    final static String SEP = File.separator;
    private static final String PATH_TO_THE_ROOT = FileSystems.getDefault().getPath(new String()).toAbsolutePath().toString();
    private static final String FILE_PATH = SEP         +
                                            "src"       + SEP +
                                            "main"      + SEP +
                                            "resources" + SEP +
                                            "front.png";


    static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    static int width = (int) screenSize.getWidth(); 
    static int height = (int) screenSize.getHeight();

    public GUIHall() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Image Background Example");
            Image backgroundImage = Toolkit.getDefaultToolkit().getImage(PATH_TO_THE_ROOT + FILE_PATH);
            ImagePanel imagePanel = new ImagePanel(backgroundImage);
            frame.getContentPane().add(imagePanel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(width, height);
            frame.setVisible(true);
        });
    }    
}
