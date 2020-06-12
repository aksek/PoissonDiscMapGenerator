package org.example;

import java.awt.*;
import java.util.Vector;

public class Triangulation {
    public Triangulation(Vector<Point> vertices) {

    }
    public Tile createSuperTriangle() {

    }
    public Point getCircumcenter(Tile t) {
        int D = 2 * (t.Xa() * (t.Yb() - t.Yc())
                   + t.Xb() * (t.Yc() - t.Ya())
                   + t.Xc() * (t.Ya() - t.Yb()));
        int Ux = ((t.Xa() ^ 2 + t.Ya() ^ 2) * (t.Yb() - t.Yc())
                + (t.Xb() ^ 2 + t.Yb() ^ 2) * (t.Yc() - t.Ya())
                + (t.Xc() ^ 2 + t.Yc() ^ 2) * (t.Ya() - t.Yb()))
                / D;
        int Uy = ((t.Xa() ^ 2 + t.Ya() ^ 2) * (t.Xc() - t.Xb())
                + (t.Xb() ^ 2 + t.Yb() ^ 2) * (t.Xa() - t.Xc())
                + (t.Xc() ^ 2 + t.Yc() ^ 2) * (t.Xb() - t.Xa()))
                / D;
        return new Point(Ux, Uy);
    }
    public int getRadius(Tile t) {
        Point circumcenter = getCircumcenter(t);
        return getRadius(t, circumcenter);
    }
    public int getRadius(Tile t, Point circumcenter) {
        return (int)Math.sqrt((t.Xa() - circumcenter.x) ^ 2 + (t.Ya() - circumcenter.y));
    }
}
