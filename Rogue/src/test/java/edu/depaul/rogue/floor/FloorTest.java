package edu.depaul.rogue.floor;

import org.junit.jupiter.api.Test;
import edu.depaul.rogue.EventManager;
import edu.depaul.rogue.RogueGame;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class FloorTest {
    private RogueGame rogueGameInstance;

    @BeforeEach
    void setup(){
        rogueGameInstance = new RogueGame();
    }
    @Test
    public void testTileCreation() {
        Tile floorTile = new Tile(TileType.FLOOR);
        Tile wallTile = new Tile(TileType.WALL);
        Tile startTile = new Tile(TileType.START);
        Tile finishTile = new Tile(TileType.FINISH);

        assertEquals(TileType.FLOOR, floorTile.getType());
        assertEquals(TileType.WALL, wallTile.getType());
        assertEquals(TileType.START, startTile.getType());
        assertEquals(TileType.FINISH, finishTile.getType());

        // Change the type of the tile and verify it changes
        floorTile.setType(TileType.WALL);
        assertEquals(TileType.WALL, floorTile.getType());
    }

    @Test
    public void testDungeonFloorInitialization() {
        EventManager eventManager = new EventManager();
        DungeonFloor dungeon = new DungeonFloor(10, 10, eventManager,rogueGameInstance);

        dungeon.printFloor();

        assertEquals(10, dungeon.getWidth());
        assertEquals(10, dungeon.getHeight());

        for (int y = 0; y < dungeon.getHeight(); y++) {
            for (int x = 0; x < dungeon.getWidth(); x++) {
                assertNotNull(dungeon.getTile(x, y));
            }
        } 
    }

    //New method to manually find tile positions if findTilePosition() is missing
    private int[] findTile(DungeonFloor floor, TileType type) {
        for (int y = 0; y < floor.getHeight(); y++) {
            for (int x = 0; x < floor.getWidth(); x++) {
                if (floor.getTile(x, y).getType() == type) {
                    return new int[]{x, y};
                }
            }
        }
        return null; // Return null if not found
    }

    @Test
    public void testDungeonFloorStartAndFinish() {
        EventManager eventManager = new EventManager();
        DungeonFloor dungeon = new DungeonFloor(10, 10, eventManager,rogueGameInstance);

        
        int[] start = findTile(dungeon, TileType.START);
        int[] finish = findTile(dungeon, TileType.FINISH);

        assertNotNull(start, "Start tile should be placed on the floor");
        assertNotNull(finish, "Finish tile should be placed on the floor");

        // Check if both the start and finish tiles are walkable
        assertTrue(dungeon.getTile(start[0], start[1]).isWalkable(), "Start tile should be walkable");
        assertTrue(dungeon.getTile(finish[0], finish[1]).isWalkable(), "Finish tile should be walkable");

        // Make sure start and finish are not the same {x, y}
        assertNotEquals(start[0], finish[0], "Start and finish should not share the same X coordinate");
        assertNotEquals(start[1], finish[1], "Start and finish should not share the same Y coordinate");
    }

    @Test
    public void testFloorBounds() {
        EventManager eventManager = new EventManager();
        DungeonFloor dungeon = new DungeonFloor(10, 10, eventManager, rogueGameInstance);

        Tile outOfBounds = dungeon.getTile(-1, -1);
        assertEquals(TileType.WALL, outOfBounds.getType());

        outOfBounds = dungeon.getTile(10, 10);
        assertEquals(TileType.WALL, outOfBounds.getType());
    }

    @Test
    public void testPathPossible() {
        EventManager eventManager = new EventManager();
        DungeonFloor dungeon = new DungeonFloor(10, 10, eventManager, rogueGameInstance);
        dungeon.generatePassableFloor();
        // Check if the floor is passable
        assertTrue(dungeon.isPathPossible(), "Path should be possible between start and finish");
    }
}
