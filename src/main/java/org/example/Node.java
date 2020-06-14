package org.example;

import java.awt.*;
import java.util.Vector;

public class Node {
    private Point vertex;
    private Vector<Tile> partOf;

    Node(Point point) {
        vertex = point;
        partOf = new Vector<>();
    }
    Point getPoint() {
        return vertex;
    }
    void include(Tile triangle) {
        partOf.addElement(triangle);
    }
    void disconnect(Vector<Tile> invalidTriangles) {
        int indPartOf = 0;
        for (int indInvalidTriangles = 0; indInvalidTriangles < invalidTriangles.size() && indPartOf < partOf.size(); ) {
            if (partOf.get(indPartOf) == invalidTriangles.get(indInvalidTriangles)) {
                System.out.println("Disconnecting " + invalidTriangles.get(indInvalidTriangles).getCircumcenter() + " from " + this.vertex);
                partOf.removeElement(indPartOf);
                indInvalidTriangles++;
            }
            indPartOf++;
        }
    }
}
