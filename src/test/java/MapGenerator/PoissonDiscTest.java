package MapGenerator;

import MapGenerator.PoissonDisc;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class PoissonDiscTest {

    @Test
    void checkCandidate() {
        PoissonDisc tester = new PoissonDisc(10, 10,500, 400);
        tester.addVertex(new Point(10, 10));
        Assertions.assertEquals(false, tester.checkCandidate(new Point(10, 15)));
        Assertions.assertEquals(false, tester.checkCandidate(new Point(12, 12)));
        Assertions.assertEquals(false, tester.checkCandidate(new Point(10, 8)));
        Assertions.assertEquals(false, tester.checkCandidate(new Point(19, 10)));
        Assertions.assertEquals(false, tester.checkCandidate(new Point(8, 10)));
        Assertions.assertEquals(false, tester.checkCandidate(new Point(8, 8)));
        Assertions.assertEquals(true, tester.checkCandidate(new Point(14, 14)));
    }

    @Test
    void nextCandidate() {
        PoissonDisc tester = new PoissonDisc(10, 10,500, 400);
        Point current = new Point(10, 10);
        tester.addVertex(current);
        Point candidate = tester.getNextCandidate(current);
        Assertions.assertEquals(true, Math.pow(candidate.x - 10, 2) + Math.pow(candidate.y - 10, 2) >= 100);
        Assertions.assertEquals(true, Math.pow(candidate.x - 10, 2) + Math.pow(candidate.y - 10, 2) <= 400);
    }
}