package it.unibo.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.FileSystems;
import javax.swing.*;
import java.util.Random;
import javax.swing.border.EmptyBorder;
import java.util.Optional;
import it.unibo.controller.impl.ControllerImpl;
import org.apache.commons.lang3.tuple.Pair;

public class GUIHallImpl implements PropertyChangeListener {

    final static String SEP = File.separator;
    private ControllerImpl controller;
    private static final String TITLE = "BENNY'S PIZZA";
    private static final String BALANCE_TOT = "Total balance : ";
    private static final String BALANCE_DAY = "Daily balance : ";
    private static final String MENU_STRING = "MENU - " + TITLE;
    private static final String FONT = "Arial";
    private static final String CURRENCY = "$";
    private static final String PATH_TO_THE_ROOT = FileSystems.getDefault().getPath(new String()).toAbsolutePath().toString();
    private static final String FILE_PATH_IN_COMMON = SEP         +
                                                    "src"       + SEP +
                                                    "main"      + SEP +
                                                    "resources" + SEP;
    private static final String FILE_PATH_BACKGROUND = FILE_PATH_IN_COMMON + "front.png";
    private static final String FILE_PATH_CLIENT = FILE_PATH_IN_COMMON + "clientsImages" + SEP;
    
    private StringBuilder sb = new StringBuilder();
    private static int lastClientShowed = 0;

    static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    static int width = (int) screenSize.getWidth();
    static int height = (int) screenSize.getHeight();

    final static int MENU_BUTTON_WIDTH = (int)(width * 0.1);
    final static int MENU_BUTTON_HEIGHT = (int)(height * 0.08);
    final static int MENU_TXTAREA_WIDTH = (int)(width * 0.85);
    final static int MENU_TXTAREA_HEIGHT = (int)(height * 0.63);

    final static int CLOCK_LABEL_WIDTH = (int)(width * 0.1);
    final static int CLOCK_LABEL_HEIGHT = (int)(height * 0.05);
    
    private JLabel balanceTotLabel = new JLabel();
    private JLabel balanceDayLabel = new JLabel();
    private JPanel balancePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

    private JLabel clockLabel = new JLabel();
    private JPanel clockPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

    private JLabel dayLabel = new JLabel();
    private JPanel dayImagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));


    public GUIHallImpl(final ControllerImpl controller) {
        this.controller = controller;
        createStringBuilderMenu();
        
        SwingUtilities.invokeLater(() -> {
            JFrame background = new JFrame(TITLE);
            Image backgroundImage = Toolkit.getDefaultToolkit().getImage(PATH_TO_THE_ROOT + FILE_PATH_BACKGROUND);
            ImagePanel imagePanel = new ImagePanel(backgroundImage);
            background.getContentPane().add(imagePanel);
            background.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            background.setSize(width, height);
            imagePanel.setLayout(new BorderLayout());

            displayMenu(imagePanel, background);
            displayClockLabels(imagePanel, background);
            displayWorkingDayLabels(imagePanel, background);
            displayBalanceLabels(imagePanel, background);
            displayClient(imagePanel);
            displayOrder(imagePanel, background);
        });
    }

    private void displayOrder(final ImagePanel imagePanel, final JFrame background){
        Pair<String, Optional<String>> order = controller.getClientThread().getOrder();
        String pizzaOrder = order.getLeft();
        Optional<String> optionalSecondPizza = order.getRight();
        JPanel pizzaPanel = new JPanel();
        pizzaPanel.setLayout(new BoxLayout(pizzaPanel, BoxLayout.Y_AXIS));
        JLabel pizzaLabel1 = new JLabel(pizzaOrder);
        pizzaPanel.add(pizzaLabel1);
        
        if(optionalSecondPizza.isPresent()){
            JLabel pizzaLabel2 = new JLabel(optionalSecondPizza.get());
            pizzaPanel.add(pizzaLabel2);
        }

        CustomDialog dialog = new CustomDialog(null, "Order", "");
        dialog.add(pizzaPanel, BorderLayout.CENTER);
        dialog.setCloseListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                // Azione da eseguire quando viene chiusa la finestra di dialogo
                controller.addToBalance(55);
            }
        });
        dialog.setVisible(true);
    }

    private void createStringBuilderMenu() {
        for(final String pizza : controller.getMenu()) {
            this.sb.append(pizza + "\n");
        }
    }

    private void displayMenu(final ImagePanel imagePanel, final JFrame background) {
        JPanel menuPanel = new JPanel(new BorderLayout());
        setPanelAttributes(menuPanel);
        JButton menuButton = new JButton("Menu");
        setMenuButtonAttributes(menuButton);
        menuPanel.add(menuButton, BorderLayout.EAST);
        imagePanel.add(menuPanel, BorderLayout.SOUTH);
        background.setVisible(true); // to be set to false when switching to the kitchen screen
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JOptionPane.showOptionDialog(
                    null,
                    sb,
                    MENU_STRING,
                    JOptionPane.CLOSED_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null, 
                    null,
                    null
                );
            }
        });
    }

    private void setPanelAttributes(final JPanel panel) {
        panel.setOpaque(false);
        panel.setBackground(new Color(0, 0, 0, 0));
        panel.setBorder(new EmptyBorder(10, 10, 50, 10));
    }

    private void setMenuButtonAttributes(final JButton menuButton) {
        menuButton.setBackground(new Color(Integer.parseInt("FF7F50", 16)));
        menuButton.setBorderPainted(false);
        menuButton.setFocusPainted(false);
        menuButton.setFont(new Font(FONT, Font.BOLD, 30));
        menuButton.setSize(MENU_BUTTON_WIDTH, MENU_BUTTON_WIDTH);
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

    private void displayClockLabels(final ImagePanel imagePanel, final JFrame background){
        setPanelAttributes(clockPanel);
        
        this.controller.newDay();
        this.controller.getTimeModel().addPropertyChangeListener(this);

        clockLabel.setFont(new Font(FONT, Font.BOLD, 25));
        clockLabel.setSize(CLOCK_LABEL_WIDTH, CLOCK_LABEL_HEIGHT);

        clockPanel.add(clockLabel); 
        imagePanel.add(clockPanel, BorderLayout.NORTH);
        
        background.setVisible(true);
    }

    private void displayBalanceLabels(final ImagePanel imagePanel, final JFrame background){
        setPanelAttributes(balancePanel);
        this.controller.getAdderManagerModel().addPropertyChangeListener(this);
        this.controller.getSubtractorManagerModel().addPropertyChangeListener(this);
        
        balanceDayLabel.setFont(new Font(FONT, Font.BOLD, 25));
        balanceTotLabel.setFont(new Font(FONT, Font.BOLD, 25));
        balanceTotLabel.setText(BALANCE_TOT + this.controller.getBalanceTot() + CURRENCY);
        balanceDayLabel.setText(BALANCE_DAY + this.controller.getBalanceDay() + CURRENCY);
        balancePanel.add(balanceTotLabel);
        balancePanel.add(balanceDayLabel);
        imagePanel.add(balancePanel, BorderLayout.NORTH);
        background.setVisible(true);
    }

    private void displayWorkingDayLabels(final ImagePanel imagePanel, final JFrame background){
        setPanelAttributes(dayImagePanel);
    
        dayLabel.setText("Day " + String.valueOf(this.controller.getWorkingDay()));
        dayLabel.setFont(new Font(FONT, Font.BOLD, 25));
        
        dayImagePanel.add(dayLabel);
        imagePanel.add(dayImagePanel, BorderLayout.NORTH);
        
        background.setVisible(true);
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

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case "time":
                SwingUtilities.invokeLater(() -> clockLabel.setText(controller.getHourAndMin()));
                break;

            case "day" :
                SwingUtilities.invokeLater(() -> dayLabel.setText((String.valueOf(controller.getWorkingDay()))));
                break;
        
            case "balanceDay" :
                SwingUtilities.invokeLater(() -> balanceDayLabel.setText(BALANCE_DAY + String.valueOf(controller.getBalanceDay()) + CURRENCY));
                break;

            case "balanceTot" :
                SwingUtilities.invokeLater(() -> balanceTotLabel.setText(BALANCE_TOT + String.valueOf(controller.getBalanceTot()) + CURRENCY));
                break;

            default:
                break;
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        new GUIHallImpl(new ControllerImpl());
    }
}