package edu.depaul.rogue.inventory;

public class LeatherArmor extends Armor {
    public LeatherArmor() {
        super("Leather", 1, 8);
    }

    public int getArmorValue() {
        return getArmorClass();
    }
}
