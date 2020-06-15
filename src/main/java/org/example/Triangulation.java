package org.example;

import java.awt.*;
import java.util.Comparator;
import java.util.Vector;

public class Triangulation {
    int W, H;
    Vector<Tile> triangles;
    Vector<Node> graph;
    Point currentCavityCenter;

    public Triangulation(int width, int height) {
        W = width;
        H = height;
        triangles = new Vector<>();
        graph = new Vector<>();
    }
    public void addSuperTriangle() {
        Node A = new Node(new Point(-1,1));
        Node B = new Node(new Point(-1, H + 1));
        Node C = new Node(new Point(W + 1, -1));
        Node D = new Node(new Point(W + 1, H + 1));
        add(new Tile(A, B, C));
        add(new Tile(B, C, D));
    }

    public void add(Tile triangle) {
        triangles.addElement(triangle);
        if (!graph.contains(triangle.A())) {
            graph.addElement(triangle.A());
        }
        if (!graph.contains(triangle.B())) {
            graph.addElement(triangle.B());
        }
        if (!graph.contains(triangle.C())) {
            graph.addElement(triangle.C());
        }
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
    private int  angle(Point center, Point vertex) {
        return (int)(23040 * Math.atan2(vertex.y - center.y, vertex.x - center.x));
    }
    class compareByAngle implements Comparator<Node> {
        public int compare(Node a, Node b) {
            return angle(currentCavityCenter, a.getPoint()) - angle(currentCavityCenter, b.getPoint());
        }
    }
    public Vector<Node> getCavityEdges(Vector<Tile> invalidTriangles, Point cavityCenter) {
        Vector<Node> cavity = new Vector<>();
        for (Tile triangle : invalidTriangles) {
            if (!cavity.contains(triangle.A()))
                cavity.addElement(triangle.A());
            if (!cavity.contains(triangle.B()))
            cavity.addElement(triangle.B());
            if (!cavity.contains(triangle.C()))
            cavity.addElement(triangle.C());
        }
        currentCavityCenter = cavityCenter;
        cavity.sort(new compareByAngle());
        return cavity;
    }
    public void remove(Vector<Tile> invalidTriangles) {
        int indInvalidTriangles = 0;
        for (int indTriangles = 0; indTriangles < triangles.size() && indInvalidTriangles < invalidTriangles.size(); ) {
            if (triangles.get(indTriangles) == invalidTriangles.get(indInvalidTriangles)) {
                triangles.removeElementAt(indTriangles);
                indInvalidTriangles++;
            } else {
                indTriangles++;
            }
        }
    }
    public Vector<Tile> fill(Vector<Node> cavity, Point vertex) {
        Vector<Tile> newTiles = new Vector<>();
        Node newVertex = new Node(vertex);
        graph.addElement(newVertex);
        Node A = cavity.firstElement();
        Node B = cavity.lastElement();
        Tile newTile = new Tile(A, B, newVertex);
        this.add(newTile);
        newTiles.addElement(newTile);
        for(int i = 0; i < cavity.size() - 1; i++) {
            A = cavity.get(i);
            B = cavity.get(i + 1);
            newTile = new Tile(A, B, newVertex);
            this.add(newTile);
            newTiles.addElement(newTile);
        }
        return newTiles;
    }
    public Vector<Tile> getFakeTriangles() {
        Vector<Tile> fakeTriangles = new Vector<>();
        for (Tile triangle : triangles) {
            if(triangle.fake(W, H)) {
                fakeTriangles.addElement(triangle);
            }
        }
        return fakeTriangles;
    }
    public Vector<Tile> getAllTriangles() {
        return triangles;
    }
}
