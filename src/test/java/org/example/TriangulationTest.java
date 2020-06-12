package org.example;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.awt.*;
import java.util.Vector;

public class TriangulationTest {
    @Disabled
    @Test
    void createSuperTriangle() {
        Vector<Point> vertices = new Vector<>();
        vertices.addElement(new Point(1, 1));
        vertices.addElement(new Point(5, 1));
        vertices.addElement(new Point(1, 10));
        vertices.addElement(new Point(5, 10));
        Triangulation tester = new Triangulation(Vector<Point> vertices);
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
        if ((p1.y < a * p1.x + b) == (p2.y < a * p2.x + b))
            return true;
        else return false;
    }
    @Test
    void getInvalidTriangles() {

    }
    @Test
    void getPolygonVertices() {

    }
}
