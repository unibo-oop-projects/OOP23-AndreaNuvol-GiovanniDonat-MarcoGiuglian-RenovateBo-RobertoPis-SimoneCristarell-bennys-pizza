package it.unibo.view;

import javax.swing.JPanel;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Dimension;

/**
 * JPanel modified to contains a background image.
 */
public class ImagePanel extends JPanel {
    private Image backgroundImage;

    /**
     * Constructor of ImagePanel.
     * @param backgroundImage the image to put in background
     */
    public ImagePanel(final Image backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        Dimension size = getSize();
        g.drawImage(backgroundImage, 0, 0, size.width, size.height, this);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(backgroundImage.getWidth(this), backgroundImage.getHeight(this));
    }
}
