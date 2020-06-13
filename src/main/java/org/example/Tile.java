package org.example;

import java.awt.*;
import javafx.scene.shape.Polygon;

public class Tile {
    private final Point A;
    private final Point B;
    private final Point C;

    private Polygon triangle;

    public Tile(Point a, Point b, Point c) {
        A = a;
        B = b;
        C = c;
        triangle.getPoints().addAll(
                (double)A.x, (double)A.y,
                (double)B.x, (double)B.y,
                (double)C.x, (double)C.y);
    }
    public int Xa() { return A.x; }
    public int Ya() { return A.y; }
    public int Xb() { return B.x; }
    public int Yb() { return B.y; }
    public int Xc() { return C.x; }
    public int Yc() { return C.y; }

    public Point A() { return A; }
    public Point B() { return B; }
    public Point C() { return C; }

    public Polygon getRepresentation() {
        return triangle;
    }
}
