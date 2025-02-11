package edu.depaul.rogue;

import java.util.ArrayList;
import java.util.Arrays;
import edu.depaul.rogue.character.CharacterPlayer;
import edu.depaul.rogue.floor.*;

public class EventManager {
	private ArrayList<EventTile> events;
	private CharacterPlayer player;
	

	public EventManager() {
		this.events = new ArrayList<EventTile>();
	}
	
	public void setPlayer(CharacterPlayer player) {
		this.player = player;
	}
	
	public void registerEvent(EventTile eventTile) {
		events.add(eventTile);
	}
	
	public ArrayList<EventTile> getEvents() {
		return this.events;
	}
	
	public ArrayList<EventTile> clearEvents() {
		events.clear();
		return events;
	}
	
	/*
	 * Gets  player's position and compares to each eventTile's position in array.
	 * If player is on an eventTile from the array, trigger event and return true.
	 * Else, return false.
	 */
	public boolean triggerEvent(Floor floor) { 
		int playerX = player.getX();
		int playerY = player.getY();
		int[] playerPos = {playerX, playerY};
		
		if (floor instanceof DungeonFloor) {
			DungeonFloor dungeon = (DungeonFloor) floor;
			for (EventTile tile : events) {
				int[] tilePos = tile.getTilePosition();
				if (Arrays.equals(playerPos, tilePos)) {
					tile.trigger(dungeon, player);
					return true;
				}
			}
		}
		return false;
	}
	 
}
