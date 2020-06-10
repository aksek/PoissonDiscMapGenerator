package MapGenerator;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;
import javafx.scene.paint.Color;
import java.util.Vector;

public class PoissonDiscMain {
    PoissonDisc poisson;
    Pane display;
    int speed;
    int k;

    PoissonDiscMain(int minimumR, int maxSampleNr, int simulationSpeed, int height, int width, Pane disp) {
        poisson = new PoissonDisc(minimumR, maxSampleNr, height, width);
        speed = simulationSpeed;
        display = disp;
        k = maxSampleNr;
        runAlgorithm();
    }
    private void runAlgorithm() {
        Point current = poisson.getFirstVertex();
        poisson.addVertex(current);
        delayDrawVertex(display, current, 0, true, speed);
        int delay = 1;
        while(!poisson.finished() && delay < 1000) {
            int currentIndex = poisson.getRandomActiveVertexIndex();
            current = poisson.getVertexByIndex(currentIndex);
            Point candidate;
            int i = 0;
            boolean validCandidate = false;
            do {
                candidate = poisson.getNextCandidate(current);
                validCandidate = poisson.checkCandidate(candidate);
                i++;
            } while (i < k && !validCandidate);
            if (validCandidate) {
                poisson.addVertex(candidate);
                delayDrawVertex(display, current, delay, true, speed);
            } else {
                poisson.deactivate(currentIndex, current);
                delayDrawVertex(display, current, delay, false, speed);

            }
            delay++;
        }
    }

    private void delayDrawVertex(Pane display, Point current, int delay, boolean active, int speed) {
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
