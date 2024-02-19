package it.unibo.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.FileSystems;
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
import it.unibo.controller.impl.ControllerImpl;

public class GUIKitchen {

    private static final String SEP = File.separator;
    private static final String PATH_TO_THE_ROOT = FileSystems.getDefault().getPath(new String()).toAbsolutePath().toString();
    private static final String PATH_TO_RESOURCES = SEP + "src" + SEP + "main" + SEP + "resources" + SEP;

    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final int screenWidth = (int)screenSize.getWidth();
    private final int screenHeight = (int)screenSize.getHeight();

    private final JFrame frame = new JFrame("KITCHEN");

    private final Map<String, JLabel> ingredientLabelsMapPizza1 = new HashMap<>();
    private final Map<String, JLabel> ingredientLabelsMapPizza2 = new HashMap<>();

    public GUIKitchen(final Controller controller) {
        frame.setSize(screenWidth, screenHeight);
        final Image background = Toolkit.getDefaultToolkit().getImage(PATH_TO_THE_ROOT + PATH_TO_RESOURCES + "Preparation_Zone.png");
        ImagePanel imagePanel = new ImagePanel(background);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(imagePanel, BorderLayout.CENTER);
        imagePanel.setLayout(new BorderLayout());

        displayInfoLabels(imagePanel, frame.getWidth());

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
                    label.setText((String) value + "  Q: " + controller.getIngredientQuantity((String)value));
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
        rightPanel.add(btnEndingKitchen);
        displayEndingKitchen(btnEndingKitchen, frame.getWidth(), frame.getHeight(), rightPanel);
        imagePanel.add(rightPanel ,BorderLayout.EAST);

        final JPanel centralSouthPanel = new JPanel();
        centralSouthPanel.setOpaque(false);
        final JButton btnOven = new JButton("Bake");
        btnOven.setBackground(new Color(181, 151, 106, 255));
        centralSouthPanel.add(btnOven);
        displayOven(btnOven, frame.getWidth(), frame.getHeight(), centralSouthPanel);
        centralPanel.add(centralSouthPanel, BorderLayout.SOUTH);

        final JPanel lowWestPanel = new JPanel();
        lowWestPanel.setOpaque(false);
        final JButton btnClean = new JButton("Clean");
        btnClean.setBackground(new Color(195, 195, 195, 255));
        lowWestPanel.add(btnClean);
        displayClean(btnClean, frame.getWidth(), frame.getHeight(), lowWestPanel);
        lowPanel.add(lowWestPanel, BorderLayout.WEST);

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
                displayInfoLabels(imagePanel, width);
                displayGarbageBinButton(btnGarbageBin, garbageBin, width, height, lowEastPanel);
                displaySupplyComponents(width, height, comboBox, btnSupply, btnAdd, centralNorthPanel);
                displayEndingKitchen(btnEndingKitchen, width, height, rightPanel);
                displayOven(btnOven, width, height, centralSouthPanel);
                displayClean(btnClean, width, height, lowWestPanel);
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        btnGarbageBin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                checkSelectedBox(pizza1, pizza2);
                if (pizza1.isSelected()) {
                    for (var ingredient : controller.getPreparationZone().getPizza1().getAddedIngredients()) {
                        if(ingredientLabelsMapPizza1.get(ingredient.toString()).isVisible()) {
                            ingredientLabelsMapPizza1.get(ingredient.toString()).setVisible(false);
                        }
                    }
                    controller.throwPizzaInGarbageBin(true);
                }
                if (pizza2.isSelected()) {
                    for (var ingredient : controller.getPreparationZone().getPizza2().getAddedIngredients()) {
                        if(ingredientLabelsMapPizza2.get(ingredient.toString()).isVisible()) {
                            ingredientLabelsMapPizza2.get(ingredient.toString()).setVisible(false);
                        }
                    }
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
            } catch (Exception bottonAddException) {
                JOptionPane.showMessageDialog(frame, bottonAddException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
            
        });

        btnEndingKitchen.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
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
                try {
                checkSelectedBox(pizza1, pizza2);
                if (pizza1.isSelected()) {
                    controller.bakingPizza();
                    JOptionPane.showMessageDialog(frame, "Pizza number 1 is baked!", "Baked!", JOptionPane.INFORMATION_MESSAGE);
                }
                if (pizza2.isSelected()) {
                    controller.bakingPizza();
                    JOptionPane.showMessageDialog(frame, "Pizza number 2 is baked!", "Baked!", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (Exception bottonOvenException) {
                JOptionPane.showMessageDialog(frame, bottonOvenException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
            
        });
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

    private void displayInfoLabels(final ImagePanel imagePanel, final int width) {
        final JPanel highPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, (int)(width / 5), 10));
        final JLabel lblDay = new JLabel("Day: 16/02/2024");
        highPanel.add(lblDay);
        highPanel.add(new JLabel("18:15"));
        highPanel.add(new JLabel("Balance: 50€"));
        imagePanel.add(highPanel, BorderLayout.NORTH);
    }

    private void displayGarbageBinButton(final JButton bin, final Image garbageBin, final int width, final int height, final JPanel lowPanel) {
        bin.setSize(new Dimension((int)(width * 0.08), (int)(height * 0.15)));
        bin.setIcon(new ImageIcon(garbageBin
            .getScaledInstance((int)(bin.getWidth()-10), (int)(bin.getHeight()-5), 0)));
        lowPanel.setBorder(new EmptyBorder((int)(height*0.01), (int)(width*0.05),
            (int)(height*0.04), (int)(width*0.14)));
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

    private void displayOven(final JButton ovenButton, final int width, final int height, final JPanel centralSouthPanel) {
        ovenButton.setSize(new Dimension((int)(width*0.08), (int)(height*0.05)));
        centralSouthPanel.setBorder(new EmptyBorder((int)(height*0), (int)(width*0.77), (int)(height*0.02), (int)(width*0.42)));
        centralSouthPanel.validate();
    }

    private void displayClean(final JButton cleanerButton, final int width, final int height, final JPanel lowWestPanel) {
        cleanerButton.setSize(new Dimension((int)(width*0.1), (int)(height*0.05)));
        lowWestPanel.setBorder(new EmptyBorder((int)(height*0), (int)(width*0.07), 
            (int)(height*0.20), (int)(width*0.1)));
        lowWestPanel.validate();
    }

    public void start() {
        frame.setVisible(true);
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        new GUIKitchen(new ControllerImpl()).start();
    }

}