package edu.depaul.rogue.floor;

public class Tile {
    protected TileType type;
    
    public Tile(TileType type) {
    	this.type = type;
    }
    
    public Tile() {
    }
    
    public TileType getType() {
        return type;
    }

    public void setType(TileType type) {
        this.type = type;
    }

    public boolean isWalkable() {
        return (type == TileType.FLOOR || type == TileType.FINISH);
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
