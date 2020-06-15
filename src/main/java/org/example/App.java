package org.example;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        GUI gui = new GUI();
        gui.initUI(stage);
    }

    public static void main(String[] args) {
        launch();
    }

}