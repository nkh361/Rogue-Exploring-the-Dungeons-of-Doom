package edu.depaul.rogue.character;

import edu.depaul.rogue.floor.DungeonFloor;

public class CharacterFactory {
    public static Player createPlayer(DungeonFloor dungeon) {
        return new Player(dungeon, 2, 2); // Start player at (2,2)
    }
}