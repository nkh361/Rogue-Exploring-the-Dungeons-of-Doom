package edu.depaul.rogue.floor;

import edu.depaul.rogue.inventory.Armor;

public class Tile {
    protected TileType type;
    protected int x, y;
    private Armor armor;
    
    public Tile(TileType type) {
    	this.type = type;
    }
    
    public Tile(int x, int y) {
    	this.x = x;
    	this.y = y;
        this.type = type;
    }
    
    public TileType getType() {
        return type;
    }

    public void setType(TileType type) {
        this.type = type;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }
    
    public int[] getTilePosition() {
    	int[] tilePosition = {this.x, this.y};
    	return tilePosition;
    }
    
    public void setTilePosition(int x, int y) {
    	this.x = x;
    	this.y = y;
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
            case LEATHER_ARMOR, IRON_ARMOR, DIAMOND_ARMOR -> "^";
            default -> "?";  // Fallback for any unhandled TileType
        };
    }
}
