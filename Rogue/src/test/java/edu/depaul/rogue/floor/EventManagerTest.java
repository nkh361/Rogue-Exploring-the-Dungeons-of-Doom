package edu.depaul.rogue.floor;

import edu.depaul.rogue.character.Player;
import edu.depaul.rogue.floor.DungeonFloor;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EventManagerTest {
	private DungeonFloor dungeon;
	
	@BeforeEach
	void setup() {
		dungeon = new DungeonFloor(10,10);
	}
	
	@Test
	void TestEventsInitialization() {
		ArrayList<EventTile> TestEvents1 = dungeon.eventManager.getEvents();
		assertFalse(TestEvents1.isEmpty(), "Stairs event was not added to EventManager.");
	}
	
	@Test
	void TestTriggerEvent() {
		EventManager TestEventMgr = dungeon.eventManager;
		ArrayList<EventTile> TestEvents2 = TestEventMgr.getEvents();
		Stairs TestTile = (Stairs) TestEvents2.get(0);
		int[] EventPos = TestTile.getTilePosition();
		// Place player on Stairs Tile
		Player TestPlayer = new Player(dungeon, EventPos[0], EventPos[1]);
		
//		assertTrue(TestEventMgr.triggerEvent(TestPlayer), "Event was not triggered.");
		
	}
}
