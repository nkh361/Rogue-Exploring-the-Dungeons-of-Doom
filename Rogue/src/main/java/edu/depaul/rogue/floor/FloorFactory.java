package edu.depaul.rogue.floor;

public class FloorFactory {
    public static Floor createFloor(String type, int width, int height) {
        return switch (type.toLowerCase()){
            case "dungeon" -> new DungeonFloor(width, height);

            // FIXME: jerry rigged, maybe make a new exception
            default -> null;
        };
    }
}
