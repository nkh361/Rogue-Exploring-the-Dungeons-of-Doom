package edu.depaul.rogue.floor;

import edu.depaul.rogue.character.CharacterPlayer;
import edu.depaul.rogue.EventManager;

public abstract class EventTile extends Tile {
	int x, y;
	protected TileType type;
	protected EventManager eventManager;
	
	public EventTile(int x, int y, EventManager eventManager) {
		super(x, y);
		this.eventManager = eventManager;
		eventManager.registerEvent(this);
	}
	
	public abstract void trigger(DungeonFloor dungeon, CharacterPlayer player);
	
	public int[] getTilePosition() {
		int[] tilePos = {this.x, this.y};
		return tilePos;
	}



}
