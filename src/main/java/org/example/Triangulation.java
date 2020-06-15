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
        add(new Tile(new Point(-1, H + 1), new Point(-1, -1), new Point(W + 10, -1)));
        add(new Tile(new Point(-10, H + 1), new Point(W + 1, H + 1), new Point(W + 1, -1)));
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

//        graph.get(indA).include(triangle);
//        graph.get(indB).include(triangle);
//        graph.get(indC).include(triangle);
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
    private int  angle(Point center, Point vertex) {
        return (int)(360 / 2 / Math.PI * Math.atan2(vertex.y - center.y, vertex.x - center.x));
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
        System.out.println("Cavity: ");
        for (Node vertex : cavity) {
            System.out.print(vertex.getPoint());
        }
        System.out.println();
        return cavity;
    }
    public void remove(Vector<Tile> invalidTriangles) {
//        for (Node vertex : graph) {
//            vertex.disconnect(invalidTriangles);
//        }
        int indInvalidTriangles = 0;
        for (int indTriangles = 0; indTriangles < triangles.size() && indInvalidTriangles < invalidTriangles.size(); ) {
            if (triangles.get(indTriangles) == invalidTriangles.get(indInvalidTriangles)) {
                System.out.println("Removing triangle " + invalidTriangles.get(indInvalidTriangles).getCircumcenter());
                triangles.removeElementAt(indTriangles);
                indInvalidTriangles++;
            } else {
                indTriangles++;
            }
        }
        System.out.println("Nr of triangles after deletion: " + triangles.size());
    }
    public Vector<Tile> fill(Vector<Node> cavity) {
        Vector<Tile> newTiles = new Vector<>();
        Node newVertex = graph.lastElement();
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
}
