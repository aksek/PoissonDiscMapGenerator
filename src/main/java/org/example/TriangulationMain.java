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
    int speed;
    Vector<Point> V;
    Pane display;
    TriangulationMain(Vector<Point> vertices, int width, int height, int simulationSpeed, Pane disp){
        V = vertices;
        W = width;
        H = height;
        speed = simulationSpeed;
        display = disp;
        runAlgorithm();
    }
    private void runAlgorithm() {
        Triangulation triangulation = new Triangulation(W, H);
        triangulation.add(triangulation.createSuperTriangle());
        Vector<Tile> invalidTriangles, newTriangles;
        Polygon hole;
        int delay = 0;
        for (Point vertex : V) {
            triangulation.add(vertex);
            invalidTriangles = triangulation.getInvalidTriangles();
            hole = triangulation.getEmptyPolygonEdges();
            for (Tile current : invalidTriangles) {
                delayUndrawTile(current, delay);
            }
            triangulation.remove(invalidTriangles);
            delay++;
            newTriangles = triangulation.fill(hole, vertex);
            for (Tile current : newTriangles) {
                delayDrawTile(current, delay);
            }
            delay++;
        }
        Vector<Tile> fakeTriangles = triangulation.getFakeTriangles();
        for (Tile current : fakeTriangles) {
            delayUndrawTile(current, delay);
        }
    }
    private void delayDrawTile(Tile current, int delay) {
        Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    Thread.sleep(1000 / speed * delay);
                } catch (InterruptedException ignored) {
                }
                return null;
            }
        };
        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                display.getChildren().addAll(current.getRepresentation());
            }
        });
        new Thread(sleeper).start();
    }
    private void delayUndrawTile(Tile current, int delay) {
        Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    Thread.sleep(1000 / speed * delay);
                } catch (InterruptedException ignored) {
                }
                return null;
            }
        };
        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                display.getChildren().removeAll(current.getRepresentation());
            }
        });
        new Thread(sleeper).start();
    }
}
