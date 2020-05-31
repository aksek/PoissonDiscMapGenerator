package MapGenerator;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

import java.awt.*;
import java.util.Collection;
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
        cellSize = (int) (minR / Math.sqrt(2));
        gridH = (int) Math.ceil((double) h / cellSize);
        gridW = (int) Math.ceil((double) w / cellSize);
        grid = new Point[gridH ][gridW];
        inactiveSamples = new Vector<>();
        activeSamples = new Vector<>();
        grid = new Point[h][w];
        System.out.println(cellSize);
        runAlgorithm();
    }
    private void runAlgorithm() {
        int x = ThreadLocalRandom.current().nextInt(0, w + 1);
        int y = ThreadLocalRandom.current().nextInt(0, h + 1);
        Point current = new Point(x, y);
        activeSamples.addElement(current);
        grid[current.y/cellSize][current.x/cellSize] = current;
        delayDrawVertex(display, current, 0, true);
        int delay = 1;
        while(!activeSamples.isEmpty() && delay < 1000) {
            int currentIndex = ThreadLocalRandom.current().nextInt(0, activeSamples.size());
//            System.out.println(currentIndex);
            current = activeSamples.get(currentIndex);
            Point candidate;
            int i = 0;
            boolean validCandidate = false;
            do {
                candidate = getNextCandidate(current);
//                System.out.println("current: " + current.x + ' ' + current.y);
//                System.out.println("candidate: " + candidate.x + ' ' + candidate.y);
                validCandidate = checkCandidate(candidate);
                i++;
            } while (i < k && !validCandidate);
            if (validCandidate) {
//                System.out.println("Valid!");
                activeSamples.addElement(candidate);
                grid[candidate.y/cellSize][candidate.x/cellSize] = candidate;
//                System.out.println("Added");
                delayDrawVertex(display, current, delay, true);
            } else {
                System.out.println("Invalid!");
                activeSamples.remove(currentIndex);
                inactiveSamples.addElement(current);
                delayDrawVertex(display, current, delay, false);

            }
            delay++;
        }
    }
    private Point getNextCandidate(Point current) {
        double a = 2 * Math.PI * ThreadLocalRandom.current().nextDouble(0, 1);
        int r = ThreadLocalRandom.current().nextInt(minR, 2 * minR);
        int x = (int) current.x + (int)(r * Math.cos(a));
        int y = (int) current.y + (int)(r * Math.sin(a));
        return new Point(x, y);
    }
    private boolean checkCandidate(Point candidate) {
        if (0 > candidate.x || candidate.x >= w || 0 > candidate.y || candidate.y >= h)
            return false;
        int cellX = candidate.x / cellSize;
        int cellY = candidate.y / cellSize;
        int minCellX = Math.max(cellX - 2, 0);
        int maxCellX = Math.min(cellX + 3, gridW);
        int minCellY = Math.max(cellY - 2, 0);
        int maxCellY = Math.min(cellY + 3, gridH);

        System.out.println("Candidate: " + candidate.x + ' ' + candidate.y);
        System.out.println("X: " + minCellX * cellSize + "Y: " + minCellY * cellSize);

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
    private void delayDrawVertex(Pane display, Point current, int delay, boolean active) {
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
        new Thread(sleeper).start();
    }
    private void addActiveVertex(Pane display, Point current) {
        var vertex = new Circle(current.x, current.y, 2);
        vertex.setFill(Color.RED);
//        System.out.println("active vertex: " + vertex.getCenterX() + " " + vertex.getCenterY());
        display.getChildren().addAll(vertex);
    }
    private void addInactiveVertex(Pane display, Point current) {
        var vertex = new Circle(current.x, current.y, 2);
        System.out.println("inactive vertex: " + vertex.getCenterX() + " " + vertex.getCenterY());
        display.getChildren().removeAll(vertex);
        display.getChildren().addAll(vertex);
    }
}

