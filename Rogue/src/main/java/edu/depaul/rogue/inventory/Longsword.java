package edu.depaul.rogue.inventory;

public class Longsword extends Weapon{
    private int damage;

    public Longsword() {
        super("Longsword", 1, 3, 4);

        int diceRoll = this.rollDamage();
        this.damage = diceRoll;
    }

    public int getDamage() {
        return damage;
    }
}
