package edu.depaul.rogue.floor;

import edu.depaul.rogue.character.Player;

public abstract class EventTile extends Tile {
	int x, y;
	protected TileType type;
	
	public EventTile(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public abstract void trigger(DungeonFloor dungeon, Player player);
	
	public int[] getTilePosition() {
		int[] tilePos = {this.x, this.y};
		return tilePos;
	}



}
