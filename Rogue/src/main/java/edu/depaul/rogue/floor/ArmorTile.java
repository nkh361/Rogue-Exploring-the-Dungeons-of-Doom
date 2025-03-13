package edu.depaul.rogue.floor;

import edu.depaul.rogue.inventory.Armor;

public class ArmorTile extends Tile {
    private Armor armor;

    public ArmorTile(int x, int y, Armor armor) {
        super(TileType.ARMOR); // It's walkable but starts as a floor
        this.armor = armor;
        this.x = x;
        this.y = y;
    }

    public Armor getArmor() {
        return armor;
    }

    @Override
    public String toString() {
        return "^";
    }
}