package org.example;

import javafx.concurrent.Task;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.awt.*;
import java.util.Vector;

public class PoissonDiscMain {
    PoissonDisc poisson;
    Pane display;
    int speed;
    int k;
    int maxVertices;
    int W, H;

    PoissonDiscMain(int minimumR, int maxSampleNr, int maxVertexNumber, int simulationSpeed, int height, int width, Pane disp) {
        poisson = new PoissonDisc(minimumR, maxSampleNr, height, width);
        speed = simulationSpeed;
        display = disp;
        k = maxSampleNr;
        maxVertices = maxVertexNumber;
        W = width;
        H = height;
        runAlgorithm();
    }
    private void runAlgorithm() {
        Point current = poisson.getFirstVertex();
        poisson.addVertex(current);
        delayDrawVertex(display, current, 0, true, speed);
        int vertexCounter = 1;
        int delay = 1;
        while(!poisson.finished() && vertexCounter < maxVertices) {
            int currentIndex = poisson.getRandomActiveVertexIndex();
            current = poisson.getVertexByIndex(currentIndex);
            Point candidate;
            int i = 0;
            boolean validCandidate;
            do {
                candidate = poisson.getNextCandidate(current);
                validCandidate = poisson.checkCandidate(candidate);
                i++;
            } while (i < k && !validCandidate);

            if (validCandidate) {
                vertexCounter++;
                poisson.addVertex(candidate);
                delayDrawVertex(display, current, delay, true, speed);
            } else {
                poisson.deactivate(currentIndex, current);
                delayDrawVertex(display, current, delay, false, speed);
            }
            System.out.println("vertexCounter: " + vertexCounter);
            delay++;
        }
        deactivateRemainingActiveSamples(delay);
    }

    private void delayDrawVertex(Pane display, Point current, int delay, boolean active, int speed) {
        Task<Void> sleeper = new Task<>() {
            @Override
            protected Void call() {
                try {
                    Thread.sleep(1000 / speed * delay);
                } catch (InterruptedException ignored) {
                }
                return null;
            }
        };
        sleeper.setOnSucceeded(event -> {
            if (active) {
                addActiveVertex(display, current);
            } else {
                addInactiveVertex(display, current);
            }
        });
        new Thread(sleeper).start();
    }
    private void addActiveVertex(Pane display, Point current) {
        var vertex = new Circle(current.x, current.y, 2);
        vertex.setFill(Color.RED);
        display.getChildren().addAll(vertex);
    }
    private void addInactiveVertex(Pane display, Point current) {
        var vertex = new Circle(current.x, current.y, 2);
        System.out.println("inactive vertex: " + vertex.getCenterX() + " " + vertex.getCenterY());
        display.getChildren().addAll(vertex);
    }
    private void deactivateRemainingActiveSamples(int delay) {
        Task<Void> sleeper = new Task<>() {
            @Override
            protected Void call() {
                try {
                    Thread.sleep(1000 / speed * delay);
                } catch (InterruptedException ignored) {
                }
                return null;
            }
        };
        sleeper.setOnSucceeded(event -> {
            Vector<Point> vertices = poisson.remainingActiveSamples();
            for (Point current : vertices) {
                var vertex = new Circle(current.x, current.y, 2);
                System.out.println("inactive vertex: " + vertex.getCenterX() + " " + vertex.getCenterY());
                display.getChildren().addAll(vertex);
            }
            wakeTriangulation();
        });
        new Thread(sleeper).start();
    }
    private void wakeTriangulation() {
        Task<Void> sleeper = new Task<>() {
            @Override
            protected Void call() {
                try {
                    Thread.sleep(1000 / speed);
                } catch (InterruptedException ignored) {
                }
                return null;
            }
        };
        sleeper.setOnSucceeded(event -> new TriangulationMain(poisson.getGeneratedVertices(), W, H, speed, display, 0));
        new Thread(sleeper).start();
    }
}

