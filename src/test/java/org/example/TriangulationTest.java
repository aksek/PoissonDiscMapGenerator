package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Vector;

public class TriangulationTest {
    @Test
    void createSuperTriangle() {
        Vector<Point> vertices = new Vector<>();
        vertices.addElement(new Point(1, 1));
        vertices.addElement(new Point(5, 1));
        vertices.addElement(new Point(1, 10));
        vertices.addElement(new Point(5, 10));
        Triangulation tester = new Triangulation(5, 10);
        Tile superTriangle = tester.createSuperTriangle();
        for (Point vertex : vertices) {
            Assertions.assertTrue(superTriangle.contains(vertex));
        }
    }

    @Test
    void getCircumcenter() {
        Vector<Point> vertices = new Vector<>();
        Triangulation tester = new Triangulation(50, 60);
        Tile t = new Tile(new Point(1, 1), new Point(50, 20), new Point(30, 60));
        Point circumcenter = tester.getCircumcenter(t);
        System.out.println("circumcenter: " + circumcenter.x + " " + circumcenter.y);

        Assertions.assertTrue((int)circumcenter.distance(t.A().getPoint()) - (int)circumcenter.distance(t.B().getPoint()) <= 1);
        Assertions.assertTrue((int)circumcenter.distance(t.B().getPoint()) - (int)circumcenter.distance(t.C().getPoint()) <= 1);
    }
}
