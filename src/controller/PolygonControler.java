package controller;

import fill.ScanLine;
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
import java.util.ArrayList;
import java.util.List;

public class PolygonControler extends Controler {

    private List<Point> polygonPoints = new ArrayList<>();
    private ScanLine scanLine;

    public PolygonControler(Raster raster, Lines lines, Color borderColor, Color fillColor) {
        renderer = new Renderer(raster, lines, borderColor);
        fillMethod = new ScanLine(raster, fillColor.getRGB(), borderColor.getRGB());
        scanLine = new ScanLine(raster, fillColor.getRGB(), borderColor.getRGB());

        initObjects(raster);
        initListeners(raster);
    }

    private void update(Raster raster) {
        raster.clear();
        renderer.drawPolygon(polygonPoints);
    }

    @Override
    public void initObjects(Raster raster) {
    }

    @Override
    public void initListeners(Raster raster) {
        raster.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (fillState) {
                    scanLine.init(polygonPoints);
                    scanLine.fill(e.getX(), e.getY());
                }
            }
        });

        raster.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!fillState) {

                    if (SwingUtilities.isLeftMouseButton(e)) {
                        polygonPoints.add(new Point(e.getX(), e.getY()));
                        if (polygonPoints.size() == 1) { // při prvním kliknutí přidat rovnou i druhý bod
                            polygonPoints.add(new Point(e.getX(), e.getY()));
                        }
                    } else
                        update(raster);
                }
            }

        });
        raster.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    polygonPoints.get(polygonPoints.size() - 1).x = e.getX();
                    polygonPoints.get(polygonPoints.size() - 1).y = e.getY();
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
                    polygonPoints = new ArrayList<>();
                    scanLine.init(polygonPoints);
                }
                if (e.getKeyCode() == KeyEvent.VK_F) {
                    fillState = !fillState;
                    raster.setFillLbl(fillState);

                }
            }
        });
    }
}
