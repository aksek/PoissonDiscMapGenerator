package org.example;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;

import java.awt.*;
import java.util.Vector;

public class TriangulationMain {
    Triangulation triangulation;
    int speed;
    Vector<Point> V;
    Pane display;
    TriangulationMain(Vector<Point> vertices, int width, int height, int simulationSpeed, Pane disp, int delay){
        System.out.println("Triangulation created");
        V = vertices;
        triangulation = new Triangulation(width, height);
        speed = simulationSpeed;
        display = disp;
        runAlgorithm(delay);
    }
    private void runAlgorithm(int delay) {
        triangulation.add(triangulation.createSuperTriangle());
        Vector<Tile> invalidTriangles, newTriangles;
        Vector<Node> cavity;
        for (Point vertex : V) {
            System.out.println("Adding vertex: " + vertex.x + " " + vertex.y);
            triangulation.add(vertex);
            invalidTriangles = triangulation.getInvalidTriangles(vertex);
            cavity = triangulation.getCavityEdges(invalidTriangles);
            delayUndrawTiles(invalidTriangles, delay);
            triangulation.remove(invalidTriangles);
            delay++;
            newTriangles = triangulation.fill(cavity);
            delayDrawTiles(newTriangles, delay);
            delay++;
        }
        Vector<Tile> fakeTriangles = triangulation.getFakeTriangles();
        delayUndrawTiles(fakeTriangles, delay);
    }
    private void delayDrawTiles(Vector<Tile> current, int delay) {
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
        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                System.out.println("adding edges");
                for (Tile triangle : current) {
                    display.getChildren().addAll(triangle.getRepresentation());
                }
            }
        });
        new Thread(sleeper).start();
    }
    private void delayUndrawTiles(Vector<Tile> current, int delay) {
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
        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                for (Tile triangle : current) {
                    display.getChildren().removeAll(triangle.getRepresentation());
                }
            }
        });
        new Thread(sleeper).start();
    }
}
