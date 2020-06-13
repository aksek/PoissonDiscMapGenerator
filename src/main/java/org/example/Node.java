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
}
