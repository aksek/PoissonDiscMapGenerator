package org.example;

import javafx.util.Pair;

import java.util.Vector;

public class PoissonDisc {
    int r, k;

    int h, w;
    int gridH, gridW;

    Vector<Integer>[][] grid;
    Vector<Integer>[][] inactiveSamples;
    Vector<Integer>[][] activeSamples;

    PoissonDisc(int minR, int maxSampleNr, int height, int width) {
        r = minR;
        k = maxSampleNr;
        h = height;
        w = width;
    }
    public boolean finished() {}
    public Pair<Integer, Integer> getNextCandidate {}

}
