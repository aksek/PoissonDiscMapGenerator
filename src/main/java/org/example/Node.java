package org.example;

import java.awt.*;

public class Node {
    private final Point vertex;

    Node(Point point) {
        vertex = point;
    }
    Point getPoint() {
        return vertex;
    }
    boolean offBoundaries(int W, int H) {
        return vertex.x < 0 || vertex.x > W || vertex.y < 0 || vertex.y > H;
    }
}
