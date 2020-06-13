package org.example;

import java.awt.*;
import java.util.Vector;

public class Node {
    private Point vertex;
    private Vector<Point> connections;

    Node(Point point) {
        vertex = point;
    }

    void connect(Point other) {
        connections.addElement(other);
    }
}
