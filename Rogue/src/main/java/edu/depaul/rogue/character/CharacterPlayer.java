package edu.depaul.rogue.character;
import edu.depaul.rogue.floor.DungeonFloor;
import edu.depaul.rogue.floor.Floor;
import edu.depaul.rogue.floor.Tile;
import edu.depaul.rogue.floor.TileType;
import edu.depaul.rogue.inventory.Armor;
import edu.depaul.rogue.floor.ArmorTile;
import edu.depaul.rogue.stats.StatsManager;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Optional;

import edu.depaul.rogue.combat.Fighter;
import edu.depaul.rogue.monsters.Monster;

public class CharacterPlayer extends Fighter{
    private int x, y;
    private Floor floor;
	private List<Armor> armors = new ArrayList<>();
	private int currentArmorIndex = -1; // -1 when no armor is equipped
	private StringProperty currentArmorDescription = new SimpleStringProperty("");

    public CharacterPlayer(Floor floor, int startX, int startY) {
    	super();
        this.floor = floor;
        this.x = startX;
        this.y = startY;
        // FIXME: player's starting stats
        super.setFighter(1, 1, 100, 10, 1, 10);
    }

    /**
     * Moves the player based on input direction.
     * Ensures movement is within walkable areas (FLOOR tiles).
     */
	public void move(int dx, int dy) {
		int newX = x + dx;
		int newY = y + dy;
		Tile newTile = floor.getTile(newX, newY);

		if (newTile.isWalkable()) {
			x = newX;
			y = newY;
			if (newTile instanceof ArmorTile) {
				Armor foundArmor = ((ArmorTile) newTile).getArmor();
				armors.add(foundArmor);
				// Cast to DungeonFloor to use setTile
				if (floor instanceof DungeonFloor) {
					DungeonFloor dungeonFloor = (DungeonFloor) floor;
					dungeonFloor.setTile(newX, newY, new Tile(TileType.FLOOR));
				} else {
					// Optionally handle the case where the cast is not possible
					System.out.println("The floor is not a DungeonFloor instance.");
				}
			}
		}
	}

	public void pickUpArmor(Armor armor) {
		if (armor != null) {
			// Add the armor to the player's inventory
			armors.add(armor);
			// Equip the armor
			equipArmor(armor);
		}
	}

	public void addArmor(Armor armor) {
		armors.add(armor);
		if (currentArmorIndex == -1) { // If no armor was equipped before
			currentArmorIndex = 0; // Equip the first armor picked up
		}
		currentArmorDescription.set(armors.get(currentArmorIndex).getName() + " (+ " + armors.get(currentArmorIndex).getHealthBonus() + " HP)");
	}

	private void equipArmor(Armor armor) {
		currentArmorIndex = armors.indexOf(armor);
		currentArmorDescription.set(armor.getName() + " (+ " + armor.getHealthBonus() + " HP)");
		StatsManager.getInstance().getHealthStat().increaseMaxHealth(armor.getHealthBonus());
	}

	public void cycleArmor() {
		if (armors.isEmpty()) return;
		currentArmorIndex = (currentArmorIndex + 1) % armors.size();
		equipArmor(armors.get(currentArmorIndex));
	}

	public Armor getCurrentArmor() {
		if (currentArmorIndex != -1 && !armors.isEmpty()) {
			return armors.get(currentArmorIndex);
		}
		return null;
	}

	public int getX() { return x; }
    public int getY() { return y; }

	public StringProperty currentArmorProperty() {
		return currentArmorDescription;
	}
    
    public void moveToStart() {
    	if (floor instanceof DungeonFloor) {
    		DungeonFloor dungeon = (DungeonFloor) floor;
    		Tile start = dungeon.start;
    		int[] startPos = start.getTilePosition();
    		this.x = startPos[0];
    		this.y = startPos[1];
    	}
    }
    
	/**    
	 * Returns array of positions surrounding player
	 */
    public Integer[][] getSurrounding() {
    	Integer[][] surrounding = new Integer[8][2];
    	int i = 0;
    	int playX = this.x;
    	int playY = this.y;
    	for (int thatX = playX - 1; thatX < playX + 2; thatX++) {
    		for (int thatY = playY - 1; thatY < playY + 2; thatY++) {
    			if (thatX != playX || thatY != playY) {
    				surrounding[i][0] = thatX;
    				surrounding[i][1] = thatY;
    				i++;
    			}
    		}
    	}
    	return surrounding;
    }
    
    
	/**
	 * Overrides Fighter attack method so that enemy attacks after player attacks
	 */
    @Override
    public void attack(Fighter enemy) {
    	super.attack(enemy);
    	if (!enemy.isDead()) {
    		enemy.attack(this);
    	}
    }
    
	/**
	 * Attack nearby monster. If monster is mean, it will attack player first,
	 * otherwise player attacks. If monster is dead, it is removed from HashMap of 
	 * monsters present on the level and is returned as an Optional. If the monster
	 * is not dead, Optional returns empty.
	 * 
	 * FIXME: Mean monster will attack before player with every KeyEvent (2 monster attacks 
	 * for each player turn) and waits for nearby player to try to attack first.
	 */
    public Optional<Monster> attackMonster(HashMap<List<Integer>, Monster> monsters) {
    	for (Integer[] position:this.getSurrounding()) {
    		if(monsters.containsKey(Arrays.asList(position))) {
    			Monster thisMonster = monsters.get(Arrays.asList(position));
    			if (thisMonster.getFlag() == 0) {
    				thisMonster.attack(this);
    			}
    			if (!this.isDead()) {
    				this.attack(thisMonster);
    			}
    			if (thisMonster.isDead()) {
    				return Optional.of(monsters.remove(Arrays.asList(position)));
    			}
    		}
    	}
    	return Optional.empty();
    }
}