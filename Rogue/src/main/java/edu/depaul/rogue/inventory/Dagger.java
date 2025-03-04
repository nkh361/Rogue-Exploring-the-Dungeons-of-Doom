package edu.depaul.rogue.inventory;

public class Dagger extends Weapon{
    private int damage;

    public Dagger() {
        super("Dagger", 1, 1, 6);

        int diceRoll = this.rollDamage();
        this.damage = diceRoll;
    }

    public int getDamage() {
        return damage;
    }
}
