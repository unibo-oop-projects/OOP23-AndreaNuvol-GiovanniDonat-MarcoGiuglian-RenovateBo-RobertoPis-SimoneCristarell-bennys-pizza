package it.unibo.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.nio.file.FileSystems;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import it.unibo.controller.api.Controller;

//CHECKSTYLE: MagicNumber OFF

/**
 * This is the view of the Kitchen of Benny's pizzeria.
 */
public class GUIKitchen {

    private static final String SEP = File.separator;
    private static final String ERROR_STRING = "Error";
    private static final String PATH_TO_THE_ROOT = FileSystems.getDefault().getPath("").toAbsolutePath().toString();
    private static final String PATH_TO_RESOURCES = SEP + "src" + SEP + "main" + SEP + "resources" + SEP;

    private static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    private static final int SCREEN_WIDTH = (int) SCREEN_SIZE.getWidth();
    private static final int SCREEN_HEIGHT = (int) SCREEN_SIZE.getHeight();

    private static final String FONT = "Arial";
    private static final int FONT_SIZE = 25;
    private static final String BALANCE_DAY = "Daily balance : ";
    private static final String CURRENCY = "$";
    static final int CLOCK_LABEL_WIDTH = (int) (SCREEN_WIDTH * 0.1);
    static final int CLOCK_LABEL_HEIGHT = (int) (SCREEN_HEIGHT * 0.05);

    static final int NUMBER_OF_INGREDIENTS = 18;

    private final JFrame frame = new JFrame("KITCHEN");

    private final Map<String, JLabel> ingredientLabelsMapPizza1 = new HashMap<>();
    private final Map<String, JLabel> ingredientLabelsMapPizza2 = new HashMap<>();

    /**
     * The constructor for the view of the kitchen.
     * @param controller
     */
    public GUIKitchen(final Controller controller) {
        frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        final Image background = Toolkit.getDefaultToolkit().getImage(PATH_TO_THE_ROOT 
                                                                        + PATH_TO_RESOURCES 
                                                                        + "Preparation_Zone.png");
        final ImagePanel imagePanel = new ImagePanel(background);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(imagePanel, BorderLayout.CENTER);
        imagePanel.setLayout(new BorderLayout());

        displayInfoLabels(imagePanel, frame.getWidth(), controller);

        final JPanel lowPanel = new JPanel(new BorderLayout());
        lowPanel.setOpaque(false);

        final JPanel lowEastPanel = new JPanel();
        lowEastPanel.setOpaque(false);
        final Image garbageBin = Toolkit.getDefaultToolkit().getImage(PATH_TO_THE_ROOT 
                                                                        + PATH_TO_RESOURCES 
                                                                        + "KitchenComponentsImages" 
                                                                        + SEP 
                                                                        + "GarbageBin.png");
        final JButton btnGarbageBin = new JButton();
        displayGarbageBinButton(btnGarbageBin, garbageBin, frame.getWidth(), frame.getHeight(), lowEastPanel);
        lowEastPanel.add(btnGarbageBin);
        lowPanel.add(lowEastPanel, BorderLayout.EAST);

        final JPanel centralPanel = new JPanel(new BorderLayout());
        centralPanel.setOpaque(false);

        final JPanel centralNorthPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 
            (int) (frame.getWidth() * 0.002), (int) (frame.getHeight() * 0.01)));
        centralNorthPanel.setOpaque(false);
        final JLabel lblSelect = new JLabel("Select the ingredient to supply:");
        centralNorthPanel.add(lblSelect);
        final String[] items = { "Anchovies", "Artichokes", "CherryTomatoes", "Fontina", 
                                "FrenchFries", "Gorgonzola", "Ham", 
                                "Wurstel", "Mushrooms", "Olives", "Onions", 
                                "Parmesan", "Salami", "Sausages", "Tuna", 
                                "Mozzarella", "TomatoeSauce", "Dough" };
        final JComboBox<String> comboBox = new JComboBox<>(items);

        final Map<String, ImageIcon> itemImageMap = new HashMap<>();
        for (final String item : items) {
            itemImageMap.put(item, new ImageIcon(PATH_TO_THE_ROOT
                                                        + PATH_TO_RESOURCES
                                                        + "IngredientsButtonsIcons"
                                                        + SEP + item + ".png"));
        }
        comboBox.setRenderer(new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(final JList<?> list, 
                                                                final Object value,
                                                                final int index, 
                                                                final boolean isSelected,
                                                                final boolean cellHasFocus) {
                    final JLabel label = (JLabel) super.getListCellRendererComponent(
                        list, value, index, isSelected, cellHasFocus);
                    // Get the icon for the current item
                    final ImageIcon icon = new ImageIcon(itemImageMap.get((String) value).getImage()
                        .getScaledInstance(80, 80, 0));
                    // Set the icon and text for the label
                    label.setIcon(icon);
                    final int quantity = controller.getIngredientQuantity((String) value);
                    label.setText((String) value + "  Q: " + (quantity >= 0 ? quantity : 0));
                    return label;
                }
        });

        centralNorthPanel.add(comboBox);
        final JButton btnSupply = new JButton("Supply");
        final JButton btnAdd = new JButton("Add");
        centralNorthPanel.add(btnAdd);
        centralNorthPanel.add(btnSupply);
        displaySupplyComponents(frame.getWidth(), frame.getHeight(), comboBox, btnSupply, btnAdd, centralNorthPanel);
        centralPanel.add(centralNorthPanel, BorderLayout.NORTH);

        final JPanel rightPanel = new JPanel();
        rightPanel.setOpaque(false);
        final JButton btnEndingKitchen = new JButton("Ready");
        btnEndingKitchen.setBackground(new Color(255, 255, 255, 255));
        btnEndingKitchen.setEnabled(false);
        rightPanel.add(btnEndingKitchen);
        displayEndingKitchen(btnEndingKitchen, frame.getWidth(), frame.getHeight(), rightPanel);
        imagePanel.add(rightPanel, BorderLayout.EAST);

        final JPanel centralSouthPanel = new JPanel();
        centralSouthPanel.setOpaque(false);
        final JButton btnOven = new JButton("Bake");
        btnOven.setBackground(new Color(181, 151, 106, 255));
        centralSouthPanel.add(btnOven);
        displayOven(btnOven, frame.getWidth(), frame.getHeight(), centralSouthPanel);
        centralPanel.add(centralSouthPanel, BorderLayout.SOUTH);

        final JPanel centralCentralPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        centralCentralPanel.setOpaque(false);
        centralPanel.add(centralCentralPanel, BorderLayout.CENTER);

        final JPanel blockPizza1 = new JPanel();
        blockPizza1.setOpaque(false);
        final JPanel blockPizza2 = new JPanel();
        blockPizza1.setBorder(new EmptyBorder(0, 0, (int) (frame.getHeight() * 0.1), 0));
        blockPizza2.setBorder(new EmptyBorder(0, 0, (int) (frame.getHeight() * 0.1), 0));
        blockPizza2.setOpaque(false);
        blockPizza1.setLayout(new BoxLayout(blockPizza1, BoxLayout.Y_AXIS));
        blockPizza2.setLayout(new BoxLayout(blockPizza2, BoxLayout.Y_AXIS));
        final JCheckBox pizza1 = new JCheckBox();
        final JCheckBox pizza2 = new JCheckBox();

        displayIngredients(items, blockPizza1, pizza1);
        displayIngredients(items, blockPizza2, pizza2);

        centralCentralPanel.add(blockPizza1);
        centralCentralPanel.add(blockPizza2);
        centralCentralPanel.setBorder(new EmptyBorder(0, (int) (frame.getWidth() * 0.07), 200, 0));
        imagePanel.add(centralPanel, BorderLayout.CENTER);
        imagePanel.add(lowPanel, BorderLayout.SOUTH);

        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(final ComponentEvent e) {
                final int width = frame.getContentPane().getWidth();
                final int height = frame.getContentPane().getHeight();
                displayInfoLabels(imagePanel, width, controller);
                displayGarbageBinButton(btnGarbageBin, garbageBin, width, height, lowEastPanel);
                displaySupplyComponents(width, height, comboBox, btnSupply, btnAdd, centralNorthPanel);
                displayEndingKitchen(btnEndingKitchen, width, height, rightPanel);
                displayOven(btnOven, width, height, centralSouthPanel);
            }
        });

        pizza2.setEnabled(controller.getClientThread().getOrder().getRight().isPresent());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        btnGarbageBin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    checkSelectedBox(pizza1, pizza2);
                    if (pizza1.isSelected()) {
                        disenableIngredientsLabels(controller, true, ingredientLabelsMapPizza1);
                        controller.throwPizzaInGarbageBin(true);
                    }
                    if (pizza2.isSelected()) {
                        disenableIngredientsLabels(controller, false, ingredientLabelsMapPizza2);
                        controller.throwPizzaInGarbageBin(false);
                    }
                } catch (IllegalStateException bottonGarbageBinException) {
                    JOptionPane.showMessageDialog(frame, bottonGarbageBinException.getMessage(),
                    ERROR_STRING, JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnAdd.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    checkSelectedBox(pizza1, pizza2);
                    if (pizza1.isSelected()) {
                        controller.addIngredient(comboBox.getSelectedItem().toString(), true);
                        ingredientLabelsMapPizza1.get(comboBox.getSelectedItem().toString()).setVisible(true);
                    }
                    if (pizza2.isSelected()) {
                        controller.addIngredient(comboBox.getSelectedItem().toString(), false);
                        ingredientLabelsMapPizza2.get(comboBox.getSelectedItem().toString()).setVisible(true);
                    }
                } catch (IllegalStateException bottonAddException) {
                    JOptionPane.showMessageDialog(frame, bottonAddException.getMessage(),
                        ERROR_STRING, JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnEndingKitchen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    controller.getClientThread().wakeUp();
                    frame.dispose();
                } catch (IllegalStateException bottonEndingKitchenException) {
                    JOptionPane.showMessageDialog(frame, bottonEndingKitchenException.getMessage(),
                    ERROR_STRING, JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnSupply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    controller.supply(comboBox.getSelectedItem().toString());
                } catch (IllegalArgumentException bottonSupplyException) {
                    JOptionPane.showMessageDialog(frame, bottonSupplyException.getMessage(),
                        ERROR_STRING, JOptionPane.ERROR_MESSAGE);
                }
            } 
        });

        btnOven.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    checkSelectedBox(pizza1, pizza2);
                    if (pizza1.isSelected() 
                    && !controller.getPreparationZone().getPizza1().getAddedIngredients().equals(Collections.EMPTY_LIST)) {
                        disenableIngredientsLabels(controller, true, ingredientLabelsMapPizza1);
                        bakingOp(pizza1, controller);
                    }
                    if (pizza2.isSelected() 
                    && !controller.getPreparationZone().getPizza2().get().getAddedIngredients().equals(Collections.EMPTY_LIST)) {
                        disenableIngredientsLabels(controller, false, ingredientLabelsMapPizza2);
                        bakingOp(pizza2, controller);
                    }
                    if (!pizza1.isEnabled() && !pizza2.isEnabled()) {
                        btnEndingKitchen.setEnabled(true);
                    }
                } catch (IllegalStateException | InterruptedException bottonOvenException) {
                    JOptionPane.showMessageDialog(frame, bottonOvenException.getMessage(),
                        ERROR_STRING, JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void bakingOp(final JCheckBox pizza, final Controller controller) throws InterruptedException {
        pizza.setEnabled(false);
        pizza.setSelected(false);
        controller.bakingPizza();
        Thread.sleep(1500);
        JOptionPane.showMessageDialog(frame, "Pizza is baked!", "INFO", JOptionPane.INFORMATION_MESSAGE);
    }

    private void disenableIngredientsLabels(final Controller controller, final boolean isPizza1, final Map<String, JLabel> map) {
        for (final var ingredient : isPizza1 
                ? controller.getPreparationZone().getPizza1().getAddedIngredients() 
                : controller.getPreparationZone().getPizza2().get().getAddedIngredients()) {
            if (map.get(ingredient.toString()).isVisible()) {
                map.get(ingredient.toString()).setVisible(false);
            }
        }
    }

    private void checkSelectedBox(final JCheckBox pizza1, final JCheckBox pizza2) {
        if (!pizza1.isSelected() && !pizza2.isSelected()) {
            throw new IllegalStateException("You have to select at least one pizza!");
        }
    }

    private void displayIngredients(final String[] items, final JPanel blockPizza, final JCheckBox pizza) {
        final ImageIcon choppingBoardIcon = new ImageIcon(PATH_TO_THE_ROOT 
                                                    + PATH_TO_RESOURCES 
                                                    + "KitchenComponentsImages" 
                                                    + SEP + "ChoppingBoard.png");
        final JPanel ingredientsPanel = new JPanel(null);
        ingredientsPanel.setPreferredSize(new Dimension((int) (frame.getWidth() * 0.2), (int) (frame.getHeight() * 0.32)));
        ingredientsPanel.setOpaque(false);
        for (final var name: items) {
            final ImageIcon ingredientIcon = new ImageIcon(PATH_TO_THE_ROOT 
                                                            + PATH_TO_RESOURCES 
                                                            + "ingredientsImages" 
                                                            + SEP + name + ".png");
            final JLabel ingredientLabel;
            switch (name) {
                case "Dough" -> { 
                    ingredientLabel = new JLabel(new ImageIcon(ingredientIcon
                                                                .getImage()
                                                                .getScaledInstance((int) (frame.getWidth() * 0.16), 
                                                                                    (int) (frame.getHeight() * 0.25),
                                                                                    0)));
                }
                default -> {
                    ingredientLabel = new JLabel(new ImageIcon(ingredientIcon
                                                                .getImage()
                                                                .getScaledInstance((int) (frame.getWidth() * 0.18), 
                                                                                    (int) (frame.getHeight() * 0.28),
                                                                                    0)));
                }
            }
            if (ingredientLabelsMapPizza1.size() < NUMBER_OF_INGREDIENTS) {
                ingredientLabelsMapPizza1.put(name, ingredientLabel);
            } else {
                ingredientLabelsMapPizza2.put(name, ingredientLabel);
            }
            ingredientLabel.setBounds(0, 0, (int) (frame.getWidth() * 0.19), (int) (frame.getHeight() * 0.3));
            ingredientsPanel.add(ingredientLabel);
            ingredientLabel.setVisible(false);
        }
        final JLabel lblChoppingBoard = new JLabel(new ImageIcon(choppingBoardIcon
                                                            .getImage()
                                                            .getScaledInstance((int) (frame.getWidth() * 0.19), 
                                                                                (int) (frame.getHeight() * 0.3),
                                                                                0)));
        lblChoppingBoard.setBounds(0, 0, (int) (frame.getWidth() * 0.19), (int) (frame.getHeight() * 0.3));
        ingredientsPanel.add(lblChoppingBoard);
        blockPizza.add(ingredientsPanel);
        pizza.setOpaque(false);
        blockPizza.add(pizza);
    }

    private void displayInfoLabels(final ImagePanel imagePanel, final int width, final Controller controller) {
        final JPanel highPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, (int) (width / 15), 10));
        final JLabel lblDay = new JLabel();
        final JLabel lblTime = new JLabel();
        final JLabel lblBalanceDay = new JLabel();
        final JLabel lblBalanceTot = new JLabel();
        lblBalanceDay.setFont(new Font(FONT, Font.BOLD, FONT_SIZE));
        lblBalanceDay.setFont(new Font(FONT, Font.BOLD, FONT_SIZE));
        lblBalanceTot.setFont(new Font(FONT, Font.BOLD, FONT_SIZE));
        lblBalanceTot.setFont(new Font(FONT, Font.BOLD, FONT_SIZE));
        updateBalanceLabels(lblBalanceDay, controller.getBalanceDay());

        lblDay.setText("Day 1");
        lblDay.setFont(new Font(FONT, Font.BOLD, FONT_SIZE));

        lblTime.setFont(new Font(FONT, Font.BOLD, FONT_SIZE));
        lblTime.setSize(CLOCK_LABEL_WIDTH, CLOCK_LABEL_HEIGHT);
        lblTime.setText(controller.getHourAndMin());
        final JButton btnShowOrder = new JButton("Show client's order");
        btnShowOrder.setPreferredSize(new Dimension(150, 21));
        btnShowOrder.setBackground(Color.WHITE);
        highPanel.setBorder(new EmptyBorder(-3, 0, 0, 0));
        highPanel.add(btnShowOrder);
        highPanel.add(lblDay);
        highPanel.add(lblTime);
        highPanel.add(lblBalanceDay);
        highPanel.add(lblBalanceTot);
        imagePanel.add(highPanel, BorderLayout.NORTH);

        final String order = controller.getClientThread().getOrder().getLeft() 
                                    + "\n\n" 
                                    + (controller.getClientThread()
                                                    .getOrder()
                                                    .getRight()
                                                    .isPresent() ? controller.getClientThread()
                                                                                .getOrder()
                                                                                .getRight()
                                                                                .get() + "\n" : "");

        btnShowOrder.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                JOptionPane.showMessageDialog(frame, order, "ORDER", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    private void displayGarbageBinButton(final JButton bin,
                                            final Image garbageBin, 
                                            final int width, 
                                            final int height, 
                                            final JPanel lowPanel) {
        bin.setSize(new Dimension((int) (width * 0.08), (int) (height * 0.15)));
        bin.setIcon(new ImageIcon(garbageBin
            .getScaledInstance((int) (bin.getWidth() - 10), (int) (bin.getHeight() - 5), 0)));
        lowPanel.setBorder(new EmptyBorder((int) (height * 0.01), (int) (width * 0.05),
            (int) (height * 0.1), (int) (width * 0.14)));
        bin.setBackground(new Color(195, 195, 195, 255));
        bin.setBorderPainted(false);
        lowPanel.validate();
    }

    private void displaySupplyComponents(final int width, 
                                            final int height, 
                                            final JComboBox<String> comboBox,
                                            final JButton btnSupply,
                                            final JButton btnAdd,
                                            final JPanel centralNorthPanel) {
        comboBox.setPreferredSize(new Dimension((int) (width * 0.18), (int) (height * 0.035)));
        btnSupply.setBackground(new Color(252, 211, 147, 255));
        btnSupply.setPreferredSize(new Dimension((int) (width * 0.04), (int) (height * 0.035)));
        btnSupply.setBorder(new LineBorder(Color.DARK_GRAY, 1));
        btnAdd.setBackground(new Color(252, 211, 147, 255));
        btnAdd.setPreferredSize(new Dimension((int) (width * 0.04), (int) (height * 0.035)));
        btnAdd.setBorder(new LineBorder(Color.DARK_GRAY, 1));
        centralNorthPanel.setBorder(new EmptyBorder((int) (height * 0.012), (int) (width * 0.04), 
        (int) (height * 0.05), (int) (width * 0.12)));
    }

    private void displayEndingKitchen(final JButton endButton, final int width, final int height, final JPanel rightPanel) {
        endButton.setSize(new Dimension((int) (width * 0.1), (int) (height * 1.2)));
        rightPanel.setBorder(new EmptyBorder((int) (height * 0.475),
                                                (int) (width * 0.08),
                                                (int) (height * 0.18),
                                                (int) (width * 0.155)));
        endButton.validate();
        rightPanel.validate();
    }

    private void displayOven(final JButton ovenButton,
                                final int width,
                                final int height,
                                final JPanel centralSouthPanel) {
        ovenButton.setSize(new Dimension((int) (width * 0.08), (int) (height * 0.05)));
        centralSouthPanel.setBorder(new EmptyBorder((int) (height * 0), (int) (width * 0.77), 0, (int) (width * 0.42)));
        centralSouthPanel.validate();
        centralSouthPanel.setBackground(Color.RED);
    }

    /**
     * It updates total and daily balance after each payment, made or received.
     * @param lblBalanceDay
     * @param balanceDay
     */
    public final void updateBalanceLabels(final JLabel lblBalanceDay,
                                    final double balanceDay) {
        final DecimalFormat df = new DecimalFormat("#.###");
        lblBalanceDay.setText(BALANCE_DAY 
                                + df.format(balanceDay)
                                + CURRENCY);
    }

    /**
     * It starts the GUI setting true the visibility of the frame.
     */
    public void start() {
        frame.setVisible(true);
    }

    //CHECKSTYLE: MagicNumber ON
    /*
     * We have decided to set the MagicNumber to OFF only for this GUI,
     * because all the MagicNumbers that are present are fundamental for the graphics
     * and in our opinion, being too many and all different from each other,
     * so it was inappropriate to include so many constants.
     */

}
