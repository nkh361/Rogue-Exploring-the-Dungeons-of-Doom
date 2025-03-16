package edu.depaul.rogue.inventory;

public class PlateMail extends Armor {
    public PlateMail() {
        super("Plate Mail", 1, 3);
    }

    public int getArmorValue() {
        return getArmorClass();
    }
}
