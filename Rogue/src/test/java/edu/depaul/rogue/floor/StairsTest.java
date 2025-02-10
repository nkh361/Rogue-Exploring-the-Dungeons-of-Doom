package edu.depaul.rogue.floor;

import edu.depaul.rogue.EventManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;

public class StairsTest {
	private static EventManager eventManager;
	
	@BeforeAll
	static void setup() {
		eventManager = new EventManager();
	}

	@Test
	void TestStairsMethods() {
		Stairs TestStairs1 = new Stairs(1, 1, eventManager);
		
		assertEquals(TileType.FINISH, TestStairs1.getType());
		
		assertTrue(TestStairs1.isWalkable());
		
	}
	@Test
	void TestStairsAsTile() {
		Tile TestStairs2 = new Stairs(1,1, eventManager);
		TestStairs2.setType(TileType.FINISH);
		
		assertTrue(TestStairs2.isWalkable());
		
		assertEquals("F", TestStairs2.toString());
	}
	
}
