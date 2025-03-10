package edu.depaul.rogue.hunger;

import edu.depaul.rogue.inventory.*;
import edu.depaul.rogue.character.CharacterPlayer;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.IntegerProperty;

public class Hunger {
	private int hungerLevel;
	private SimpleIntegerProperty value = new SimpleIntegerProperty(this, "hunger");
	private CharacterPlayer player;
	
	public Hunger() {
		this.hungerLevel = 0;
		value.set(0);
		this.player = null;
	}
	
	
	public void setPlayer(CharacterPlayer player) {
		this.player = player;
	}
	
	public void setHunger(int hungerLvl) {
		this.hungerLevel = hungerLvl;
	}
	
	public int getHunger() {
		return this.hungerLevel;
	}
	
	public void takeTurn() {
		hungerLevel++;
		if (hungerLevel > 199) {
			player.setHp(0);
			player.isDead();
		} else if (hungerLevel > 174) {
			value.set(3);
		} else if (hungerLevel > 124) {
			value.set(2);
		} else if (hungerLevel > 74) {
			value.set(1);
		}
	}
	
	public void setHungerValue(int hungerValue) {
		this.value.set(hungerValue);
	}
	
	public int getHungerValue() {
		return this.value.get();
	}
	
	public IntegerProperty hungerProperty() {
		return value;
	}
}
