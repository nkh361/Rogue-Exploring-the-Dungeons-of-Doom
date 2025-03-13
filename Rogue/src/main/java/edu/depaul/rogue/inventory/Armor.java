package edu.depaul.rogue.inventory;

public class Armor {
    private String name;
    private int healthBonus;

    public Armor(String name, int healthBonus) {
        this.name = name;
        this.healthBonus = healthBonus;
    }

    public String getName() {
        return name;
    }

    public int getHealthBonus() {
        return healthBonus;
    }
}