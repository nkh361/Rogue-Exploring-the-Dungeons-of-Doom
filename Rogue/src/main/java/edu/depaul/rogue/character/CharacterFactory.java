package edu.depaul.rogue.character;

import edu.depaul.rogue.floor.DungeonFloor;
import edu.depaul.rogue.floor.Floor;
import edu.depaul.rogue.floor.TileType;

public class CharacterFactory {
    public static CharacterPlayer createPlayer(Floor floor, int startX, int startY) {
        if (floor.getTile(startX, startY).getType() == TileType.FLOOR || floor.getTile(startX, startY).getType() == TileType.START) {
            return new CharacterPlayer(floor, startX, startY);
        }
        else {
            throw new IllegalArgumentException("Starting position must be on a FLOOR tile.");
        }
    }
}