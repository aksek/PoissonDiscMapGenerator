package org.example;

import java.awt.*;

public class Tile {
    private final Point A;
    private final Point B;
    private final Point C;

    public Tile(Point a, Point b, Point c) {
        A = a;
        B = b;
        C = c;
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
}
