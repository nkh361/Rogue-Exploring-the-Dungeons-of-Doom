package edu.depaul.rogue.inventory;

public class BandedMailArmor extends Armor {
    public BandedMailArmor() {
        super("Banded Mail", 1, 4);
    }

    public int getArmorValue() {
        return getArmorClass();
    }
}
