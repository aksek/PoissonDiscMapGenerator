package org.example;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Random;

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
//        randomDistribution rand = new randomDistribution(display);
        PoissonDisc poisson = new PoissonDisc(100, 10, 500, 400, display);
    }
}
