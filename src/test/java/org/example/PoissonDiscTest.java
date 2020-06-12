package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;


class PoissonDiscTest {

    @Test
    void checkCandidate() {
        PoissonDisc tester = new PoissonDisc(10, 10,500, 400);
        tester.addVertex(new Point(10, 10));
        Assertions.assertFalse(tester.checkCandidate(new Point(10, 15)));
        Assertions.assertFalse(tester.checkCandidate(new Point(12, 12)));
        Assertions.assertFalse(tester.checkCandidate(new Point(10, 8)));
        Assertions.assertFalse(tester.checkCandidate(new Point(19, 10)));
        Assertions.assertFalse(tester.checkCandidate(new Point(8, 10)));
        Assertions.assertFalse(tester.checkCandidate(new Point(8, 8)));
        Assertions.assertTrue(tester.checkCandidate(new Point(21, 0)));
    }

    @Test
    void nextCandidate() {
        PoissonDisc tester = new PoissonDisc(10, 10,500, 400);
        Point current = new Point(10, 10);
        tester.addVertex(current);
        Point candidate = tester.getNextCandidate(current);
        Assertions.assertTrue(candidate.distance(current) >= 10);
        Assertions.assertTrue(candidate.distance(current) <= 20);
    }
}