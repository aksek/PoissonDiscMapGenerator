package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Vector;

public class TriangulationTest {
    @Test
    void addSuperTriangle() {
        Triangulation tester = new Triangulation(5, 10);
        tester.addSuperTriangle();
        Assertions.assertTrue(tester.getInvalidTriangles(new Point(1, 1)).size() >= 1 );
        Assertions.assertTrue(tester.getInvalidTriangles(new Point(5, 1)).size() >= 1 );
        Assertions.assertTrue(tester.getInvalidTriangles(new Point(1, 10)).size() >= 1 );
        Assertions.assertTrue(tester.getInvalidTriangles(new Point(5, 10)).size() >= 1 );
    }
}
