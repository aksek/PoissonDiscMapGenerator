package org.example;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class GUI {
    public void initUI(Stage stage) {

        Display display = new Display();
        display.setMinSize(40, 40);
        display.setPrefSize(400, 500);
        display.setMaxWidth(800);
        display.setBackground(new Background(new BackgroundFill(Color.FLORALWHITE, CornerRadii.EMPTY, new Insets(0))));
        display.setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderStroke.THIN)));


        GridPane menu = new GridPane();
        menu.setMinSize(40, 40);
        menu.setPrefSize(400, 500);
        menu.setMaxSize(480, 1000);
        menu.setPadding(new Insets(20));
        menu.setVgap(10);
        menu.setHgap(10);
        ColumnConstraints column0 = new ColumnConstraints();
        column0.setPercentWidth(50);
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(50);
        menu.getColumnConstraints().addAll(column0, column1);
//        menu.setBackground(new Background(new BackgroundFill(Color.LINEN, new CornerRadii(0), new Insets(0))));

        Text lblMinDist = new Text("Min distance: ");
        Spinner<Integer> spnMinDist = new Spinner<>(1, 100, 50);

        Text lblMaxSampleNr = new Text("Max sample number: ");
        Spinner<Integer> spnMaxSampleNr = new Spinner<>(1, 50, 10);

        Text lblSpeed = new Text("Simulation speed: ");
        Spinner<Integer> spnSpeed = new Spinner<>(1, 100, 50);

        Text lblMaxVertexNr = new Text("Maximum vertex number: ");
        Spinner<Integer> spnMaxVertexNr = new Spinner<>(1, 1000, 500);

        final ToggleGroup mode = new ToggleGroup();

        RadioButton rbPoisson = new RadioButton("Poisson distribution");
        rbPoisson.setToggleGroup(mode);
        rbPoisson.setSelected(true);
        RadioButton rbRandom = new RadioButton("Random distribution");
        rbRandom.setToggleGroup(mode);

        Button btnStart = new Button("Start!");

        EventHandler<ActionEvent> btnStartPressed = e -> {
            if (rbPoisson.isSelected()) {
                new PoissonDiscMain(spnMinDist.getValue(), spnMaxSampleNr.getValue(), spnMaxVertexNr.getValue(), spnSpeed.getValue(), (int) display.getHeight(), (int) display.getWidth(), display);
            } else if (rbRandom.isSelected()) {
                new randomDistribution(display, spnSpeed.getValue(), spnMaxVertexNr.getValue(), (int) display.getHeight(), (int) display.getWidth());
            }
        };
        btnStart.setOnAction(btnStartPressed);

        Button btnClear = new Button("Clear");
        EventHandler<ActionEvent> btnClearPressed = e -> display.getChildren().clear();
        btnClear.setOnAction(btnClearPressed);

        Button btnSave = new Button("Save");
        EventHandler<ActionEvent> btnSavePressed = e -> display.captureAndSaveDisplay();
        btnSave.setOnAction(btnSavePressed);


        menu.add(lblMinDist, 0, 0);
        menu.add(spnMinDist, 1, 0);
        menu.add(lblMaxSampleNr, 0, 1);
        menu.add(spnMaxSampleNr, 1, 1);
        menu.add(lblSpeed, 0, 2);
        menu.add(spnSpeed, 1, 2);
        menu.add(lblMaxVertexNr, 0, 3);
        menu.add(spnMaxVertexNr, 1, 3);
        menu.add(rbPoisson, 0, 4);
        menu.add(rbRandom, 1, 4);
        menu.add(btnStart, 0, 5);
        menu.add(btnClear, 1, 5);
        menu.add(btnSave, 0, 6);


        GridPane root = new GridPane();
        root.setPadding(new Insets(10));
        root.setPrefSize(display.getPrefWidth() + menu.getPrefWidth(), display.getPrefHeight());
//        ColumnConstraints column0 = new ColumnConstraints(display.getPrefWidth(), display.getPrefHeight(), Double.MAX_VALUE);
//        column0.setHgrow(Priority.ALWAYS);
        GridPane.setFillHeight(display, Boolean.TRUE);
        GridPane.setFillWidth(display, Boolean.TRUE);


        root.add(display, 0, 0);
        root.add(menu, 1, 0);

        Scene scene = new Scene(root, root.getPrefWidth(), root.getPrefHeight());
        stage.setTitle("Map Generator");
        stage.setScene(scene);
        stage.show();

    }
}
