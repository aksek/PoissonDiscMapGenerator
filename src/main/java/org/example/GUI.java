package org.example;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class GUI {
    public void initUI(Stage stage) {

        Pane display = new Pane();
        display.setMinSize(400, 500);


        GridPane menu = new GridPane();
        menu.setMinSize(500, 500);
        menu.setPadding(new Insets(10, 10, 10, 10));
        menu.setVgap(5);
        menu.setHgap(5);

        Text lblMinDist = new Text("Min distance: ");
        Spinner<Integer> spnMinDist = new Spinner<Integer>(1, 100, 1);

        Text lblMaxSampleNr = new Text("Max sample number: ");
        Spinner<Integer> spnMaxSampleNr = new Spinner<Integer>(1, 50, 1);

        Text lblSpeed = new Text("Simulation speed: ");
        Spinner<Integer> spnSpeed = new Spinner<Integer>(1, 100, 1);

        final ToggleGroup mode = new ToggleGroup();

        RadioButton rbPoisson = new RadioButton("Poisson distribution");
        rbPoisson.setToggleGroup(mode);
        RadioButton rbRandom = new RadioButton("Random distribution");
        rbRandom.setToggleGroup(mode);

        Button btnStart = new Button("Start!");

        EventHandler<ActionEvent> btnStartPressed = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if (rbPoisson.isSelected()) {
                    PoissonDiscMain poisson = new PoissonDiscMain(spnMinDist.getValue(), spnMaxSampleNr.getValue(), spnSpeed.getValue(), 500, 400, display);
                } else if (rbRandom.isSelected()) {
                    randomDistribution rand = new randomDistribution(display, spnSpeed.getValue());
                }
            }
        };
        btnStart.setOnAction(btnStartPressed);

        menu.add(lblMinDist, 0, 0);
        menu.add(spnMinDist, 1, 0);
        menu.add(lblMaxSampleNr, 0, 1);
        menu.add(spnMaxSampleNr, 1, 1);
        menu.add(lblSpeed, 0, 2);
        menu.add(spnSpeed, 1, 2);
        menu.add(rbPoisson, 0, 3);
        menu.add(rbRandom, 1, 3);
        menu.add(btnStart, 1, 4);


        GridPane root = new GridPane();
        root.add(display, 0, 0);
        root.add(menu, 1, 0);

        Scene scene = new Scene(root, 760, 480);
        scene.setFill(Color.AZURE);
        stage.setTitle("Map Generator");
        stage.setScene(scene);
        stage.show();

    }
}
