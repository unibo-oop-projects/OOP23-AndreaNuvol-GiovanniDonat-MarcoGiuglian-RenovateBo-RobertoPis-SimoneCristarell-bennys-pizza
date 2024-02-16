package it.unibo.view;

import java.awt.*;
import java.io.File;
import java.nio.file.FileSystems;
import javax.swing.*;
import java.util.Random;

public class GUIHall {

    final static String SEP = File.separator;
    private static final String BALANCE_TOT = "Balance tot: ";
    private static final String BALANCE_DAY = "Balance day: ";
    private static final String PATH_TO_THE_ROOT = FileSystems.getDefault().getPath(new String()).toAbsolutePath().toString();
    private static final String FILE_PATH_IN_COMMON = SEP         +
                                                    "src"       + SEP +
                                                    "main"      + SEP +
                                                    "resources" + SEP;
    private static final String FILE_PATH_BACKGROUND = FILE_PATH_IN_COMMON + "front.png";
    private static final String FILE_PATH_CLIENT = FILE_PATH_IN_COMMON + "clientsImages" + SEP;
    private static int lastClientShowed = 0;

    static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    static int width = (int) screenSize.getWidth(); 
    static int height = (int) screenSize.getHeight();

    public GUIHall() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Image Background Example");
            Image backgroundImage = Toolkit.getDefaultToolkit().getImage(PATH_TO_THE_ROOT + FILE_PATH_BACKGROUND);
            ImagePanel imagePanel = new ImagePanel(backgroundImage);
            frame.getContentPane().setLayout(new BorderLayout()); // Imposta il layout manager del frame
            frame.getContentPane().add(imagePanel, BorderLayout.CENTER);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(width, height);
            frame.setVisible(true);

            displayLabelsBalance(imagePanel);
            displayClient(imagePanel);
            
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

    // Show a client different from the last showed, it return the index 
    private int showNewClient(){
        int indexClient;
        if(lastClientShowed != 0){
            do{
                indexClient = randomIndexClientImage();
            }while(indexClient != lastClientShowed);
        }else{
            indexClient = randomIndexClientImage();
        }
        lastClientShowed = indexClient;
        return indexClient;
    }

    private void displayClient(final ImagePanel imagePanel){
        int indexClient = showNewClient();
        String imagePath = PATH_TO_THE_ROOT + FILE_PATH_CLIENT + "ClientImage" + indexClient + ".png";
        Image clientImage = Toolkit.getDefaultToolkit().getImage(imagePath);
        JLabel clientLabel = new JLabel(new ImageIcon(clientImage));
        int clientX = 0;
        int clientY = 0;
        switch (indexClient) {
            case 1:
                clientX = 450;
                clientY = 342;
                break; 
            case 2:
                clientX = 450;
                clientY = 425;
                break;
            case 3:
                clientX = 450;
                clientY = 440;
                break;
        }
        
        clientLabel.setBounds(clientX, clientY, clientImage.getWidth(null), clientImage.getHeight(null));
        imagePanel.setLayout(null);
        imagePanel.add(clientLabel);
    }

    private int randomIndexClientImage(){
        Random random = new Random();
        return random.nextInt(3) + 1;
    }
}
