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
import java.util.ArrayList;
import java.util.List;

public class LineControler extends Controler {

    private List<Point> linePoints = new ArrayList<>();

    public LineControler(Raster raster, Lines lines, Color borderColor, Color fillColor) {
        renderer = new Renderer(raster, lines, borderColor);

        fillMethod = new SeedFiller(raster, fillColor.getRGB());

        initObjects(raster);

        initListeners(raster);
    }

    private void update(Raster raster) {
        raster.clear();
        renderer.drawLines(linePoints);
    }


    @Override
    public void initObjects(Raster raster) {
    }

    @Override
    public void initListeners(Raster raster) {
        raster.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    linePoints.add(new model.Point(e.getX(), e.getY()));
                    linePoints.add(new model.Point(e.getX(), e.getY()));
                }
                update(raster);
            }

        });
        raster.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    linePoints.set(linePoints.size() - 1,
                            linePoints.get(linePoints.size() - 1).withX(e.getX())
                    );
                    linePoints.set(linePoints.size() - 1,
                            linePoints.get(linePoints.size() - 1).withY(e.getY())
                    );
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

                if (e.getKeyCode() == KeyEvent.VK_F) {
                    fillState = !fillState;
                    raster.setFillLbl(fillState);

                }
            }
        });
    }
}
