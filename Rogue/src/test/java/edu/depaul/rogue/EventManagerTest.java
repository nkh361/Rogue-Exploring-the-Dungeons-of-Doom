package edu.depaul.rogue;

import edu.depaul.rogue.character.CharacterPlayer;
import edu.depaul.rogue.floor.*;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EventManagerTest {
	private DungeonFloor dungeon;
	private EventManager eventManager;
	private CharacterPlayer player;
	
	@BeforeEach
	void setup() {
		eventManager = new EventManager();
		Floor floor = FloorFactory.createFloor("dungeon", 10, 10, eventManager);
		dungeon = (DungeonFloor) floor;
		player = new CharacterPlayer(dungeon, 1, 1);
		player.moveToStart();
		eventManager.setPlayer(player);
	}
	
	@Test
	void TestEventsInitialization() {
		ArrayList<EventTile> TestEvents1 = eventManager.getEvents();
		assertFalse(TestEvents1.isEmpty(), "Stairs event was not added to EventManager.");
	}
	
	@Test
	void TestTriggerEvent() {
		ArrayList<EventTile> TestEvents2 = eventManager.getEvents();
		Stairs TestTile = (Stairs) TestEvents2.get(0);
		int[] EventPos = TestTile.getTilePosition();
		// Place player on Stairs Tile
		CharacterPlayer TestPlayer = new CharacterPlayer(dungeon, EventPos[0], EventPos[1]);
		eventManager.setPlayer(TestPlayer);

		assertEquals(EventPos[0], TestPlayer.getX(), "Player is not on Stairs.");
		assertEquals(EventPos[1], TestPlayer.getY(), "Player is not on Stairs.");
		
		assertTrue(eventManager.triggerEvent(dungeon), "Event was not triggered.");
		
	}
}
