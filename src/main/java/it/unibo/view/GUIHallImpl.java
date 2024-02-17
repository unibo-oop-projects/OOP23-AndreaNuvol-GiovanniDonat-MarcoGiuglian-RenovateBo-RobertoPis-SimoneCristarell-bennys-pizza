package it.unibo.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.FileSystems;
import javax.swing.*;
import java.util.Random;
import javax.swing.border.EmptyBorder;
import java.util.Optional;
import it.unibo.controller.api.Controller;
import it.unibo.controller.impl.ControllerImpl;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

public class GUIHallImpl {

    final static String SEP = File.separator;
    private static final String BALANCE_TOT = "Balance tot: ";
    private static final String BALANCE_DAY = "Balance day: ";
    private static final String MENU_STRING = "MENU - BENNY'S PIZZA";
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

    final static int MENU_BUTTON_WIDTH = (int)(width * 0.1);
    final static int MENU_BUTTON_HEIGHT = (int)(height * 0.08);
    final static int MENU_TXTAREA_WIDTH = (int)(width * 0.85);
    final static int MENU_TXTAREA_HEIGHT = (int)(height * 0.63);

    public GUIHallImpl(final Controller controller) {
        SwingUtilities.invokeLater(() -> {
            JFrame background = new JFrame("BENNY'S PIZZA");
            Image backgroundImage = Toolkit.getDefaultToolkit().getImage(PATH_TO_THE_ROOT + FILE_PATH_BACKGROUND);
            ImagePanel imagePanel = new ImagePanel(backgroundImage);
            background.getContentPane().add(imagePanel);
            background.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            background.setSize(width, height);
            imagePanel.setLayout(new BorderLayout());

            displayMenu(imagePanel, background, controller);
            displayClockLabels(imagePanel, background);
            displayWorkingDayLabels(imagePanel, background);
            displayBalanceLabels(imagePanel, background, controller);
            displayClient(imagePanel);

            controller.generateMenu();// generation of menu
            Pair<String, Optional<String>> order = controller.order();
            String pizzaOrder = order.getLeft();
            Optional<String> optionalSecondPizza = order.getRight();
            if(optionalSecondPizza.isPresent()){
                pizzaOrder += "\n" + optionalSecondPizza.get();
            }
            Object[] options = {"OK"};
            int res = JOptionPane.showOptionDialog(
                null,
                pizzaOrder,
                "ORDER",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]
            );
            if(res == JOptionPane.OK_OPTION){
                // qui passa alla cucina 
            }

        });
    }

    public void displayMenu(final ImagePanel imagePanel, final JFrame background, final Controller controller) {
        JPanel menuPanel = new JPanel(new BorderLayout());
        setPanelAttributes(menuPanel);
        JButton menuButton = new JButton("Menu");
        setMenuButtonAttributes(menuButton);
        menuPanel.add(menuButton, BorderLayout.EAST);
        imagePanel.add(menuPanel, BorderLayout.SOUTH);
        background.setVisible(true); // da mettere false quando si passa alla schermata della cucina 

        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new JDialog();
                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                dialog.setSize(MENU_TXTAREA_WIDTH, MENU_TXTAREA_HEIGHT); // da rendere portabileee!!

                JTextArea textArea = new JTextArea(MENU_STRING);
                StringBuilder sb = new StringBuilder();
                sb.append(MENU_STRING + "\n\n");
                for(final String pizza : controller.getMenu()) {
                    sb.append(pizza + "\n");
                }
                textArea.setText(sb.toString());

                textArea.setFont(new Font("Arial", Font.BOLD, 15)); // Imposta il tipo e la dimensione del carattere
                dialog.getContentPane().add(textArea); // Aggiunge il messaggio al dialogo

                dialog.setVisible(true); // Rende visibile il dialogo
            }
        });
    }

    public static void displayBalanceLabels(final ImagePanel imagePanel, final JFrame background, final Controller controller){
        JPanel balanceLabelsPanel = new JPanel();
        balanceLabelsPanel.setLayout(new BoxLayout(balanceLabelsPanel, BoxLayout.Y_AXIS));
        setPanelAttributes(balanceLabelsPanel);
        controller.addToBalance(5);
        JLabel balanceTotLabel = new JLabel(BALANCE_TOT + Double.toString(controller.getBalanceTot()));
        JLabel balanceDayLabel = new JLabel(BALANCE_DAY + Double.toString(controller.getBalanceDay()));
        Font fontLabelBalanceTot = balanceTotLabel.getFont().deriveFont(Font.BOLD, 20);
        Font fontLabelBalanceDay = balanceDayLabel.getFont().deriveFont(Font.BOLD, 25);
        balanceTotLabel.setFont(fontLabelBalanceTot);
        balanceDayLabel.setFont(fontLabelBalanceDay);
        balanceLabelsPanel.add(balanceTotLabel);
        balanceLabelsPanel.add(balanceDayLabel);
        imagePanel.add(balanceLabelsPanel, BorderLayout.NORTH);
        background.setVisible(true);
    }

    private static void setPanelAttributes(final JPanel panel) {
        panel.setOpaque(false);
        panel.setBackground(new Color(0, 0, 0, 0));
        panel.setBorder(new EmptyBorder(10, 10, 50, 10));
    }

    private static void setMenuButtonAttributes(final JButton menuButton) {
        menuButton.setBackground(new Color(Integer.parseInt("FF7F50", 16)));
        menuButton.setBorderPainted(false);
        menuButton.setFocusPainted(false);
        menuButton.setFont(new Font("Arial", Font.BOLD, 30));
        menuButton.setSize(MENU_BUTTON_WIDTH, MENU_BUTTON_WIDTH);
    }

    // Show a client different from the last showed, it return the index 
    private static int showNewClient(){
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

    public static void displayClockLabels(final ImagePanel imagePanel, final JFrame background){
        JPanel clockPanel = new JPanel(new BorderLayout());
        setPanelAttributes(clockPanel);

        JLabel clockLabel = new JLabel("Ora : Minuti");
        clockLabel.setFont(new Font("Arial", Font.BOLD, 25));
        clockLabel.setSize(300, 300); // da rendere portabile!!

        clockPanel.add(clockLabel, BorderLayout.EAST);
        imagePanel.add(clockPanel, BorderLayout.NORTH);
        
        background.setVisible(true);
    }

    
    public static void displayWorkingDayLabels(final ImagePanel imagePanel, final JFrame background){
        JPanel workingDayPanel = new JPanel(new GridBagLayout());
        setPanelAttributes(workingDayPanel);
    
        JLabel workingDayLabel = new JLabel("Numero giornata");
        workingDayLabel.setFont(new Font("Arial", Font.BOLD, 25));
        workingDayLabel.setSize(300, 300); // da rendere portabile!!
    
        GridBagConstraints gbc = new GridBagConstraints();
    
        workingDayPanel.add(workingDayLabel, gbc);
        imagePanel.add(workingDayPanel, BorderLayout.NORTH);
        
        background.setVisible(true);
    }

    public static void displayClient(final ImagePanel imagePanel){
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

    private static int randomIndexClientImage(){
        Random random = new Random();
        return random.nextInt(3) + 1;
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        new GUIHallImpl(new ControllerImpl());
    }

}
