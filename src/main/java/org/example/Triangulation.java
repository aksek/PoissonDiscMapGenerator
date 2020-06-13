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
        triangles = new Vector<>();
        graph = new Vector<>();
    }
    public Tile createSuperTriangle() {
        return new Tile(new Point(-W / 2 - 2, -2), new Point(W * 3 / 2 + 2, -2), new Point(W / 2 , 2 * H + 1));
    }


    public void add(Tile triangle) {
        triangles.addElement(triangle);
        int indA = graph.indexOf(triangle.A());
        if (indA == -1) {
            graph.addElement(triangle.A());
            indA = graph.size() - 1;
        }
        int indB = graph.indexOf(triangle.B());
        if (indB == -1) {
            graph.addElement(triangle.B());
            indB = graph.size() - 1;
        }
        int indC = graph.indexOf(triangle.C());
        if (indC == -1) {
            graph.addElement(triangle.C());
            indC = graph.size() - 1;
        }

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
    public Vector<Node> getCavityEdges(Vector<Tile> invalidTriangles) {
        Vector<Node> cavity = new Vector<>();
        for (Tile triangle : invalidTriangles) {
            if (cavity.isEmpty() || cavity.lastElement() != triangle.A())
                cavity.addElement(triangle.A());
            if (cavity.isEmpty() || cavity.lastElement() != triangle.B())
            cavity.addElement(triangle.B());
            if (cavity.isEmpty() || cavity.lastElement() != triangle.C())
            cavity.addElement(triangle.C());
        }
        return cavity;
    }
    public void remove(Vector<Tile> invalidTriangles) {
        int indInvalidTriangles = 0;
        for (int indTriangles = 0; indTriangles < triangles.size() && indInvalidTriangles < invalidTriangles.size();) {
            if (triangles.get(indTriangles) == invalidTriangles.get(indInvalidTriangles)) {
                triangles.removeElement(indTriangles);
                indInvalidTriangles++;
            } else {
                indTriangles++;
            }
        }
    }
    public Vector<Tile> fill(Vector<Node> cavity) {
        Vector<Tile> newTiles = new Vector<>();
        Node newVertex = graph.lastElement();
        Node A, B;
        for(int i = 0; i < cavity.size() - 1; i++) {
            A = cavity.get(i);
            B = cavity.get(i + 1);
            this.add(new Tile(A, B, newVertex));
        }
        return newTiles;
    }
//    TODO: the tiles are displayed with delay, when the data structures they are using have already been altered. Do something about it
    public Vector<Tile> getFakeTriangles() {
        Vector<Node> superTriangleNodes = new Vector<>();
        superTriangleNodes.addElement(graph.get(0));
        superTriangleNodes.addElement(graph.get(1));
        superTriangleNodes.addElement(graph.get(2));
        Vector<Tile> fakeTriangles = new Vector<>();
        for (Tile triangle : triangles) {
            for (Node vertex : superTriangleNodes) {
                if (triangle.has(vertex)) {
                    triangles.removeElement(triangle);
                    fakeTriangles.addElement(triangle);
                }
            }
        }
        return fakeTriangles;
    }
}
