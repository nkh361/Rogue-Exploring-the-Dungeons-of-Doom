package edu.depaul.rogue.floor;

import edu.depaul.rogue.EventManager;
import edu.depaul.rogue.RogueGame;
import edu.depaul.rogue.character.CharacterPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StairsTest {
    private EventManager eventManager = new EventManager();
    private RogueGame testGame;
    private DungeonFloor testFloor;
    private CharacterPlayer testPlayer;
    private Stairs testStairs;

    @BeforeEach
    void setup() {
        testGame = new RogueGame(); // Create a test game instance
        testFloor = (DungeonFloor) FloorFactory.createFloor("dungeon", 10, 10, eventManager);
        testPlayer = new CharacterPlayer(testFloor, 1, 1); 
        testStairs = new Stairs(1, 1, eventManager);
    }

    @Test
    void testStairsAttributes() {
        assertEquals(TileType.FINISH, testStairs.getType(), "Stairs should be of type FINISH.");
        assertTrue(testStairs.isWalkable(), "Stairs should be walkable.");
        assertEquals("F", testStairs.toString(), "Stairs should be represented by 'F'.");
    }

//    @Test
//    void testStairsTriggerGeneratesNewFloor() {
//        int oldFloorLevel = testGame.getFloorLevel();
//        testStairs.trigger(testFloor, testPlayer);
//        int newFloorLevel = testGame.getFloorLevel();
//
//        assertNotEquals(oldFloorLevel, newFloorLevel, "Floor level should increase after stepping on stairs.");
//    }
}
