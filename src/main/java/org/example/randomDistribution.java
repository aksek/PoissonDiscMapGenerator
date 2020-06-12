package org.example;

import javafx.concurrent.Task;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

import java.util.Random;

public class randomDistribution {
    int W, H;
    Random rand;
    randomDistribution(Pane display, int speed, int maxVertexNumber, int height, int width) {
        rand = new Random();
        W = width;
        H = height;
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
        display.getChildren().addAll(vertex);
    }
}
