package org.example;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;

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
        Vector<Node> cavity;
        int delay = 0;
        for (Point vertex : V) {
            triangulation.add(vertex);
            invalidTriangles = triangulation.getInvalidTriangles(vertex);
            cavity = triangulation.getCavityEdges(invalidTriangles);
            for (Tile current : invalidTriangles) {
                delayUndrawTile(current, delay);
            }
            triangulation.remove(invalidTriangles);
            delay++;
            newTriangles = triangulation.fill(cavity);
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
        sleeper.setOnSucceeded(event -> display.getChildren().addAll(current.getRepresentation()));
        new Thread(sleeper).start();
    }
    private void delayUndrawTile(Tile current, int delay) {
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
        sleeper.setOnSucceeded(event -> display.getChildren().removeAll(current.getRepresentation()));
        new Thread(sleeper).start();
    }
}
