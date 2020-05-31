package MapGenerator;

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
        Spinner spnMindDist = new Spinner(1, 100, 1);

        Text lblSpeed = new Text("Simulation speed: ");
        Spinner spnSpeed = new Spinner(1, 100, 1);

        final ToggleGroup mode = new ToggleGroup();

        RadioButton rbPoisson = new RadioButton("Poisson distribution");
        rbPoisson.setToggleGroup(mode);
        RadioButton rbRandom = new RadioButton("Random distribution");
        rbRandom.setToggleGroup(mode);

        Button btnStart = new Button("Start!");

        EventHandler<ActionEvent> btnStartPressed = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                PoissonDisc poisson = new PoissonDisc(100, 10, 500, 400, display);
            }
        };
        btnStart.setOnAction(btnStartPressed);

        menu.add(lblMinDist, 0, 1);
        menu.add(spnMindDist, 1, 1);
        menu.add(lblSpeed, 0, 2);
        menu.add(spnSpeed, 1, 2);
        menu.add(rbPoisson, 0, 3);
        menu.add(rbRandom, 1, 3);
        menu.add(btnStart, 1, 4);


        GridPane root = new GridPane();
        root.add(display, 0, 0);
        root.add(menu, 1, 0);

        Scene scene = new Scene(root, 640, 480);
        scene.setFill(Color.AZURE);
        stage.setTitle("Map Generator");
        stage.setScene(scene);
        stage.show();
//        randomDistribution rand = new randomDistribution(display);

    }
}
