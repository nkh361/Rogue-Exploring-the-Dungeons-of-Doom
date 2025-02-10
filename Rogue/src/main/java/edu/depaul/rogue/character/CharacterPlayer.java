package edu.depaul.rogue.character;
import edu.depaul.rogue.floor.DungeonFloor;
import edu.depaul.rogue.floor.Floor;
import edu.depaul.rogue.floor.Tile;

public class CharacterPlayer {
    private int x, y;
    private Floor floor;

    public CharacterPlayer(Floor floor, int startX, int startY) {
        this.floor = floor;
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

        if (floor.getTile(newX, newY).isWalkable()) {
            x = newX;
            y = newY;
        }
    }

    public int getX() { return x; }
    public int getY() { return y; }
    
    public void moveToStart() {
    	if (floor instanceof DungeonFloor) {
    		DungeonFloor dungeon = (DungeonFloor) floor;
    		Tile start = dungeon.start;
    		int[] startPos = start.getTilePosition();
    		this.x = startPos[0];
    		this.y = startPos[1];
    	}
    }
}