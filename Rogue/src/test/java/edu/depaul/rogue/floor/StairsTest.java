package edu.depaul.rogue.floor;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StairsTest {

	@Test
	void TestStairsMethods() {
		Stairs TestStairs1 = new Stairs(1, 1);
		
		assertEquals(TileType.FINISH, TestStairs1.getType());
		
		assertTrue(TestStairs1.isWalkable());
		
	}
	@Test
	void TestStairsAsTile() {
		Tile TestStairs2 = new Stairs(1,1);
		TestStairs2.setType(TileType.FINISH);
		
		assertTrue(TestStairs2.isWalkable());
		
		assertEquals("F", TestStairs2.toString());
	}
	
}
