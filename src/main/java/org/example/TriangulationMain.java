package org.example;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.TriangleMesh;

import java.awt.*;

public class TriangulationMain {
    TriangulationMain(){}
    private void runAlgorithm() {}
    private void delayDrawVertex(Pane display, Tile current, int delay, int speed) {
        Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    Thread.sleep(1000 / speed * delay);
                } catch (InterruptedException e) {
                }
                return null;
            }
        };
        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                var tile = new Polygon();
                tile.getPoints().addAll(
                        (double)current.Xa(), (double)current.Ya(),
                        (double)current.Xb(), (double)current.Yb(),
                        (double)current.Xc(), (double)current.Yc());
                display.getChildren().addAll(tile);
            }
        });
        new Thread(sleeper).start();
    }
}
