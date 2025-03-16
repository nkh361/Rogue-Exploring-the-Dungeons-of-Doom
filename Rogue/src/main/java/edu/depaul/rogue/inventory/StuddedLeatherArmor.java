package edu.depaul.rogue.inventory;

public class StuddedLeatherArmor extends Armor{
    public StuddedLeatherArmor() {
        super("Studded Leather", 1, 7);
    }

    public int getArmorValue() {
        return getArmorClass();
    }
}
