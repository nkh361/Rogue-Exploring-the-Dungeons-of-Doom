package edu.depaul.rogue.floor;

import java.util.ArrayList;
import edu.depaul.rogue.character.Player;

public class EventManager {
	private ArrayList<EventTile> events;
	private DungeonFloor dungeon;
	
	/*
	 * New instance of EventManager created at the start of a new level.
	 * Creates a new ArrayList of the EventTiles in the dungeon floor.
	 */
	public EventManager(DungeonFloor dungeon) {
		this.dungeon = dungeon;
		this.events = new ArrayList<>();
	}
	
	public void registerEvent(EventTile eventTile) {
		events.add(eventTile);
	}
	
	public ArrayList<EventTile> getEvents() {
		return this.events;
	}
	
	/*
	 * Gets  player's position and compares to each eventTile's position in array.
	 * If player is on an eventTile from the array, trigger event and return true.
	 * Else, return false.
	 */
	public boolean triggerEvent(Player player) { 
		int playerX = player.getX();
		int playerY = player.getY();
		int[] playerPos = {playerX, playerY};
		
		for (EventTile tile : events) {
			if (tile.getTilePosition() == playerPos) {
				tile.trigger(dungeon, player);
				return true;
			}
		}
		return false;
	}
	 
}
