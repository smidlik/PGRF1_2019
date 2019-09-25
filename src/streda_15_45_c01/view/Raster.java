package streda_15_45_c01.view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

public class Raster extends JPanel {

    private final BufferedImage img; // objekt pro zápis pixelů
    private final Graphics g; // objekt nad kterým jsou k dispozici grafické funkce
    private static final int FPS = 1000 / 30;

    public Raster() {
        // inicializace image, nastavení rozměrů (nastavení typu - pro nás nedůležité)
        img = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
        g = img.getGraphics();
        setTimer();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, null);
        // pro zájemce - co dělá observer - https://stackoverflow.com/a/1684476
    }

    private void setTimer() {
        // časovač, který 30 krát za vteřinu obnoví obsah plátna aktuálním img
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                // říct plátnu, aby zobrazilo aktuální img
                repaint();
            }
        }, 0, FPS);
    }

}
