package edu.depaul.rogue.floor;

public class Tile {
    protected TileType type;
    protected int x, y;
    
    public Tile(TileType type) {
    	this.type = type;
    }
    
    public Tile(int x, int y) {
    	this.x = x;
    	this.y = y;
    }
    
    public TileType getType() {
        return type;
    }

    public void setType(TileType type) {
        this.type = type;
    }
    
    public int[] getTilePosition() {
    	int[] tilePosition = {this.x, this.y};
    	return tilePosition;
    }

	/**
	 * All tiles are walkable, except WALL.
	 */
    public boolean isWalkable() {
        return (type != TileType.WALL);
    }

    /**
     * String representation of the tile
     */
    @Override
    public String toString() {
        return switch (type) {
            case FLOOR -> ".";
            case WALL -> "#";
            case START -> "S";
            case FINISH -> "F";
        };
    }
}
