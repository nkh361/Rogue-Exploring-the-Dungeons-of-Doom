package edu.depaul.rogue.floor;

import edu.depaul.rogue.character.Player;

public class Stairs extends EventTile {
	private int x, y;
	final private TileType type;
	
	public Stairs(int x, int y) {
		super(x, y);
		this.type = TileType.FINISH;
	}
	
	@Override
	public boolean isWalkable() {
		return true;
	}

	@Override
	public TileType getType() {
		return type;
	}
	

	/**
	 * Defines stair event, which generates a new floor level and places 
	 * the player at the new start position.
	 */
	public void trigger(DungeonFloor dungeonFloor, Player player) {
		dungeonFloor.generatePassableFloor();
	}
	  
	 

}
