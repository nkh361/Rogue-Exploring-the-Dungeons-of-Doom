package edu.depaul.rogue.character;
import edu.depaul.rogue.floor.DungeonFloor;
import edu.depaul.rogue.floor.TileType;

public class Player {
    private int x, y;
    private DungeonFloor dungeon;

    public Player(DungeonFloor dungeon, int startX, int startY) {
        this.dungeon = dungeon;
        this.x = startX;
        this.y = startY;
    }

    /**
     * Moves the player based on input direction.
     * Ensures movement is within walkable areas (FLOOR tiles).
     */
    public void move(int dx, int dy) {
        int newX = x + dx;
        int newY = y + dy;

        if (dungeon.getTile(newX, newY).getType() == TileType.FLOOR) {
            x = newX;
            y = newY;
        }
    }

    public int getX() { return x; }
    public int getY() { return y; }
}