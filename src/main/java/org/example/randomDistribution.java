package org.example;

import javafx.concurrent.Task;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

import java.awt.*;
import java.util.Random;
import java.util.Vector;

public class randomDistribution {
    int W, H;
    Random rand;
    Vector<Point> vertices;
    randomDistribution(Pane display, int speed, int maxVertexNumber, int height, int width) {
        rand = new Random();
        W = width;
        H = height;
        vertices = new Vector<>();
        wakeTriangulation(speed, maxVertexNumber, display);
        for (int i = 0; i < maxVertexNumber; i++){
            int finalI = i;
            Task<Void> sleeper = new Task<>() {
                @Override
                protected Void call() {
                    try {
                        Thread.sleep(1000 / speed * finalI);
                    } catch (InterruptedException ignored) {
                    }
                    return null;
                }
            };
            sleeper.setOnSucceeded(event -> addRandomVertex(display));
            new Thread(sleeper).start();
        }
    }

    private void addRandomVertex(Pane display) {
        var vertex = new Circle(rand.nextDouble() * W, rand.nextDouble() * H, 2);
        System.out.println(vertex.getCenterX() + " " + vertex.getCenterY());
        vertices.addElement(new Point((int)(vertex.getCenterX()), (int)(vertex.getCenterY())));
        display.getChildren().addAll(vertex);
    }

    private void wakeTriangulation(int speed, int delay, Pane display) {
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
        sleeper.setOnSucceeded(event -> new TriangulationMain(vertices, W, H, speed, display, delay));
        new Thread(sleeper).start();
    }
}
