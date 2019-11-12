package model;

public class Point {

    public int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point withX(int x) {
        return new Point(x, this.y);
    }

    public Point withY(int y) {
        return new Point(this.x, y);
    }
}
