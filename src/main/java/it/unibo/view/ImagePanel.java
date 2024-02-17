package it.unibo.view.impl;

import java.awt.*;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {
    private Image backgroundImage;

    public ImagePanel(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Disegna l'immagine come sfondo
        Dimension size = getSize();
        g.drawImage(backgroundImage, 0, 0, size.width, size.height, this);
    }

    @Override
    public Dimension getPreferredSize() {
        // Restituisce le dimensioni dell'immagine come dimensioni preferite della JPanel
        return new Dimension(backgroundImage.getWidth(this), backgroundImage.getHeight(this));
    }
}