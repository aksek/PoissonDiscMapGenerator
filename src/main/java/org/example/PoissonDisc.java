package org.example;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

import java.util.Vector;

public class PoissonDisc {
    int r, k;
    int h, w;
    int gridH, gridW;

    Vector<Point> inactiveSamples;
    Vector<Point> grid;
    Vector<Point> activeSamples;

    PoissonDisc(int minR, int maxSampleNr, int height, int width) {
        r = minR;
        k = maxSampleNr;
        h = height;
        w = width;
        gridH = (int) Math.ceil((double) h / Math.sqrt(r));
        gridW = (int) Math.ceil((double) w / Math.sqrt(r));
        runAlgorithm();
    }
    private void runAlgorithm() {
        int x = ThreadLocalRandom.current().nextInt(0, w + 1);
        int y = ThreadLocalRandom.current().nextInt(0, h + 1);
        Point current = new Point(x, y);
        activeSamples.addElement(current);
        while(!activeSamples.isEmpty()) {
            int randomActiveSample = ThreadLocalRandom.current().nextInt(0, activeSamples.size());
            current = activeSamples.get(randomActiveSample);
            Point candidate = new Point(-1, -1);
            for (int i = 0; i < k && !checkCandidate(candidate); i++) {
                candidate = getNextCandidate(current);
            }
        }
    }
    private Point getNextCandidate(Point current) {
        return new Point(1, 2);
    }
    private Boolean checkCandidate(Point candidate) {
        return true;
    }

}
