package it.unibo.view;

import java.awt.*;
import java.io.File;
import java.nio.file.FileSystems;
import javax.swing.*;
import java.util.Random;
import javax.swing.border.EmptyBorder;

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

    final static int MENU_WIDTH = (int)(width * 0.1);
    final static int MENU_HEIGHT = (int)(height * 0.08);

    public GUIHall() {
        SwingUtilities.invokeLater(() -> {
            JFrame background = new JFrame("BENNY'S PIZZA");
            Image backgroundImage = Toolkit.getDefaultToolkit().getImage(PATH_TO_THE_ROOT + FILE_PATH_BACKGROUND);
            ImagePanel imagePanel = new ImagePanel(backgroundImage);
            
            frontView(imagePanel, background);
            displayLabelsBalance(imagePanel);
            displayClient(imagePanel);
            
        });
    }

    private void frontView(ImagePanel imagePanel, JFrame background) {
        
        imagePanel.setLayout(new BorderLayout());

        background.getContentPane().add(imagePanel);
        background.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        background.setSize(width, height);

        JPanel menuPanel = new JPanel(new BorderLayout());
        setMenuPanelAttributes(menuPanel);

        JButton menuButton = new JButton("Menu");
        setMenuButtonAttributes(menuButton);
        
        menuPanel.add(menuButton, BorderLayout.EAST);
        imagePanel.add(menuPanel, BorderLayout.SOUTH);
        
        background.setVisible(true); // da mettere false quando si passa alla schermata della cucina 
    }

    private void setMenuPanelAttributes(JPanel menuPanel) {
        menuPanel.setOpaque(false);
        menuPanel.setBackground(new Color(0, 0, 0, 0));
        menuPanel.setBorder(new EmptyBorder(10, 10, 50, 10));
    }

    private void setMenuButtonAttributes(JButton menuButton) {
        menuButton.setBackground(new Color(Integer.parseInt("FF7F50", 16)));
        menuButton.setBorderPainted(false);
        menuButton.setFocusPainted(false);
        menuButton.setFont(new Font("Arial", Font.BOLD, 30));
        menuButton.setSize(MENU_WIDTH, MENU_WIDTH);
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
                clientX = (int)(width * 0.26);
                clientY = (int)(height * 0.32);
                break; 
            case 2:
                clientX = (int)(width * 0.24);
                clientY = (int)(height * 0.40);
                break;
            case 3:
                clientX = (int)(width * 0.25);
                clientY = (int)(height * 0.41);
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

    public static void main(String[] args) {
        new GUIHall();
    }

}
