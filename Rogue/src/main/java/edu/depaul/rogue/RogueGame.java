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

public class RogueGame extends Application {

    /**
     * Starts the JavaFX application by initializing the stage and scene. This method
     * creates a grid layout using a GridPane to represent a dungeon-themed floor. It
     * generates and renders the floor layout, and then displays it within the primary
     * application window.
     *
     * TODO: render character in here! character should start at the Start (S) location
     *
     * @param primaryStage      The primary stage for this application, onto which
     *                          the application scene is set.
     */
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

    /**
     * Renders the given floor onto the provided GridPane by creating a visual representation
     * of the floor layout using JavaFX Label components. Each tile on the floor is mapped
     * to the grid.
     *
     *
     * @param floor             The floor object containing the layout to be rendered.
     * @param gridPane          The JavaFX GridPane where the floor layout will be displayed.
     */
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
