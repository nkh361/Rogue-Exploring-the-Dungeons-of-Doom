package edu.depaul.rogue;

import edu.depaul.rogue.character.CharacterController;
import edu.depaul.rogue.character.CharacterFactory;
import edu.depaul.rogue.floor.Floor;
import edu.depaul.rogue.floor.FloorFactory;
import edu.depaul.rogue.floor.Tile;
import edu.depaul.rogue.monsters.Monster;
import edu.depaul.rogue.monsters.MonsterFactory;
import edu.depaul.rogue.stats.StatsManager;
import edu.depaul.rogue.character.CharacterPlayer;
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
import javafx.scene.input.KeyEvent;

public class RogueGame extends Application {
    private ProgressBar healthBar;
    private Label healthLabel;
    private CharacterPlayer player;
    private CharacterController characterController;
    private GridPane gridPane;
    private Floor floor;
    private Label playerLabel;

    /**
     * Starts the JavaFX application by initializing the stage and scene. This method
     * creates a gridPane layout using a GridPane to represent a dungeon-themed floor. It
     * generates and renders the floor layout, and then displays it within the primary
     * application window.
     *
     * After initializing the dungeon floor, it will then identify the starting position S and render a character.
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
        gridPane = new GridPane();
        gridPane.setHgap(2);
        gridPane.setVgap(2);

        // create a floor and generate it
        floor = FloorFactory.createFloor("dungeon", 10, 10);
        if (floor == null) {
            System.out.println("Failed to create floor.");
            return;
        }

        // find the starting tile
        int startX = -1, startY = -1;
        for (int y = 0; y < floor.getHeight(); y++) {
            for (int x = 0; x < floor.getWidth(); x++) {
                Tile tile = floor.getTile(x, y);
                if (tile.toString().equals("S")) {
                    startX = x;
                    startY = y;
                    break;
                }
            }
            if (startX != -1 && startY != -1) break;
        }

        if (startX == -1 || startY == -1) {
            System.out.println("No starting tile 'S' found.");
            return;
        }

        // initialize the player
        player = CharacterFactory.createPlayer(floor, startX, startY);
        
        eventManager.setPlayer(player);

        // render the floor in the GridPane
        renderFloor(floor, gridPane);

        // render the player
        renderPlayer();

        // generate monsters
        generateMonsters();

        // event handler for key presses
        Scene scene = new Scene(root, 400, 400);
        scene.setOnKeyPressed(this::handleKeyPressed);

        root.setCenter(gridPane);

        // set up the scene and stage
        primaryStage.setTitle("Rogue: Exploring the Dungeons of Doom");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private int floorLevel = 1; // Track current floor level

    public int getFloorLevel() {
      return floorLevel;
    }

    /**
     * Clears the existing floor from the GridPane before rendering the new one.
     */
    private void clearFloorRender() {
        gridPane.getChildren().clear(); // Removes all current tiles from GridPane
    }

    /**
     * Renders the given floor onto the provided GridPane by creating a visual representation
     * of the floor layout using JavaFX Label components. Each tile on the floor is mapped
     * to the gridPane.
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

    private void generateMonsters() {
        for (int i = 0; i < 3; i++) {
            int x = (int) (Math.random() * floor.getWidth());
            int y = (int) (Math.random() * floor.getHeight());

            if (floor.getTile(x, y).isWalkable()) {
                // FIXME: adjust to be dynamic based on floors
                Monster monster = MonsterFactory.createMonster('A', x, y);
                renderMonster(monster);
            }
        }
    }

    private void renderMonster(Monster monster) {
        Label monsterLabel = new Label(String.valueOf(monster.getType()));

        monsterLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: green;");
        monsterLabel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        monsterLabel.setAlignment(Pos.CENTER);
        gridPane.add(monsterLabel, monster.getX(), monster.getY());

        GridPane.setHgrow(monsterLabel, Priority.ALWAYS);
        GridPane.setVgrow(monsterLabel, Priority.ALWAYS);
    }

    /**
     * Renders a player onto the GridPane.
     */
    private void renderPlayer() {
        if (playerLabel != null) {
            gridPane.getChildren().remove(playerLabel);
        }

        playerLabel = new Label("@");
        playerLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: red;");
        playerLabel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        playerLabel.setAlignment(Pos.CENTER);
        gridPane.add(playerLabel, player.getX(), player.getY());

        GridPane.setHgrow(playerLabel, Priority.ALWAYS);
        GridPane.setVgrow(playerLabel, Priority.ALWAYS);
    }

    /**
     * Registers a key press on the keyboard. Controls for the game are WASD.
     * After player moves, check for event tile.
     *
     * @param event         Event handler for keyboard.
     */
    private void handleKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case W -> player.move(0, -1);
            case S -> player.move(0, 1);
            case A -> player.move(-1, 0);
            case D -> player.move(1, 0);
        }

        renderFloor(floor, gridPane);
        renderPlayer();
        
        if (eventManager.triggerEvent(floor)) {
            clearFloorRender();  // Ensures cleared floor before rendering a new one
            renderFloor(floor, gridPane);
            renderPlayer();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
