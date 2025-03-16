package edu.depaul.rogue.inventory;

import edu.depaul.rogue.monsters.Monster;

public class Stick extends Weapon {
    private int charges;
    private StickEffect effect;

    public Stick(String name, int weight, int numDice, int diceSides, int charges, StickEffect effect){
        super(name, weight, numDice, diceSides);
        this.charges = charges;
        this.effect = effect;
    }

    public boolean useStick(Monster monster){
        if (charges > 0) {
            effect.apply(monster);
            charges--;
            System.out.println(getName() + " used. " + charges + " remaining.");
            return true;
        } else {
            System.out.println(getName() + " out of charges.");
            return false;
        }
    }

    public int getCharges(){
        return charges;
    }

    public boolean hasCharges(){
        return charges > 0;
    }

    @Override
    public void use() {
        System.out.println(getName() + " has" + charges + " charges left");
    }
}
