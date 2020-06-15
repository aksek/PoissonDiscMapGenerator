package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class TileTest {

    @Test
    void getCircumcenter() {
        Tile t = new Tile(new Point(1, 1), new Point(50, 20), new Point(30, 60));
        Point circumcenter = t.getCircumcenter();
        System.out.println("circumcenter: " + circumcenter.x + " " + circumcenter.y);

        Assertions.assertTrue((int)circumcenter.distance(t.A().getPoint()) - (int)circumcenter.distance(t.B().getPoint()) <= 1);
        Assertions.assertTrue((int)circumcenter.distance(t.B().getPoint()) - (int)circumcenter.distance(t.C().getPoint()) <= 1);
    }
}
