package edu.depaul.rogue.inventory;

import edu.depaul.rogue.dice.Dice;

public class Weapon extends Item {
    private int diceSides;
    private int numDice;

    public Weapon(String name, int weight, int numDice, int diceSides) {
        super(name, weight);
        this.numDice = numDice;
        this.diceSides = diceSides;
    }

    public int rollDamage() {
        return Dice.roll(numDice, diceSides);
    }

    public int[] getDice() {
        return new int[]{numDice, diceSides};
    }

    @Override
    public void use() {
        System.out.println(getName() + " uses " + numDice + "d" + diceSides + " damage");
    }

}
