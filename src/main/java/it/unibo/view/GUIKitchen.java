package it.unibo.view;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.nio.file.FileSystems;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.*;
import it.unibo.controller.api.Controller;

public class GUIKitchen {

    private static final String SEP = File.separator;
    private static final String PATH_TO_THE_ROOT = FileSystems.getDefault().getPath(new String()).toAbsolutePath().toString();
    private static final String PATH_TO_RESOURCES = SEP + "src" + SEP + "main" + SEP + "resources" + SEP;

    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static final int screenWidth = (int)screenSize.getWidth();
    private static final int screenHeight = (int)screenSize.getHeight();

    private static final String FONT = "Arial";
    private static final int FONT_SIZE = 25;
    private static final String BALANCE_TOT = "Total balance : ";
    private static final String BALANCE_DAY = "Daily balance : ";
    private static final String CURRENCY = "$";
    static final int CLOCK_LABEL_WIDTH = (int) (screenWidth * 0.1);
    static final int CLOCK_LABEL_HEIGHT = (int) (screenHeight * 0.05);

    private final JFrame frame = new JFrame("KITCHEN");

    private final Map<String, JLabel> ingredientLabelsMapPizza1 = new HashMap<>();
    private final Map<String, JLabel> ingredientLabelsMapPizza2 = new HashMap<>();

    public GUIKitchen(final Controller controller, final JFrame backgroundHall, final JLabel dayLabel) {
        frame.setSize(screenWidth, screenHeight);
        final Image background = Toolkit.getDefaultToolkit().getImage(PATH_TO_THE_ROOT + PATH_TO_RESOURCES + "Preparation_Zone.png");
        ImagePanel imagePanel = new ImagePanel(background);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(imagePanel, BorderLayout.CENTER);
        imagePanel.setLayout(new BorderLayout());

        displayInfoLabels(imagePanel, frame.getWidth(), controller);

        final JPanel lowPanel = new JPanel(new BorderLayout());
        lowPanel.setOpaque(false);

        final JPanel lowEastPanel = new JPanel();
        lowEastPanel.setOpaque(false);
        final Image garbageBin = Toolkit.getDefaultToolkit().getImage(PATH_TO_THE_ROOT + PATH_TO_RESOURCES + "KitchenComponentsImages" + SEP + "GarbageBin.png");
        final JButton btnGarbageBin = new JButton();
        displayGarbageBinButton(btnGarbageBin, garbageBin, frame.getWidth(), frame.getHeight(), lowEastPanel);
        lowEastPanel.add(btnGarbageBin);
        lowPanel.add(lowEastPanel, BorderLayout.EAST);

        final JPanel centralPanel = new JPanel(new BorderLayout());
        centralPanel.setOpaque(false);

        final JPanel centralNorthPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, (int)(frame.getWidth()*0.002), (int)(frame.getHeight()*0.01)));
        centralNorthPanel.setOpaque(false);
        final JLabel lblSelect = new JLabel("Select the ingredient to supply:");
        centralNorthPanel.add(lblSelect);
        final String[] items = { "Anchovies", "Artichokes", "CherryTomatoes", "Fontina", "FrenchFries", "Gorgonzola",
            "Ham", "Wurstel", "Mushrooms", "Olives", "Onions", "Parmesan", "Salami", "Sausages", "Tuna", "Mozzarella", "TomatoeSauce", "Dough" };
        final JComboBox<String> comboBox = new JComboBox<>(items);

        final Map<String, ImageIcon> itemImageMap = new HashMap<>();
        for (int i = 0; i < items.length; i++) {
            itemImageMap.put(items[i], new ImageIcon(PATH_TO_THE_ROOT + PATH_TO_RESOURCES + "IngredientsButtonsIcons" + SEP + items[i] + ".png"));
        }
        comboBox.setRenderer(new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                    JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    // Get the icon for the current item
                    ImageIcon icon = new ImageIcon(itemImageMap.get((String) value).getImage().getScaledInstance(80, 80, 0));
                    // Set the icon and text for the label
                    label.setIcon(icon);
                    final int quantity = controller.getIngredientQuantity((String)value);
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
        imagePanel.add(rightPanel ,BorderLayout.EAST);

        final JPanel centralSouthPanel = new JPanel();
        centralSouthPanel.setOpaque(false);
        final JButton btnOven = new JButton("Bake");
        btnOven.setBackground(new Color(181, 151, 106, 255));
        centralSouthPanel.add(btnOven);
        displayOven(btnOven, frame.getWidth(), frame.getHeight(), centralSouthPanel, controller);
        centralPanel.add(centralSouthPanel, BorderLayout.SOUTH);

        final JPanel centralCentralPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        centralCentralPanel.setOpaque(false);
        centralPanel.add(centralCentralPanel, BorderLayout.CENTER);

        final JPanel blockPizza1 = new JPanel();
        blockPizza1.setOpaque(false);
        final JPanel blockPizza2 = new JPanel();
        blockPizza1.setBorder(new EmptyBorder(0, 0, (int)(frame.getHeight()*0.1), 0));
        blockPizza2.setBorder(new EmptyBorder(0, 0, (int)(frame.getHeight()*0.1), 0));
        blockPizza2.setOpaque(false);
        blockPizza1.setLayout(new BoxLayout(blockPizza1, BoxLayout.Y_AXIS));
        blockPizza2.setLayout(new BoxLayout(blockPizza2, BoxLayout.Y_AXIS));
        final JCheckBox pizza1 = new JCheckBox();
        final JCheckBox pizza2 = new JCheckBox();

        displayIngredients(items, blockPizza1, pizza1);
        displayIngredients(items, blockPizza2, pizza2);
        
        centralCentralPanel.add(blockPizza1);
        centralCentralPanel.add(blockPizza2);
        centralCentralPanel.setBorder(new EmptyBorder(0, (int)(frame.getWidth()*0.07), 200, 0));
        imagePanel.add(centralPanel, BorderLayout.CENTER);
        imagePanel.add(lowPanel, BorderLayout.SOUTH);

        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(final ComponentEvent e) {
                int width = frame.getContentPane().getWidth();
                int height = frame.getContentPane().getHeight();
                displayInfoLabels(imagePanel, width, controller);
                displayGarbageBinButton(btnGarbageBin, garbageBin, width, height, lowEastPanel);
                displaySupplyComponents(width, height, comboBox, btnSupply, btnAdd, centralNorthPanel);
                displayEndingKitchen(btnEndingKitchen, width, height, rightPanel);
                displayOven(btnOven, width, height, centralSouthPanel, controller);
            }
        });

        pizza2.setEnabled(controller.getPreparationZone().getPizza2().equals(Optional.empty()) ? false : true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        btnGarbageBin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkSelectedBox(pizza1, pizza2);
                try {
                    if (pizza1.isSelected()) {
                        disenableIngredientsLabels(controller, true, ingredientLabelsMapPizza1);
                        controller.throwPizzaInGarbageBin(true);
                    }
                    if (pizza2.isSelected()) {
                        disenableIngredientsLabels(controller, false, ingredientLabelsMapPizza2);
                        controller.throwPizzaInGarbageBin(false);
                    }
                } catch (Exception bottonGarbageBinException) {
                    JOptionPane.showMessageDialog(frame, bottonGarbageBinException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

                }
            }
            
        });

        btnAdd.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                checkSelectedBox(pizza1, pizza2);
                try {
                    if (pizza1.isSelected()) {
                        controller.addIngredient(comboBox.getSelectedItem().toString(), true);
                        ingredientLabelsMapPizza1.get(comboBox.getSelectedItem().toString()).setVisible(true);
                    }
                    if (pizza2.isSelected()) {
                        controller.addIngredient(comboBox.getSelectedItem().toString(), false);
                        ingredientLabelsMapPizza2.get(comboBox.getSelectedItem().toString()).setVisible(true);
                    }
                } catch (Exception bottonAddException) {
                    JOptionPane.showMessageDialog(frame, bottonAddException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            
        });

        btnEndingKitchen.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.getClientThread().wakeUp();
                    dayLabel.repaint();
                    backgroundHall.setVisible(true);
                    frame.dispose();
                } catch (Exception bottonEndingKitchenException) {
                    JOptionPane.showMessageDialog(frame, bottonEndingKitchenException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }  
        });

        btnSupply.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.supply(comboBox.getSelectedItem().toString());
                } catch (Exception bottonSupplyException) {
                    JOptionPane.showMessageDialog(frame, bottonSupplyException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } 
        });
        
        btnOven.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                checkSelectedBox(pizza1, pizza2);   
                try {
                    if (pizza1.isSelected()) {
                        disenableIngredientsLabels(controller, true, ingredientLabelsMapPizza1);
                        pizza1.setEnabled(false);
                        pizza1.setSelected(false);
                        controller.bakingPizza();
                        Thread.sleep(1500);
                        JOptionPane.showMessageDialog(frame, "Pizza is baked!", "INFO", JOptionPane.INFORMATION_MESSAGE);
                    }
                    if (pizza2.isSelected()) {
                        disenableIngredientsLabels(controller, false, ingredientLabelsMapPizza2);
                        pizza2.setEnabled(false);
                        pizza2.setSelected(false);
                        controller.bakingPizza();
                        Thread.sleep(1500);
                        JOptionPane.showMessageDialog(frame, "Pizza is baked!", "INFO", JOptionPane.INFORMATION_MESSAGE);
                    }
                    
                    if (!pizza1.isEnabled() && !pizza2.isEnabled()) {
                        btnEndingKitchen.setEnabled(true);
                    }
                } catch (Exception bottonOvenException) {
                    JOptionPane.showMessageDialog(frame, bottonOvenException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void disenableIngredientsLabels(final Controller controller, final boolean isPizza1, final Map<String, JLabel> map) {
        for (var ingredient : isPizza1 
                ? controller.getPreparationZone().getPizza1().getAddedIngredients() 
                : controller.getPreparationZone().getPizza2().get().getAddedIngredients()) {
            if(map.get(ingredient.toString()).isVisible()) {
                map.get(ingredient.toString()).setVisible(false);
            }
        }
    }

    private void checkSelectedBox(final JCheckBox pizza1, final JCheckBox pizza2) {
        if (!pizza1.isSelected() && !pizza2.isSelected()) {
            JOptionPane.showMessageDialog(frame, "You have to select at least one pizza!", "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayIngredients(final String[] items, final JPanel blockPizza, final JCheckBox pizza) {
        ImageIcon choppingBoardIcon = new ImageIcon(PATH_TO_THE_ROOT + PATH_TO_RESOURCES + "KitchenComponentsImages" + SEP + 
        "ChoppingBoard.png");
        final JPanel ingredientsPanel = new JPanel(null);
        ingredientsPanel.setPreferredSize(new Dimension((int)(frame.getWidth()*0.2), (int)(frame.getHeight()*0.32)));
        ingredientsPanel.setOpaque(false);
        for (final var name: items) {
            final ImageIcon ingredientIcon = new ImageIcon(PATH_TO_THE_ROOT + PATH_TO_RESOURCES + "ingredientsImages" + SEP + name + ".png");
            final JLabel ingredientLabel;
            switch (name) {
                case "Dough" -> { 
                    ingredientLabel = new JLabel(new ImageIcon(ingredientIcon.getImage().getScaledInstance((int)(frame.getWidth()*0.16), (int)(frame.getHeight()*0.25), 0)));
                }
                case "TomatoSauce" -> {
                    ingredientLabel = new JLabel(new ImageIcon(ingredientIcon.getImage().getScaledInstance((int)(frame.getWidth()*0.18), (int)(frame.getHeight()*0.28), 0)));
                }
                default -> {
                    ingredientLabel = new JLabel(new ImageIcon(ingredientIcon.getImage().getScaledInstance((int)(frame.getWidth()*0.18), (int)(frame.getHeight()*0.28), 0)));
                }
            }
            if (ingredientLabelsMapPizza1.size() < 18) {
                ingredientLabelsMapPizza1.put(name, ingredientLabel);
            } else {
                ingredientLabelsMapPizza2.put(name, ingredientLabel);
            }
            ingredientLabel.setBounds(0, 0, (int)(frame.getWidth()*0.19), (int)(frame.getHeight()*0.3));
            ingredientsPanel.add(ingredientLabel);
            ingredientLabel.setVisible(false);
        }
        JLabel lblChoppingBoard = new JLabel(new ImageIcon(choppingBoardIcon.getImage().getScaledInstance((int)(frame.getWidth()*0.19), (int)(frame.getHeight()*0.3), 0)));
        lblChoppingBoard.setBounds(0, 0, (int)(frame.getWidth()*0.19), (int)(frame.getHeight()*0.3));
        ingredientsPanel.add(lblChoppingBoard);
        blockPizza.add(ingredientsPanel);
        pizza.setOpaque(false);
        blockPizza.add(pizza);
    }

    private void displayInfoLabels(final ImagePanel imagePanel, final int width, final Controller controller) {
        final JPanel highPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, (int)(width / 15), 10));
        final JLabel lblDay = new JLabel();
        final JLabel lblTime = new JLabel();
        final JLabel lblBalanceDay = new JLabel();
        final JLabel lblBalanceTot = new JLabel();
        lblBalanceDay.setFont(new Font(FONT, Font.BOLD, FONT_SIZE));
        lblBalanceDay.setFont(new Font(FONT, Font.BOLD, FONT_SIZE));
        lblBalanceTot.setFont(new Font(FONT, Font.BOLD, FONT_SIZE));
        lblBalanceTot.setFont(new Font(FONT, Font.BOLD, FONT_SIZE));
        updateBalanceLabels(lblBalanceTot, lblBalanceDay, controller.getBalanceDay(), controller.getBalanceTot());
        
        lblDay.setText("Day " + String.valueOf(controller.getWorkingDay()));
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

        final String order = controller.getClientThread().getOrder().getLeft() + "\n\n" +
            (controller.getClientThread().getOrder().getRight().isPresent() ? controller.getClientThread().getOrder().getRight().get() + "\n" : "");

        btnShowOrder.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, order, "ORDER", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    private void displayGarbageBinButton(final JButton bin, final Image garbageBin, final int width, final int height, final JPanel lowPanel) {
        bin.setSize(new Dimension((int)(width * 0.08), (int)(height * 0.15)));
        bin.setIcon(new ImageIcon(garbageBin
            .getScaledInstance((int)(bin.getWidth()-10), (int)(bin.getHeight()-5), 0)));
        lowPanel.setBorder(new EmptyBorder((int)(height*0.01), (int)(width*0.05),
            (int)(height*0.1), (int)(width*0.14)));
        bin.setBackground(new Color(195, 195, 195, 255));
        bin.setBorderPainted(false);
        lowPanel.validate();
    }

    private void displaySupplyComponents(final int width, final int height, final JComboBox<String> comboBox, final JButton btnSupply, final JButton btnAdd, final JPanel centralNorthPanel) {
        comboBox.setPreferredSize(new Dimension((int)(width * 0.18), (int)(height * 0.035)));
        btnSupply.setBackground(new Color(252, 211, 147, 255));
        btnSupply.setPreferredSize(new Dimension((int)(width * 0.04), (int)(height * 0.035)));
        btnSupply.setBorder(new LineBorder(Color.DARK_GRAY, 1));
        btnAdd.setBackground(new Color(252, 211, 147, 255));
        btnAdd.setPreferredSize(new Dimension((int)(width * 0.04), (int)(height * 0.035)));
        btnAdd.setBorder(new LineBorder(Color.DARK_GRAY, 1));
        centralNorthPanel.setBorder(new EmptyBorder((int)(height*0.012), (int)(width*0.04), 
        (int)(height*0.05), (int)(width*0.12)));
    }

    private void displayEndingKitchen(final JButton endButton, final int width, final int height, final JPanel rightPanel) {
        endButton.setSize(new Dimension((int)(width*0.1), (int)(height*1.2)));
        rightPanel.setBorder(new EmptyBorder((int)(height*0.475), (int)(width*0.08), (int)(height*0.18), (int)(width*0.155)));
        endButton.validate();
        rightPanel.validate();
    }

    private void displayOven(final JButton ovenButton, final int width, final int height, final JPanel centralSouthPanel, final Controller controller) {
        ovenButton.setSize(new Dimension((int)(width*0.08), (int)(height*0.05)));
        centralSouthPanel.setBorder(new EmptyBorder((int)(height*0), (int)(width*0.77), 0, (int)(width*0.42)));
        centralSouthPanel.validate();    
        centralSouthPanel.setBackground(Color.RED);
    }

        public void updateBalanceLabels(final JLabel lblbalanceTot, final JLabel lblbalanceDay, double balanceDay, double balanceTot) {
        DecimalFormat df = new DecimalFormat("#.###");
        lblbalanceTot.setText(BALANCE_TOT
                                + df.format(balanceTot)
                                + CURRENCY);
        lblbalanceDay.setText(BALANCE_DAY 
                                + df.format(balanceDay)
                                + CURRENCY);
    }

    public void start() {
        frame.setVisible(true);
    }
}
