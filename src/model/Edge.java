package model;

public class Edge {
    private int x1, y1, x2, y2;

    public Edge(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public Edge(Point p1, Point p2) {
        x1 = p1.x;
        y1 = p1.y;
        x2 = p2.x;
        y2 = p2.y;
    }

    /**
     * Zjistí, zda je hrana vodorovná
     *
     * @return true pokud je vodorovná, jinak false
     */
    public boolean isHorizontal() {
        if (y1 == y2)
            return true;
        else
            return false;
    }

    /**
     * Zorientuje hranu odshora dolů
     */
    public void orientate() {
        if(y1>y2)
        {
            int temp = y2;
            y2 = y1;
            y1 = temp;

            temp = x2;
            x2 = x1;
            x1 = temp;
        }
    }

    /**
     * Zjistí, zda existuje průsečík scanline s hranou
     *
     * @param y y-ová souřadnice scanline
     * @return true pokud průsečík existuje, jinak false
     */
    public boolean intersectionExists(int y) {
        if (y >= y1 && y < y2)
            return true;
        else
            return false;
    }

    /**
     * Vypočítá a vrátí x-ovou souřadnici průsečíku se scanline
     *
     * @param y y-ová souřadnice scanline
     * @return souřadnice x
     */
    public int getIntersection(int y) {
        float k = (x2 - x1) / (y2 - y1);
        float q = x1 - k * y1;
        return Math.round((k * y) + q);
    }

    /**
     * Zjistí, na které straně přímky tvořené touto úsečkou se nachází bod z parametru
     *
     * @param p testovaný bod
     * @return true pokud se nachází uvnitř (za předpokladu správné orientace)
     */
    public boolean inside(Point p) {
        Point t = new Point(x2 - x1, y2 - y1);
        //Point n = new Point(t.y, -t.x);
        @SuppressWarnings("SuspiciousNameCombination")
        Point n = new Point(-t.y, t.x);
        Point v = new Point(p.x - x1, p.y - y1);
        return (v.x * n.x + v.y * n.y < 0);
    }

    /**
     * Vypočítání průsečíku dvou hran
     *
     * @param v1 první bod druhé hrany
     * @param v2 druhý bod druhé hrany
     * @return průsečík
     */
    public Point getIntersection(Point v1, Point v2) {
        float x0 = ((v1.x * v2.y - v1.y * v2.x) * (x1 - x2) - (x1 * y2 - y1 * x2) * (v1.x - v2.x))
                / (float) ((v1.x - v2.x) * (y1 - y2) - (x1 - x2) * (v1.y - v2.y));

        float y0 = ((v1.x * v2.y - v1.y * v2.x) * (y1 - y2) - (x1 * y2 - y1 * x2) * (v1.y - v2.y))
                / (float) ((v1.x - v2.x) * (y1 - y2) - (x1 - x2) * (v1.y - v2.y));

        return new Point(Math.round(x0), Math.round(y0));
    }
}
