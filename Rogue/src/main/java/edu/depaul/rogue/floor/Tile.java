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
        return new int[]{this.x, this.y};
    }

    public void setTilePosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isWalkable() {
        return type != TileType.WALL;
    }

    @Override
    public String toString() {
        return switch (type) {
            case FLOOR -> ".";
            case WALL -> "#";
            case START -> "S";
            case FINISH -> "F";
            case GOLD -> "G"; 
        };
    }
}
