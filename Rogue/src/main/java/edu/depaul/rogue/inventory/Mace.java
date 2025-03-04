package edu.depaul.rogue.inventory;

public class Mace extends Weapon{
    private int damage;

    public Mace() {
        super("Mace", 1, 2, 4);

        int diceRoll = this.rollDamage();
        this.damage = diceRoll;
    }

    public int getDamage() {
        return damage;
    }
}
