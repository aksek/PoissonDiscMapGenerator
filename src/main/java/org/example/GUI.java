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

public class GUI {
    public void initUI(Stage stage) {

        AnchorPane display = new AnchorPane();
        display.setMinSize(400, 500);

        var rect = new Rectangle(20, 20, 60, 60);
        rect.setEffect(new Lighting());
        rect.setFill(Color.CADETBLUE);

        var tl = new Timeline();

        tl.setCycleCount(2);
        tl.setAutoReverse(true);

        var kv = new KeyValue(rect.translateXProperty(), 200);
        var kf = new KeyFrame(Duration.millis(2000), kv);
        tl.getKeyFrames().addAll(kf);

        tl.play();

        display.getChildren().addAll(rect);


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
}
