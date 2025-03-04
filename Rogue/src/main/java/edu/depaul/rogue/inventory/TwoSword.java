package edu.depaul.rogue.inventory;

public class TwoSword extends Weapon{
    private int damage;

    public TwoSword() {
        super("Two-Handed Sword", 1, 1, 6);

        int diceRoll = this.rollDamage();
        this.damage = diceRoll;
    }

    public int getDamage() {
        return damage;
    }
}
