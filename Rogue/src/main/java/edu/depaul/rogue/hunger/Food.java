package edu.depaul.rogue.hunger;

import edu.depaul.rogue.inventory.*;

public class Food extends Item {
	private Hunger hunger;
	private Inventory inventory;
	
	public Food(Hunger hunger, Inventory inventory) {
		super("Food", 1);
		this.hunger = hunger;
		this.inventory = inventory;
	}
	
	public void use() {
		hunger.setHunger(0);
		hunger.setHungerValue(0);
		inventory.removeItem(this);
	}
	
}
