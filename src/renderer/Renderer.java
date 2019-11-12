package renderer;

import misc.Lines;
import model.Point;
import view.Raster;

import java.awt.*;
import java.util.List;


public class Renderer {

    private Raster raster;

    private Lines lines;

    private int color;

    public Renderer(Raster raster, Lines lines, Color color) {
        this.raster = raster;
        this.lines = lines;
        this.color = color.getRGB();
    }

    private void drawPixel(int x1, int y1,int color) {
        raster.drawPixel(x1, y1,color);
    }

    private void drawLine(int x1, int y1, int x2, int y2) {
        switch (lines) {
            case trivial:
                lineTrivial(x1, y1, x2, y2);
                break;

            case dda:
                lineDDA(x1, y1, x2, y2);
                break;

            case bresenham:
                lineBersenham(x1, y1, x2, y2);
                break;

        }
    }

    private void lineTrivial(int x1, int y1, int x2, int y2) {
        int dx = x2 - x1;
        int dy = y2 - y1;
        drawPixel(x1, y1,color);
        if (Math.abs(dx) > Math.abs(dy)) {
            float k = (float) dy / (float) dx;
            float q = y1 - k * x1;
            if (dx < 0) dx = -1;
            else dx = 1;
            while (x1 != x2) {
                x1 += dx;

                drawPixel(x1, Math.round(k * x1 + q),color);
            }
        } else if (dy != 0) {
            float m = (float) dx / (float) dy;
            float b = x1 - m * y1;
            if (dy < 0) dy = -1;
            else dy = 1;
            while (y1 != y2) {
                y1 += dy;
                drawPixel(Math.round(m * y1 + b), y1,color);
            }
        }
    }

    private void lineDDA(int x1, int y1, int x2, int y2) {
        int dx, dy;
        dx = x2 - x1;
        dy = y2 - y1;
        float k = dy / (float) dx;
        float g, h;
        if (Math.abs(k) < 1) {
            // řídící osa X
            g = 1;
            h = k;
            if (x1 > x2) {
                x1 = x2;
                y1 = y2;
            }
        } else {
            // řídící osa Y
            g = 1 / k;
            h = 1;
            if (y1 > y2) {
                x1 = x2;
                y1 = y2;
            }
        }
        float x = x1;
        float y = y1;
        for (int i = 0; i <= Math.max(Math.abs(dx), Math.abs(dy)); i++) {
            drawPixel(Math.round(x), Math.round(y),color);
            x += g;
            y += h;
        }
    }

    private void lineBersenham( int x1, int y1, int x2, int y2) {
        int d = 0;

        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);

        int dx2 = 2 * dx;
        int dy2 = 2 * dy;

        int ix = x1 < x2 ? 1 : -1;
        int iy = y1 < y2 ? 1 : -1;

        int x = x1;
        int y = y1;

        if (dx >= dy) {
            while (true) {
                drawPixel(x, y,color);
                if (x == x2)
                    break;
                x += ix;
                d += dy2;
                if (d > dx) {
                    y += iy;
                    d -= dx2;
                }
            }
        } else {
            while (true) {
                drawPixel(x, y,color);
                if (y == y2)
                    break;
                y += iy;
                d += dx2;
                if (d > dy) {
                    x += ix;
                    d -= dy2;
                }
            }
        }
    }




    public void drawPolygon(List<Point> polygonPoints) {
        for (int i = 0; i < polygonPoints.size() - 1; i++) {
            drawLine(polygonPoints.get(i).x,
                    polygonPoints.get(i).y,
                    polygonPoints.get(i + 1).x,
                    polygonPoints.get(i + 1).y
            );
        }
        // spoj poslední a první
        drawLine(polygonPoints.get(0).x,
                polygonPoints.get(0).y,
                polygonPoints.get(polygonPoints.size() - 1).x,
                polygonPoints.get(polygonPoints.size() - 1).y
        );
    }

    public void drawNObject(int x1, int y1, int x2, int y2, int count) {
        double x0 = x2 - x1;
        double y0 = y2 - y1;
        double circleRadius = 2 * Math.PI;
        double step = circleRadius / (double) count;
        for (double i = 0; i < circleRadius; i += step) {
            // dle rotační matice
            double x = x0 * Math.cos(step) + y0 * Math.sin(step);
            double y = y0 * Math.cos(step) - x0 * Math.sin(step);
            drawLine((int) x0 + x1, (int) y0 + y1,
                    (int) x + x1, (int) y + y1);
            // potřeba změnit x0, y0
            x0 = x;
            y0 = y;
        }
    }

    public void drawLines(List<Point> linePoints) {
        for (int i = 0; i < linePoints.size() - 1; i += 2) {
            drawLine(linePoints.get(i).x,
                    linePoints.get(i).y,
                    linePoints.get(i + 1).x,
                    linePoints.get(i + 1).y

            );
        }
    }
    public void setLines(Lines lines) {
        this.lines = lines;
    }

    public void setColor(Color color) {
        this.color = color.getRGB();
        raster.repaint();
    }
}
