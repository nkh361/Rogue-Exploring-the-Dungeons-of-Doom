package edu.depaul.rogue.floor;

import java.util.HashSet;
import java.util.Set;

public class EventManager {
	private Set<EventTile> events;
	private DungeonFloor dungeon;
	
	public EventManager(DungeonFloor dungeon) {
		this.dungeon = dungeon;
		this.events = new HashSet<>();
	}
	
	public void registerEvent(EventTile eventTile) {
		events.add(eventTile);
	}
	
	/*
	 * public static void triggerEvent(Tile tile, Player player) { if
	 * (events.contains(tile)) { tile.trigger(player, dungeon); } }
	 */
}
