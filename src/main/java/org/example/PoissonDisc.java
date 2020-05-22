package org.example;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

import java.awt.*;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import javafx.scene.paint.Color;
import java.util.Vector;

public class PoissonDisc {
    Pane display;

    int minR, k;
    int h, w;
    int gridH, gridW;
    int cellSize;

    Vector<Point> inactiveSamples;
    Vector<Point> activeSamples;
    Point[][] grid;

    PoissonDisc(int minimumR, int maxSampleNr, int height, int width, Pane disp) {
        display = disp;
        minR = minimumR;
        k = maxSampleNr;
        h = height;
        w = width;
        cellSize = (int) Math.sqrt(minR);
        gridH = (int) Math.ceil((double) h / cellSize);
        gridW = (int) Math.ceil((double) w / cellSize);
        grid = new Point[gridH ][gridW];
        runAlgorithm();
    }
    private void runAlgorithm() {
        int x = ThreadLocalRandom.current().nextInt(0, w + 1);
        int y = ThreadLocalRandom.current().nextInt(0, h + 1);
        Point current = new Point(x, y);
        activeSamples.addElement(current);
        addActiveVertex(display, current);
        while(!activeSamples.isEmpty()) {
            int currentIndex = ThreadLocalRandom.current().nextInt(0, activeSamples.size());
            current = activeSamples.get(currentIndex);
            Point candidate;
            int i = 0;
            Boolean validCandidate;
            do {
                candidate = getNextCandidate(current);
                validCandidate = checkCandidate(candidate);
                i++;
            } while (i < k && !validCandidate);
            if (validCandidate) {
                activeSamples.addElement(candidate);
            } else {
                activeSamples.remove(currentIndex);
                inactiveSamples.addElement(current);
            }
        }
    }
    private Point getNextCandidate(Point current) {
        double a = 2 * Math.PI * ThreadLocalRandom.current().nextDouble(0, 1);
        int r = ThreadLocalRandom.current().nextInt(minR, 2 * minR);
        int x = (int) current.x + (int)(r * Math.cos(a));
        int y = (int) current.y + (int)(r * Math.sin(a));
        return new Point(x, y);
    }
    private Boolean checkCandidate(Point candidate) {
        if (0 > candidate.x || candidate.x >= w || 0 > candidate.y || candidate.y >= h)
            return Boolean.FALSE;
        int cellX = candidate.x / cellSize;
        int cellY = candidate.y / cellSize;
        int minCellX = Math.max(cellX - 2, 0);
        int maxCellX = Math.max(cellX + 3, gridW);
        int minCellY = Math.max(cellY - 2, 0);
        int maxCellY = Math.max(cellY + 3, gridH);

        for (int i = minCellX; i < maxCellX; i++) {
            for (int j = minCellY; j < maxCellY; j++) {
                if ((grid[j][i].x - candidate.x) * (grid[j][i].x - candidate.x) +
                        (grid[j][i].y - candidate.y) * (grid[j][i].y - candidate.y) < minR)
                    return false;
            }
        }
        return true;
    }
    private void delayAddVertex(Pane display, Point current, int delay, boolean active) {
        Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    Thread.sleep(200 * delay);
                } catch (InterruptedException e) {
                }
                return null;
            }
        };
        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                if (active) {
                    addActiveVertex(display, current);
                } else {
                    addInactiveVertex(display, current);
                }
            }
        });
    }
    private void addActiveVertex(Pane display, Point current) {
        var vertex = new Circle(current.x, current.y, 2);
        vertex.setStroke(Color.RED);
        System.out.println("active vertex: " + vertex.getCenterX() + " " + vertex.getCenterY());
        display.getChildren().addAll(vertex);
    }
    private void addInactiveVertex(Pane display, Point current) {
        var vertex = new Circle(current.x, current.y, 2);
        System.out.println("inactive vertex: " + vertex.getCenterX() + " " + vertex.getCenterY());
        display.getChildren().addAll(vertex);
    }
}
