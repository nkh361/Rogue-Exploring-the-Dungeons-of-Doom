package edu.depaul.rogue;

import edu.depaul.rogue.floor.Floor;
import edu.depaul.rogue.floor.FloorFactory;
import edu.depaul.rogue.floor.Tile;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class RogueLikeApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // make the GridPane to hold the tiles
        GridPane gridPane = new GridPane();

        // make it look a little prettier
        gridPane.setHgap(2);
        gridPane.setVgap(2);

        // create a floor and generate it
        Floor floor = FloorFactory.createFloor("dungeon", 10, 9);
        if (floor == null) {
            System.out.println("Failed to create floor.");
            return;
        }

        // render the floor in the GridPane
        renderFloor(floor, gridPane);

        // set up the scene and stage
        Scene scene = new Scene(gridPane, 400, 400);
        primaryStage.setTitle("Rogue: Exploring the Dungeons of Doom");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void renderFloor(Floor floor, GridPane gridPane) {
        int width = floor.getWidth();
        int height = floor.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Tile tile = floor.getTile(x, y);
                Label label = new Label(tile.toString());
                label.setFont(Font.font("Monospaced", 20));

                label.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                label.setAlignment(Pos.CENTER);
                gridPane.add(label, x, y);

                GridPane.setHgrow(label, Priority.ALWAYS);
                GridPane.setVgrow(label, Priority.ALWAYS);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
