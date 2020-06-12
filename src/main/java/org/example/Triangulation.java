package org.example;

import java.awt.*;
import java.util.Vector;

public class Triangulation {
    public Triangulation(Vector<Point> vertices) {

    }
    public Tile createSuperTriangle() {
        return new Tile(new Point(1, 1), new Point(2, 1), new Point(1, 2));
    }
    public Point getCircumcenter(Tile t) {
        int D = 2 * (t.Xa() * (t.Yb() - t.Yc())
                   + t.Xb() * (t.Yc() - t.Ya())
                   + t.Xc() * (t.Ya() - t.Yb()));
        int Ux = (int)(((Math.pow(t.Xa(), 2) + Math.pow(t.Ya(), 2)) * (t.Yb() - t.Yc())
                      + (Math.pow(t.Xb(), 2) + Math.pow(t.Yb(), 2)) * (t.Yc() - t.Ya())
                      + (Math.pow(t.Xc(), 2) + Math.pow(t.Yc(), 2)) * (t.Ya() - t.Yb()))
                       / D);
        int Uy = (int)(((Math.pow(t.Xa(), 2) + Math.pow(t.Ya(), 2)) * (t.Xc() - t.Xb())
                      + (Math.pow(t.Xb(), 2) + Math.pow(t.Yb(), 2)) * (t.Xa() - t.Xc())
                      + (Math.pow(t.Xc(), 2) + Math.pow(t.Yc(), 2)) * (t.Xb() - t.Xa()))
                       / D);
        return new Point(Ux, Uy);
    }
    public int getRadius(Tile t) {
        Point circumcenter = getCircumcenter(t);
        return getRadius(t, circumcenter);
    }
    public int getRadius(Tile t, Point circumcenter) {
        return (int)circumcenter.distance(t.A());
    }
}
