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
        Triangulation tester = new Triangulation(vertices, 5, 10);
        Tile superTriangle = tester.createSuperTriangle();
        for (Point vertex : vertices) {
            Assertions.assertTrue(onSameSideOfLine(vertex, superTriangle.A(), superTriangle.B(), superTriangle.C()));
            Assertions.assertTrue(onSameSideOfLine(vertex, superTriangle.B(), superTriangle.C(), superTriangle.A()));
            Assertions.assertTrue(onSameSideOfLine(vertex, superTriangle.C(), superTriangle.A(), superTriangle.B()));
        }

    }
    boolean onSameSideOfLine(Point p1, Point p2, Point lineThrough1, Point lineThrough2) {
        double a = (double)(lineThrough2.y - lineThrough1.y)/(lineThrough2.x - lineThrough1.x);
        double b = lineThrough1.y - a * lineThrough1.x;
        return (p1.y <= a * p1.x + b) && (p2.y <= a * p2.x + b) ||
                (p1.y >= a * p1.x + b) && (p2.y >= a * p2.x + b);
    }
    @Test
    void getCircumcenter() {
        Vector<Point> vertices = new Vector<>();
        Triangulation tester = new Triangulation(vertices, 50, 60);
        Tile t = new Tile(new Point(1, 1), new Point(50, 20), new Point(30, 60));
        Point circumcenter = tester.getCircumcenter(t);
        System.out.println("circumcenter: " + circumcenter.x + " " + circumcenter.y);

        Assertions.assertTrue((int)circumcenter.distance(t.A()) - (int)circumcenter.distance(t.B()) <= 1);
        Assertions.assertTrue((int)circumcenter.distance(t.B()) - (int)circumcenter.distance(t.C()) <= 1);
    }
}
