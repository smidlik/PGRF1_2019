package view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

public class Raster extends JPanel {

    public static final int WIDTH = 800, HEIGHT = 600;
    private static final int FPS = 1000 / 60;
    public static int COLOR;
    private final BufferedImage img; // objekt pro zápis pixelů
    private final Graphics g; // objekt nad kterým jsou k dispozici grafické funkce

    private JLabel fillLbl;


    public Raster() {

        setLayout(new FlowLayout(FlowLayout.TRAILING));
        fillLbl = new JLabel();
        fillLbl.setText("Vyplňování: OFF");
        fillLbl.setForeground(Color.white);
        add(fillLbl);

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        // inicializace image, nastavení rozměrů (nastavení typu - pro nás nedůležité)
        img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        COLOR = Color.YELLOW.getRGB();
        g = img.getGraphics();
        setLoop();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, null);
        // pro zájemce - co dělá observer - https://stackoverflow.com/a/1684476
    }

    private void setLoop() {
        // časovač, který 30 krát za vteřinu obnoví obsah plátna aktuálním img
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                repaint();
            }
        }, 0, FPS);
    }

    public void clear() {
        g.setColor(Color.BLACK);
        g.clearRect(0, 0, WIDTH, HEIGHT);
    }

    public void drawPixel(int x, int y,int color) {
        // nastavit pixel do img
        if (x < 0 || x >= WIDTH) return;
        if (y < 0 || y >= HEIGHT) return;
        img.setRGB(x, y, color);
    }

    public int getPixel(int x, int y) {
        return img.getRGB(x, y);
    }

    public void setFillLbl(boolean fillState) {
        if(fillState)
            fillLbl.setText("Vyplňování: ON");
    else
            fillLbl.setText("Vyplňování: OFF");
    //add(fillLbl);
    }

}
