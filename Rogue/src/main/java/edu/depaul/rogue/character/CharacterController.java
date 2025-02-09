package edu.depaul.rogue.character;

import edu.depaul.rogue.floor.DungeonFloor;
import javafx.scene.input.KeyEvent;

public class CharacterController {
    private Player player;
    private DungeonFloor dungeon;

    public CharacterController(DungeonFloor dungeon, Player player) {
        this.dungeon = dungeon;
        this.player = player;
    }

    public void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
            case W -> player.move(0, -1);
            case S -> player.move(0, 1);
            case A -> player.move(-1, 0);
            case D -> player.move(1, 0);
        }
    }
}
