package org.example;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;

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
        triangulation.addSuperTriangle();
        Vector<Tile> invalidTriangles, newTriangles;
        Vector<Node> cavity;
        for (Point vertex : V) {
            System.out.println("Adding vertex: " + vertex.x + " " + vertex.y);
            triangulation.add(vertex);
            invalidTriangles = triangulation.getInvalidTriangles(vertex);
            System.out.println("Invalid: " + invalidTriangles);
            System.out.println("UNDRAWING");
            delayUndrawTiles(invalidTriangles, delay);
//            for (Tile triangle : invalidTriangles) {
//                display.getChildren().removeAll(triangle.getRepresentation());
//            }
            triangulation.remove(invalidTriangles);
            delay++;
            cavity = triangulation.getCavityEdges(invalidTriangles, vertex);
            newTriangles = triangulation.fill(cavity);
            System.out.println("Cavity: " + cavity);
            System.out.println("New triangles: " + newTriangles);
            System.out.println("DRAWING");
            delayDrawTiles(newTriangles, delay);
//            for (Tile triangle : newTriangles) {
//                System.out.println("Tile: " + triangle.getCircumcenter());
//                display.getChildren().addAll((triangle.getRepresentation()));
//            }
            delay++;
        }
        Vector<Tile> fakeTriangles = triangulation.getFakeTriangles();
        delayUndrawTiles(fakeTriangles, delay);
//        for (Tile triangle : fakeTriangles) {
//            display.getChildren().removeAll(triangle.getRepresentation());
//        }
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
        sleeper.setOnSucceeded(event -> {
            System.out.println("adding edges");
            for (Tile triangle : current) {
                display.getChildren().addAll(triangle.getRepresentation());
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
        sleeper.setOnSucceeded(event -> {
            for (Tile triangle : current) {
                display.getChildren().removeAll(triangle.getRepresentation());
            }
        });
        new Thread(sleeper).start();
    }
}
