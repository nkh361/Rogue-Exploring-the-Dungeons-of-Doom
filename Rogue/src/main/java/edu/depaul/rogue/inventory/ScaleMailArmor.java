package edu.depaul.rogue.inventory;

public class ScaleMailArmor extends Armor {
    public ScaleMailArmor() {
        super("Scale Mail", 1, 6);
    }

    public int getArmorValue() {
        return getArmorClass();
    }
}
