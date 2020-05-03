package org.example;

import javafx.animation.ScaleTransition;
import javafx.application.Application;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        Circle circle = new Circle();
        circle.setCenterX(300);
        circle.setCenterY(150);
        circle.setRadius(20);
        circle.setFill(Color.CORNFLOWERBLUE);
        ScaleTransition scaleTransition = new ScaleTransition();
        scaleTransition.setDuration(Duration.millis(2000));
        scaleTransition.setNode(circle);
        scaleTransition.setByY(1.5);
        scaleTransition.setByX(1.5);
        scaleTransition.setCycleCount(50);
        scaleTransition.setAutoReverse(true);
        scaleTransition.play();

        AnchorPane display = new AnchorPane();
        display.setMinSize(400, 500);
        AnchorPane.setTopAnchor(circle, 50.0);
        AnchorPane.setLeftAnchor(circle, 50.0);

        ObservableList list = display.getChildren();
        list.addAll(circle);

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

    public static void main(String[] args) {
        launch();
    }

}