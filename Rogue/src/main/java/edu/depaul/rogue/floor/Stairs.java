package edu.depaul.rogue.floor;

import edu.depaul.rogue.character.CharacterPlayer;
import edu.depaul.rogue.EventManager;

public class Stairs extends EventTile {
	private int x, y;
	final private TileType type;
	private EventManager eventManager;
	
	public Stairs(int x, int y, EventManager eventManager) {
		super(x, y, eventManager);
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
	public void trigger(DungeonFloor dungeonFloor, CharacterPlayer player) {
		eventManager.clearEvents();
		dungeonFloor.generatePassableFloor();
		player.moveToStart();
	}
	  
	 

}
