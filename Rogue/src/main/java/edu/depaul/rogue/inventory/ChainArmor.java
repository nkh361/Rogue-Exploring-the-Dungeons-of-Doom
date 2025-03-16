package edu.depaul.rogue.inventory;

public class ChainArmor extends Armor {
    public ChainArmor() {
        super("Chain Mail", 1, 5);
    }

    public int getArmorValue() {
        return getArmorClass();
    }
}
