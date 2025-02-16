package edu.depaul.rogue.floor;

import edu.depaul.rogue.EventManager;

public class FloorFactory {
    public static Floor createFloor(String type, int width, int height, EventManager eventManager) {
        return switch (type.toLowerCase()){
            case "dungeon" -> new DungeonFloor(width, height, eventManager);

            // FIXME: jerry rigged, maybe make a new exception
            default -> null;
        };
    }
}
