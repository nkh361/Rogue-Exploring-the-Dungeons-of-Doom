package edu.depaul.rogue.floor;

import edu.depaul.rogue.character.CharacterPlayer;
import edu.depaul.rogue.EventManager;
import edu.depaul.rogue.RogueGame;

public class Stairs extends EventTile {
    protected int x, y;
    final protected TileType type;
    protected EventManager eventManager;
    private RogueGame gameInstance;

    public Stairs(int x, int y, EventManager eventManager) {
		super(x, y, eventManager);
		this.x = x;
		this.y = y;
		this.eventManager = eventManager;
		this.type = TileType.FINISH;
	}
	

    @Override
    public boolean isWalkable() {
        return true;
    }

    @Override
    public TileType getType() {
        return TileType.FINISH;
    }

    @Override
    public String toString() {
        return "F";
    }

    /**
     * Defines stair event, which generates a new floor level and places 
     * the player at the new start position.
     */
	@Override
	public void trigger(DungeonFloor dungeonFloor, CharacterPlayer player) {
		eventManager.clearEvents();
		dungeonFloor.generatePassableFloor();
		player.moveToStart();
}


}
