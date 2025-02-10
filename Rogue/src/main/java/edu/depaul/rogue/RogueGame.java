package edu.depaul.rogue;

import edu.depaul.rogue.floor.Floor;
import edu.depaul.rogue.floor.FloorFactory;
import edu.depaul.rogue.floor.Tile;
import edu.depaul.rogue.stats.StatsManager;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class RogueGame extends Application {
    private ProgressBar healthBar;
    private Label healthLabel;

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
        // character stats
        StatsManager statsManager = new StatsManager(100);
        healthBar = new ProgressBar();
        healthBar.progressProperty().bind(statsManager.getHealthStat().currentHealthProperty().divide(100));
        healthBar.setPrefWidth(100);

        healthLabel = new Label();
        healthLabel.textProperty().bind(statsManager.getHealthStat().currentHealthProperty().asString("HP: %d/100"));

        BorderPane root = new BorderPane();

        GridPane healthPane = new GridPane();
        healthPane.setHgap(10);
        healthPane.add(healthLabel, 0, 0);
        healthPane.add(healthBar, 1, 0);

        // align healthPane to bottom left
        root.setBottom(healthPane);
        BorderPane.setAlignment(healthPane, Pos.BOTTOM_LEFT);

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

        root.setCenter(gridPane);

        // set up the scene and stage
        Scene scene = new Scene(root, 400, 400);
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
