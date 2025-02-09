package edu.depaul.rogue.floor;

public class Stairs extends EventTile {

	final private TileType type = TileType.FINISH;
	
	public Stairs() {
		super();
	}
	
	@Override
	public boolean isWalkable() {
		return true;
	}

	@Override
	public TileType getType() {
		return type;
	}

	/*
	 * public void trigger(DungeonFloor dungeonFloor, Player player) { dungeonFloor.generatePassableFloor(); //
	 * TODO Place player at start tile // TODO Increment level number }
	 */

}
