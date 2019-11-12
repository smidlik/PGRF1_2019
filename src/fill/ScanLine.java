package fill;

import model.Edge;
import model.Point;
import view.Raster;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScanLine implements Filler {

    private Raster raster;
    private int fillColor, edgeColor;
    private List<Point> points;

    public ScanLine(Raster raster, int fillColor, int edgeColor) {
        this.raster = raster;
        this.fillColor = fillColor;
        this.edgeColor = edgeColor;
    }

    @Override
    public void setRaster(Raster raster) {
        this.raster = raster;
    }

    @Override
    public void setColor(Color color) {
        fillColor = color.getRGB();
    }

    @Override
    public void fill(int x, int y) {
        scanLine();
    }

    public void init(List<Point> points) {
        this.points = points;
    }

    private void scanLine() {

        List<Edge> edges = new ArrayList<>();
        int minY = points.get(0).y;
        int maxY = minY;

        for (int i = 0; i < points.size()-1; i++) {

            Edge e = new Edge(points.get(i), points.get(i + 1));
            if (!e.isHorizontal()) {
                e.orientate();
                edges.add(e);
            }
        }

        Edge e = new Edge(points.get(points.size() - 1), points.get(0));
        if (!e.isHorizontal()) {
            e.orientate();
            edges.add(e);
        }

        for (Point point : points) {
            if (minY > point.y) {
                minY = point.y;
            }
            if (maxY < point.y) {
                maxY = point.y;
            }
        }

            // projet všechny body a vytvořit z nich hrany (jako polygon)
            // 0. a 1. bod budou první hrana; 1. a 2. bod budou druhá hrana
            // ...; poslední a 0. bod budou poslední hrana
            // ignorovat vodorovné hrany
            // vytvořené hrany zorientovat a přidat do seznamu

            // najít minimum a maximum pro Y

            // projet všechny body a najít minimální a maximální Y

            // pro všechna Y od min do max včetně
            for (int y = minY; y <= maxY; y++) {

                List<Integer> intersections = new ArrayList<>();

                for (Edge edge : edges) {
                    if (edge.intersectionExists(y)) {
                        intersections.add(edge.getIntersection(y));
                    }
                }

                // projít všechny hrany
                // pokud hrana má průsečík pro dané Y
                // tak vypočítáme průsečík a uložíme hodnotu do seznamu

                Collections.sort(intersections);
                // nebo volitelně implementovat vlastní algoritmus na seřazení

                for (int i = 0; i < intersections.size()-1; i+=2) {
                    for (int x = intersections.get(i); x < intersections.get(i+1); x++) {
                        raster.drawPixel(x,y,fillColor);
                    }

                }
                // vybarvení mezi průsečíky
                // spojení vždy sudého s lichým
                // 0. a 1.; 2. a 3.;...
            }

            // obtáhnutí hranice
            //renderer.drawPolygon(points, edgeColor);

    }
}
