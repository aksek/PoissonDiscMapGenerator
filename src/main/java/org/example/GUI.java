package org.example;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.Lighting;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.Random;
import java.util.Vector;

public class GUI {
    public void initUI(Stage stage) {

        Pane display = new Pane();
        display.setMinSize(400, 500);

        Text lblVertices = new Text("Vertices: ");
        Slider sldVertices = new Slider(1.0, 0.0, 100.0);

        GridPane menu = new GridPane();
        menu.setMinSize(500, 500);
        menu.setPadding(new Insets(10, 10, 10, 10));
        menu.setVgap(5);
        menu.setHgap(5);

        menu.add(lblVertices, 0, 0);
        menu.add(sldVertices, 1, 0);

        GridPane root = new GridPane();
        root.add(display, 0, 0);
        root.add(menu, 1, 0);

        Scene scene = new Scene(root, 640, 480);
        scene.setFill(Color.AZURE);
        stage.setTitle("Map Generator");
        stage.setScene(scene);
        stage.show();
        visualiseAlgorithm(display);
    }

    private void visualiseAlgorithm(Pane display) {
        Random rand = new Random();
        for (int i = 0; i < 50; i++){
            int finalI = i;
            Task<Void> sleeper = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    try {
                        Thread.sleep(200 * finalI);
                    } catch (InterruptedException e) {
                    }
                    return null;
                }
            };
            sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
                @Override
                public void handle(WorkerStateEvent event) {
                    addVertex(display, rand);
                    new Thread(sleeper).start();
                }
            });
            new Thread(sleeper).start();
        }
    }

    private void addVertex(Pane display, Random rand) {
        var vertex = new Circle(rand.nextDouble() * 300, rand.nextDouble() * 500, 2);
        System.out.println(vertex.getCenterX() + " " + vertex.getCenterY());
        display.getChildren().addAll(vertex);
    }



}
