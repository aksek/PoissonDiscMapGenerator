package org.example;

import java.awt.*;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

public class PoissonDisc {
    int minR, k;
    int h, w;
    int gridH, gridW;
    int cellSize;

    Vector<Point> inactiveSamples;
    Vector<Point> activeSamples;
    Point[][] grid;

    public PoissonDisc(int minimumR, int maxSampleNr,  int height, int width) {
        minR = minimumR;
        k = maxSampleNr;
        h = height;
        w = width;
        cellSize = (int) (minR / Math.sqrt(2));
        gridH = (int) Math.ceil((double) h / cellSize);
        gridW = (int) Math.ceil((double) w / cellSize);
        grid = new Point[gridH ][gridW];
        inactiveSamples = new Vector<>();
        activeSamples = new Vector<>();
        grid = new Point[h][w];
    }
    public void deactivate(int index, Point vertex) {
        activeSamples.removeElementAt(index);
        inactiveSamples.addElement(vertex);
    }
    public Point getVertexByIndex(int index) {
        return activeSamples.get(index);
    }
    public int getRandomActiveVertexIndex() {
        return ThreadLocalRandom.current().nextInt(0, activeSamples.size());
    }
    public boolean finished() {
        return activeSamples.isEmpty();
    }
    public Point getFirstVertex() {
        int x = ThreadLocalRandom.current().nextInt(1, w);
        int y = ThreadLocalRandom.current().nextInt(1, h);
        return new Point(x, y);
    }

    public void addVertex(Point current) {
        activeSamples.addElement(current);
        grid[current.y/cellSize][current.x/cellSize] = current;
    }

    public Point getNextCandidate(Point current) {
        double a = 2 * Math.PI * ThreadLocalRandom.current().nextDouble(0, 1);
        int r = ThreadLocalRandom.current().nextInt(minR, 2 * minR);
        int x = current.x + (int)Math.ceil(r * Math.cos(a));
        int y = current.y + (int)Math.ceil(r * Math.sin(a));
        return new Point(x, y);
    }
    public boolean checkCandidate(Point candidate) {
        if (2 > candidate.x || candidate.x > w - 2 || 2 > candidate.y || candidate.y > h - 2)
            return false;
        int cellX = candidate.x / cellSize;
        int cellY = candidate.y / cellSize;
        int minCellX = Math.max(cellX - 2, 0);
        int maxCellX = Math.min(cellX + 3, gridW);
        int minCellY = Math.max(cellY - 2, 0);
        int maxCellY = Math.min(cellY + 3, gridH);

        int distance;
        for (int i = minCellX; i < maxCellX; i++) {
            for (int j = minCellY; j < maxCellY; j++) {
                if (grid[j][i] != null) {
                    distance = (grid[j][i].x - candidate.x) * (grid[j][i].x - candidate.x) +
                            (grid[j][i].y - candidate.y) * (grid[j][i].y - candidate.y);
                    System.out.println(distance);
                    if (distance < minR * minR)
                        return false;
                }
            }
        }
        return true;
    }
    public Vector<Point> getGeneratedVertices() {
        return inactiveSamples;
    }

    public Vector<Point> remainingActiveSamples() {
        Vector<Point> redVertices = new Vector<>();
        for (Point vertex : activeSamples) {
            inactiveSamples.addElement(vertex);
            redVertices.addElement(vertex);
        }
        return redVertices;
    }
}
