package edu.depaul.rogue.floor;

import edu.depaul.rogue.EventManager;
import edu.depaul.rogue.RogueGame;

public class FloorFactory {
    public static Floor createFloor(String type, int width, int height, EventManager eventManager, RogueGame rogueGameInstance) {
        return switch (type.toLowerCase()){
            case "dungeon" -> new DungeonFloor(width, height, eventManager, rogueGameInstance);

            // FIXME: jerry rigged, maybe make a new exception
            default -> null;
        };
    }
}
