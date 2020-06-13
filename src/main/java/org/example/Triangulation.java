package org.example;

import javafx.scene.shape.Polygon;

import java.awt.*;
import java.util.Vector;

public class Triangulation {
    int W, H;
    Vector<Tile> triangles;
    Vector<Node> graph;
    public Triangulation(int width, int height) {
        W = width;
        H = height;
    }
    public Tile createSuperTriangle() {
        return new Tile(new Point(-W / 2 - 2, -2), new Point(W * 3 / 2 + 2, -2), new Point(W / 2 , 2 * H + 1));
    }


    public void add(Tile triangle) {
        triangles.addElement(triangle);
        int indA = graph.indexOf(triangle.A());
        if (indA == -1) graph.addElement(triangle.A());
        int indB = graph.indexOf(triangle.B());
        if (indB == -1) graph.addElement(triangle.B());
        int indC = graph.indexOf(triangle.C());
        if (indC == -1) graph.addElement(triangle.C());

        graph.get(indA).include(triangle);
        graph.get(indB).include(triangle);
        graph.get(indC).include(triangle);
    }

    public void add(Point vertex) {
        graph.addElement(new Node(vertex));
    }

    public Vector<Tile> getInvalidTriangles(Point vertex) {
        Vector<Tile> invalidTriangles = new Vector<>();
        for(Tile triangle : triangles) {
            if (triangle.circumcircleContains(vertex)) {
                invalidTriangles.addElement(triangle);
            }
        }
        return invalidTriangles;
    }

    public Polygon getEmptyPolygonEdges() {
        return new Polygon();
    }

    public void remove(Vector<Tile> invalidTriangles) {
    }

    public Vector<Tile> fill(Polygon hole, Point vertex) {
        return new Vector<Tile>();
    }

    public Vector<Tile> getFakeTriangles() {
        return new Vector<Tile>();
    }
}
