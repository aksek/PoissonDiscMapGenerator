package org.example;

import java.awt.*;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Tile {
    private final Node A;
    private final Node B;
    private final Node C;

    private final Point circumcenter;

    private Polygon representation;

    public Tile(Point a, Point b, Point c) {
        A = new Node(a);
        B = new Node(b);
        C = new Node(c);
        createRepresentation();
        circumcenter = calcCircumcenter();
    }
    public Tile(Node a, Node b, Node c) {
        A = a;
        B = b;
        C = c;
        createRepresentation();
        circumcenter = calcCircumcenter();
    }
    private void createRepresentation() {
        representation = new Polygon();
        representation.getPoints().addAll(
                (double)A.getPoint().x, (double)A.getPoint().y,
                (double)B.getPoint().x, (double)B.getPoint().y,
                (double)C.getPoint().x, (double)C.getPoint().y);
        representation.setStroke(Color.GRAY);
        representation.setFill(Color.TRANSPARENT);
    }

    public int Xa() { return A.getPoint().x; }
    public int Ya() { return A.getPoint().y; }
    public int Xb() { return B.getPoint().x; }
    public int Yb() { return B.getPoint().y; }
    public int Xc() { return C.getPoint().x; }
    public int Yc() { return C.getPoint().y; }

    public Node A() { return A; }
    public Node B() { return B; }
    public Node C() { return C; }

    public Polygon getRepresentation() {
        return representation;
    }

    public boolean circumcircleContains(Point vertex) {
        return vertex.distance(this.getCircumcenter()) <= this.getRadius();
    }

    private Point calcCircumcenter() {
        int D = 2 * (this.Xa() * (this.Yb() - this.Yc())
                + this.Xb() * (this.Yc() - this.Ya())
                + this.Xc() * (this.Ya() - this.Yb()));
        int Ux = (int)(((Math.pow(this.Xa(), 2) + Math.pow(this.Ya(), 2)) * (this.Yb() - this.Yc())
                + (Math.pow(this.Xb(), 2) + Math.pow(this.Yb(), 2)) * (this.Yc() - this.Ya())
                + (Math.pow(this.Xc(), 2) + Math.pow(this.Yc(), 2)) * (this.Ya() - this.Yb()))
                / D);
        int Uy = (int)(((Math.pow(this.Xa(), 2) + Math.pow(this.Ya(), 2)) * (this.Xc() - this.Xb())
                + (Math.pow(this.Xb(), 2) + Math.pow(this.Yb(), 2)) * (this.Xa() - this.Xc())
                + (Math.pow(this.Xc(), 2) + Math.pow(this.Yc(), 2)) * (this.Xb() - this.Xa()))
                / D);
        return new Point(Ux, Uy);
    }
    public int getRadius() {
        return this.getRadius(this.getCircumcenter());
    }
    public int getRadius(Point circumcenter) {
        return (int)circumcenter.distance(this.A().getPoint());
    }
    public Point getCircumcenter() {
        return circumcenter;
    }
    boolean fake(int W, int H) {
        return A.offBoundaries(W, H) || B.offBoundaries(W, H) || C.offBoundaries(W, H);
    }
}
