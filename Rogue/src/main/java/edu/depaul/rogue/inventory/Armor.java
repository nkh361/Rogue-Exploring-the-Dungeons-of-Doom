package edu.depaul.rogue.inventory;

public class Armor extends Item {
    private int armorClass;

    public Armor(String name, int weight, int armorClass) {
        super (name, weight);
        this.armorClass = armorClass;
    }

    @Override
    public void use() {
        System.out.println(getName() + "has been equipped");
    }

    public int getArmorClass() {
        return armorClass;
    }
}
