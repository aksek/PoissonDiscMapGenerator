package org.example;

import javafx.concurrent.Task;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.Random;
import java.util.Vector;

public class Colouring {
    private final Vector<Color> altitudes = new Vector<>();
    Colouring (Vector<Tile> tiles, Pane display, int delay, int speed) {
        altitudes.addElement(Color.NAVY);
        altitudes.addElement(Color.BLUE);
        altitudes.addElement(Color.SANDYBROWN);
        altitudes.addElement(Color.LIMEGREEN);
        altitudes.addElement(Color.GREEN);
        altitudes.addElement(Color.FORESTGREEN);
        altitudes.addElement(Color.DARKGREEN);
        altitudes.addElement(Color.DARKGREEN);
        altitudes.addElement(Color.DARKOLIVEGREEN);
        altitudes.addElement(Color.LIGHTGREY);

        Task<Void> sleeper = new Task<>() {
            @Override
            protected Void call() {
                try {
                    Thread.sleep(1000 / speed * delay);
                } catch (InterruptedException ignored) {
                }
                return null;
            }
        };
        sleeper.setOnSucceeded(event -> run(tiles));
        new Thread(sleeper).start();
    }
    private void run(Vector<Tile> tiles) {
        Random rand = new Random();
        int color = altitudes.size() - 1;
        for(Tile tile : tiles) {
            tile.getRepresentation().setFill(altitudes.get(color));
            color = Math.max(0, color - (rand.nextInt(100) >= 90 + 10*Math.atan((double)tiles.size() / 800) ? 1 : 0));
        }
    }
}
