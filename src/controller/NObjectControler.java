package controller;

import fill.SeedFiller;
import misc.Lines;
import model.Point;
import renderer.Renderer;
import view.Raster;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NObjectControler extends Controler {

    private Point p1;
    private Point p2;
    private int count;

    public NObjectControler(Raster raster, Lines lines, Color borderColor, Color fillColor) {
        count = 3;
        renderer = new Renderer(raster,lines, borderColor);
        fillMethod = new SeedFiller(raster,fillColor.getRGB());

        initObjects(raster);
        initListeners(raster);
    }

    private void update(Raster raster) {
        raster.clear();
        if (p1 != null || p2 != null)
            renderer.drawNObject(p1.x, p1.y, p2.x, p2.y, count);
    }
    @Override
    public void initObjects(Raster raster) {

    }

    @Override
    public void initListeners(Raster raster) {
        raster.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent e) {
                p2 = new Point(e.getX(), e.getY());
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {

                    p1 = new Point(e.getX(), e.getY());
                    p2 = new Point(e.getX(), e.getY());
                    update(raster);
                }
            }
        });
        raster.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {

                    p2 = new Point(e.getX(), e.getY());
                }
                update(raster);
            }
        });
        raster.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                // při zmáčknutí klávesy C vymazat plátno
                if (e.getKeyCode() == KeyEvent.VK_C) {
                    raster.clear();
                }
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    if (count < 36)
                        count++;
                    update(raster);
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    if (count > 3)
                        count--;
                    update(raster);

                }
                if (e.getKeyCode() == KeyEvent.VK_F) {
                    fillState = !fillState;
                    raster.setFillLbl(fillState);
                }

            }
        });
    }
}
