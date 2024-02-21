package it.unibo.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.BoxLayout;
import java.nio.file.FileSystems;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import javax.swing.border.EmptyBorder;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.util.Optional;
import java.text.DecimalFormat;
import it.unibo.controller.impl.ControllerImpl;

//CHECKSTYLE: MagicNumber OFF

/**
 * This is the view for the Hall of Benny's pizzeria.
 */
public class GUIHallImpl implements PropertyChangeListener {

    static final String SEP = File.separator;
    private ControllerImpl controller;
    private static final String TITLE = "BENNY'S PIZZA";
    private static final String BALANCE_DAY = "Daily balance : ";
    private static final String MENU_STRING = "MENU - " + TITLE;
    private static final String FONT = "Arial";
    private static final String CURRENCY = "$";
    private static final String PATH_TO_THE_ROOT = FileSystems.getDefault().getPath("").toAbsolutePath().toString();
    private static final String FILE_PATH_IN_COMMON = SEP
                                                      + "src"
                                                      + SEP
                                                      + "main"
                                                      + SEP
                                                      + "resources"
                                                      + SEP;
    private static final String FILE_PATH_BACKGROUND = FILE_PATH_IN_COMMON
                                                        + "front.png";
    private static final String FILE_PATH_CLIENT = FILE_PATH_IN_COMMON
                                                    + "clientsImages"
                                                    + SEP;
    private static final int FONT_SIZE = 25;
    private static final Random RANDOM = new Random();
    private StringBuilder sb = new StringBuilder();
    private static int lastClientShowed = 0;
    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static int width = (int) screenSize.getWidth();
    private static int height = (int) screenSize.getHeight();
    static final int MENU_BUTTON_WIDTH = (int) (width * 0.1);
    static final int MENU_BUTTON_HEIGHT = (int) (height * 0.08);
    static final int MENU_TXTAREA_WIDTH = (int) (width * 0.85);
    static final int MENU_TXTAREA_HEIGHT = (int) (height * 0.63);
    static final int CLOCK_LABEL_WIDTH = (int) (width * 0.1);
    static final int CLOCK_LABEL_HEIGHT = (int) (height * 0.05);
    private JLabel balanceTotLabel = new JLabel();
    private JLabel balanceDayLabel = new JLabel();
    private JPanel balancePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JLabel clockLabel = new JLabel();
    private JPanel clockPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    private JLabel dayLabel = new JLabel();
    private JPanel dayImagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

    /**
     * The constructor for the view of the hall.
     * @param controller
     */
    @SuppressFBWarnings(
        value = { "EI_EXPOSE_REP2"},
        justification = "trying to resolve the warning, we noticed that the game was"
            + " causing several problems, for example labels etc. were not shown"
    )
    public GUIHallImpl(final ControllerImpl controller) {
        this.controller = controller;
        createStringBuilderMenu();
        this.controller.startClientThread();
        SwingUtilities.invokeLater(() -> {
            JFrame background = new JFrame(TITLE);
            Image backgroundImage = Toolkit.getDefaultToolkit().getImage(PATH_TO_THE_ROOT
                                                                        + FILE_PATH_BACKGROUND);
            ImagePanel imagePanel = new ImagePanel(backgroundImage);
            UpdateThread updateThread = new UpdateThread(this, controller);
            updateThread.start();
            background.getContentPane().add(imagePanel);
            background.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            background.setSize(width, height);
            imagePanel.setLayout(new BorderLayout());
            OrderThread orderThread = new OrderThread(imagePanel, background);
            orderThread.start();
            displayMenu(imagePanel, background);
            displayClockLabels(imagePanel, background);
            displayWorkingDayLabels(imagePanel, background);
            displayBalanceLabels(imagePanel, background);
            displayClient(imagePanel);
            displayOrder(imagePanel, background);
        });
    }

    /**
     * It displays the order of the client.
     * @param imagePanel
     * @param background
     */
    private void displayOrder(final ImagePanel imagePanel, final JFrame background) {
        String pizzaOrder1 = controller.getClientThread().getOrder().getLeft().getName();
        Optional<String> pizzaOrder2 = Optional.empty();
        if (controller.getClientThread().getOrder().getRight().isPresent()) {
            pizzaOrder2 = Optional.of(controller.getClientThread().getOrder().getRight().get().getName());
        }
        JPanel pizzaPanel = new JPanel();
        pizzaPanel.setLayout(new BoxLayout(pizzaPanel, BoxLayout.Y_AXIS));
        JLabel pizzaLabel1 = new JLabel(pizzaOrder1);
        pizzaPanel.add(pizzaLabel1);
        if (pizzaOrder2.isPresent()) {
            JLabel pizzaLabel2 = new JLabel(pizzaOrder2.get());
            pizzaPanel.add(pizzaLabel2);
        }
        CustomDialog dialog = new CustomDialog(null, "Order", "");
        dialog.add(pizzaPanel, BorderLayout.CENTER);
        dialog.setCloseListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent e) {
                new GUIKitchen(controller, background, dayLabel).start();
            }
        });
        dialog.setVisible(true);
    }

    /**
     * It generates the string that must be displayed as menu.
     */
    private void createStringBuilderMenu() {
        for (final String pizza : controller.getMenu()) {
            this.sb.append(pizza + "\n");
        }
    }

    /**
     * It displays the menu of the pizzeria.
     * @param imagePanel
     * @param background
     */
    private void displayMenu(final ImagePanel imagePanel, final JFrame background) {
        JPanel menuPanel = new JPanel(new BorderLayout());
        setPanelAttributes(menuPanel);
        JButton menuButton = new JButton("Menu");
        setMenuButtonAttributes(menuButton);
        menuPanel.add(menuButton, BorderLayout.EAST);
        imagePanel.add(menuPanel, BorderLayout.SOUTH);
        background.setVisible(true);
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
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

    /**
     * It sets the attributes of every panel in this class.
     * @param panel
     */
    private void setPanelAttributes(final JPanel panel) {
        panel.setOpaque(false);
        panel.setBackground(new Color(0, 0, 0, 0));
        panel.setBorder(new EmptyBorder(10, 10, 50, 10));
    }

    /**
     * It sets the specific attributes of the button 'Menu'.
     * @param menuButton
     */
    private void setMenuButtonAttributes(final JButton menuButton) {
        menuButton.setBackground(new Color(Integer.parseInt("FF7F50", 16)));
        menuButton.setBorderPainted(false);
        menuButton.setFocusPainted(false);
        menuButton.setFont(new Font(FONT, Font.BOLD, 30));
        menuButton.setSize(MENU_BUTTON_WIDTH, MENU_BUTTON_WIDTH);
    }

    /**
     * Show a client different from the last showed, it return the index.
     * @return a random indexClient that must be different from the previous one.
     */ 
    private int showNewClient() {
        int indexClient;
        if (lastClientShowed != 0) {
            do {
                indexClient = randomIndexClientImage();
            } while (indexClient != lastClientShowed);
        } else {
            indexClient = randomIndexClientImage();
        }
        lastClientShowed = indexClient;
        return indexClient;
    }

    /**
     * It displays the hour during Benny's working day.
     * @param imagePanel
     * @param background
     */
    private void displayClockLabels(final ImagePanel imagePanel, final JFrame background) {
        setPanelAttributes(clockPanel);
        this.controller.newDay();
        this.controller.getTimeModel().addPropertyChangeListener(this);
        clockLabel.setFont(new Font(FONT, Font.BOLD, FONT_SIZE));
        clockLabel.setSize(CLOCK_LABEL_WIDTH, CLOCK_LABEL_HEIGHT);
        clockPanel.add(clockLabel); 
        imagePanel.add(clockPanel, BorderLayout.NORTH);
        background.setVisible(true);
    }

    /**
     * It displays the total and daily balance of the pizzeria.
     * @param imagePanel
     * @param background
     */
    private void displayBalanceLabels(final ImagePanel imagePanel, final JFrame background) {
        setPanelAttributes(balancePanel);
        balanceDayLabel.setFont(new Font(FONT, Font.BOLD, FONT_SIZE));
        balanceDayLabel.setText(BALANCE_DAY
                                + this.controller.getBalanceDay()
                                + CURRENCY);
        balancePanel.add(balanceTotLabel);
        balancePanel.add(balanceDayLabel);
        imagePanel.add(balancePanel, BorderLayout.NORTH);
        background.setVisible(true);
    }

    /**
     * It displays the number of days Benny worked.
     * @param imagePanel
     * @param background
     */
    private void displayWorkingDayLabels(final ImagePanel imagePanel, final JFrame background) {
        setPanelAttributes(dayImagePanel);
        dayLabel.setText("Day 1");
        dayLabel.setFont(new Font(FONT, Font.BOLD, FONT_SIZE));
        dayImagePanel.add(dayLabel);
        imagePanel.add(dayImagePanel, BorderLayout.NORTH);
        background.setVisible(true);
    }

    /**
     * It displays a random client.
     * @param imagePanel
     */
    private void displayClient(final ImagePanel imagePanel) {
        int indexClient = showNewClient();
        String imagePath = PATH_TO_THE_ROOT
                            + FILE_PATH_CLIENT
                            + "ClientImage"
                            + indexClient
                            + ".png";
        Image clientImage = Toolkit.getDefaultToolkit().getImage(imagePath);
        JLabel clientLabel = new JLabel(new ImageIcon(clientImage));
        int clientX = 0;
        int clientY = 0;
        switch (indexClient) {
            case 1:
                clientX = (int) (width * 0.26);
                clientY = (int) (height * 0.32);
                break; 
            case 2:
                clientX = (int) (width * 0.24);
                clientY = (int) (height * 0.40);
                break;
            case 3:
                clientX = (int) (width * 0.25);
                clientY = (int) (height * 0.41);
                break;
            default:
                break;
        }
        clientLabel.setBounds(clientX, clientY, clientImage.getWidth(null), clientImage.getHeight(null));
        imagePanel.setLayout(null);
        imagePanel.add(clientLabel);
    }

    /**
     * It updates total and daily balance after each payment, made or received.
     * @param balanceDay
     */
    public void updateBalanceLabels(final double balanceDay) {
        DecimalFormat df = new DecimalFormat("#.###");
        balanceDayLabel.setText(BALANCE_DAY 
                                + df.format(balanceDay));
    }

    /**
     * @return a random integer between 1 and 3.
     */
    private int randomIndexClientImage() {
        return RANDOM.nextInt(3) + 1;
    }

    /**
     * Depending on the signal it receives, it updates the corret label with the updated values.
     * @param evt the name of received signal.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case "time":
                SwingUtilities.invokeLater(() -> clockLabel.setText(controller.getHourAndMin()));
                break;
            case "balanceDay":
                SwingUtilities.invokeLater(() -> balanceDayLabel.setText(BALANCE_DAY
                                                                        + String.valueOf(controller.getBalanceDay())
                                                                        + CURRENCY));
                break;
            case "end":
                SwingUtilities.invokeLater(() -> {
                    JOptionPane pane = new JOptionPane(controller.getResult(), JOptionPane.INFORMATION_MESSAGE);
                    JDialog dialog = pane.createDialog("Result");
                    dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
                    dialog.setAlwaysOnTop(true);
                    pane.setOptions(new Object[]{});
                    pane.addPropertyChangeListener(e -> {
                        String prop = e.getPropertyName();
                        if (dialog.isVisible() && e.getSource() == pane && prop.equals(JOptionPane.VALUE_PROPERTY)) {
                            dialog.setVisible(false);
                            dialog.dispose();
                            close();
                        }
                    });
                    dialog.setVisible(true);
                });
                break;
            default:
                break;
        }
    }

    @SuppressFBWarnings(
        value = {"DM_EXIT"},
        justification = "trying to resolve the warning, we noticed that the game was"
            + " causing several problems, for example labels etc. were not shown"
    )
    private void close() {
        System.exit(0);
    }

    /**
     * Thread to simulate clients that make orders.
     */
    public class OrderThread extends Thread {
        private static final Lock LOCK = new ReentrantLock();
        private static final Condition COND = LOCK.newCondition();
        private ImagePanel imagePanel;
        private JFrame background;

        /**
         * Constructor of OrderThread.
         * @param imagePanel panel that contains the image.
         * @param background frame with background.
         */
        @SuppressFBWarnings(
            value = { "EI_EXPOSE_REP"},
            justification = "trying to resolve the warning, we noticed that the game was"
                + " causing several problems, for example labels etc. were not shown"
        )
        public OrderThread(final ImagePanel imagePanel, final JFrame background) {
            this.imagePanel = new ImagePanel(imagePanel);
            this.background = background;
        }

        /**
         * Method to run this thread.
         */
        @Override
        public void run() {
            while (true) {
                LOCK.lock();
                try {
                    COND.await();
                    displayOrder(imagePanel, background);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    LOCK.unlock();
                }
            }
        }

        /**
         * Method to wakeUp this thread.
         */
        public static void wakeUp() {
            LOCK.lock();
            try {
                COND.signal();
            } finally {
                LOCK.unlock();
            }
        }
    }
}
//CHECKSTYLE: MagicNumber ON
/*
 * We have decided to set the MagicNumber to OFF only for this GUI,
 * because all the MagicNumbers that are present are fundamental for the graphics
 * and in our opinion, being too many and all different from each other,
 * so it was inappropriate to include so many constants.
*/
