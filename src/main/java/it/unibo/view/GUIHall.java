package it.unibo.view;

import java.awt.*;
import java.io.File;
import java.nio.file.FileSystems;
import javax.swing.*;

public class GUIHall {

    final static String SEP = File.separator;
    private static final String BALANCE_TOT = "Balance tot: ";
    private static final String BALANCE_DAY = "Balance day: ";
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
            frame.getContentPane().setLayout(new BorderLayout()); // Imposta il layout manager del frame
            frame.getContentPane().add(imagePanel, BorderLayout.CENTER);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(width, height);
            frame.setVisible(true);


            displayLabelsBalance(imagePanel);
            
        });
    }

    public static void main(String[] args) {
        new GUIHall();
    }

    private void displayLabelsBalance(final ImagePanel imagePanel){
        JLabel labelTotalBalance = new JLabel(BALANCE_TOT);
        JLabel labelDayBalance = new JLabel(BALANCE_DAY);
        Font fontLabelBalanceTot = labelTotalBalance.getFont().deriveFont(Font.BOLD, 20);
        Font fontLabelBalanceDay = labelTotalBalance.getFont().deriveFont(Font.BOLD, 25);
        labelTotalBalance.setFont(fontLabelBalanceTot);
        labelDayBalance.setFont(fontLabelBalanceDay);
        imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.Y_AXIS));
        imagePanel.add(labelTotalBalance);
        imagePanel.add(labelDayBalance);
    }
}
