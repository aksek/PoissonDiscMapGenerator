package org.example;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
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

        Random rand = new Random();
        Circle vertex;
        Timeline timeline = new Timeline();
        for (int i = 0; i < 10; i++) {
            vertex = new Circle(rand.nextDouble() * 300, rand.nextDouble() * 500, 10);
            vertex.setOpacity(0.0);
            System.out.println(vertex.getCenterX() + " " + vertex.getCenterY());
            display.getChildren().addAll(vertex);
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(5000),
                    new KeyValue (vertex.opacityProperty(), 1)));
        }
        timeline.play();

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
    }

    private void addVertex(Pane display, Random rand) {
        var vertex = new Circle(rand.nextDouble() * 300, rand.nextDouble() * 500, 10);
        System.out.println(vertex.getCenterX() + " " + vertex.getCenterY());
        display.getChildren().addAll(vertex);
    }

}
