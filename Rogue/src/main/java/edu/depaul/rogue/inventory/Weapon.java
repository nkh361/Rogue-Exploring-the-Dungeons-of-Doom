package edu.depaul.rogue.inventory;

public class Weapon extends Item {
    private int damage;

    public Weapon(String name, int damage, int weight) {
        super(name, weight);
        this.damage = damage;
    }

    @Override
    public void use() {
        System.out.println(getName() + " uses " + damage + " damage");
    }

    public int getDamage() {
        return damage;
    }

}
