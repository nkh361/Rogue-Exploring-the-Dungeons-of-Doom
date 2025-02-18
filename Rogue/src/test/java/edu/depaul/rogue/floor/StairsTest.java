package edu.depaul.rogue.floor;

import edu.depaul.rogue.EventManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;

public class StairsTest {
	private static EventManager eventManager;
	private static int[] TestPos = new int[2];
	@BeforeAll
	static void setup() {
		eventManager = new EventManager();
		TestPos[0] = 1;
		TestPos[1] = 1;
	}

	@Test
	void TestStairsMethods() {
		Stairs TestStairs1 = new Stairs(1, 1, eventManager);
		
		assertEquals(TileType.FINISH, TestStairs1.getType(), "Stairs are not FINISH tiles.");
		
		assertTrue(TestStairs1.isWalkable(), "Stairs are not walkable.");
		
		assertEquals("F", TestStairs1.toString(), "Incorrect string representation of Stairs");
		
		assertArrayEquals(TestPos, TestStairs1.getTilePosition(), "Incorrect tile position of Stairs.");
		
	}
	@Test
	void TestStairsAsTile() {
		Tile TestStairs2 = new Stairs(1,1, eventManager);
		TestStairs2.setType(TileType.FINISH);
		
		assertTrue(TestStairs2.isWalkable(), "Stairs (as Tile) are not walkable.");
		
		assertEquals("F", TestStairs2.toString(), "Incorrect string representation for Stairs (as Tile).");
		
		assertArrayEquals(TestPos, TestStairs2.getTilePosition(), "Incorrect tile position of Stairs (as Tile)." );
		
		
		
	}
	
}
