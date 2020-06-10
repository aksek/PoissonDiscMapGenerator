package MapGenerator;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

import java.util.Random;

public class randomDistribution {
    randomDistribution(Pane display, int speed) {
        Random rand = new Random();
        for (int i = 0; i < 50; i++){
            int finalI = i;
            Task<Void> sleeper = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    try {
                        Thread.sleep(1000 / speed * finalI);
                    } catch (InterruptedException e) {
                    }
                    return null;
                }
            };
            sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
                @Override
                public void handle(WorkerStateEvent event) {
                    addRandomVertex(display, rand);
                }
            });
            new Thread(sleeper).start();
        }
    }

    private void addRandomVertex(Pane display, Random rand) {
        var vertex = new Circle(rand.nextDouble() * 300, rand.nextDouble() * 500, 2);
        System.out.println(vertex.getCenterX() + " " + vertex.getCenterY());
        display.getChildren().addAll(vertex);
    }
}
