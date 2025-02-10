package edu.depaul.rogue;

import edu.depaul.rogue.character.Player;
import edu.depaul.rogue.character.CharacterController;
import edu.depaul.rogue.floor.DungeonFloor;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class HelloController {
    @FXML
    private Canvas canvas;  // Canvas for drawing the game

    private DungeonFloor dungeon;
    private Player player;
    private CharacterController characterController;

    public void initialize() {
        dungeon = new DungeonFloor(20, 15); // Generate the dungeon
        player = new Player(dungeon, 2, 2);  // Place player at (2,2)
        characterController = new CharacterController(dungeon, player);
        drawGame(); // Draw the initial state
    }

    @FXML
    public void handleKeyPress(KeyEvent event) {
        characterController.handleKeyPress(event);
        drawGame(); // Update drawing after movement
    }

    private void drawGame() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight()); // Clear screen

        for (int y = 0; y < dungeon.getHeight(); y++) {
            for (int x = 0; x < dungeon.getWidth(); x++) {
                switch (dungeon.getTile(x, y).getType()) {
                    case WALL -> gc.setFill(Color.GRAY);
                    case FLOOR -> gc.setFill(Color.DARKSLATEGRAY);
                    case START -> gc.setFill(Color.GREEN);
                    case FINISH -> gc.setFill(Color.YELLOW);
                }
                gc.fillRect(x * 30, y * 30, 30, 30); 
            }
        }

        // Draw the player as a blue circle
        gc.setFill(Color.BLUE);
        gc.fillOval(player.getX() * 30, player.getY() * 30, 30, 30);
    }
}
