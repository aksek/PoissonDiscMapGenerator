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
import java.util.Vector;

public class TriangulationMain {
    int W, H;
    Vector<Point> V;
    TriangulationMain(Vector<Point> vertices){
        V = vertices;
    }
    private void runAlgorithm() {
        Triangulation triangulation = new Triangulation(W, H);
        triangulation.add(triangulation.createSuperTriangle());
        Vector<Tile> invalidTriangles = new Vector<>();
        Polygon hole = new Polygon();
        for (Point vertex : V) {
            triangulation.add(vertex);
            invalidTriangles = triangulation.getInvalidTriangles();
            hole = triangulation.getEmptyPolygonEdges();
            triangulation.remove(invalidTriangles);
            triangulation.fill(hole, vertex);
        }
        triangulation.removeFakeTriangles();
    }
    private void delayDrawTile(Pane display, Tile current, int delay, int speed) {
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
