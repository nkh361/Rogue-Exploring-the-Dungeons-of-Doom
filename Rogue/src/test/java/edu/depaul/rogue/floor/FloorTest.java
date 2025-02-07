package edu.depaul.rogue.floor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class FloorTest {

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

        // change the type of the tile and verify it changes
        floorTile.setType(TileType.WALL);
        assertEquals(TileType.WALL, floorTile.getType());
    }

    @Test
    public void testDungeonFloorInitialization() {
        DungeonFloor dungeon = new DungeonFloor(10, 10);

        dungeon.printFloor();

        // test the bounds created
        assertEquals(10, dungeon.getWidth());
        assertEquals(10, dungeon.getHeight());

        // check if the grid is blank
//        assertnotNull(dungeon.getGrid());

        for (int y = 0; y < dungeon.getHeight(); y++) {
            for (int x = 0; x < dungeon.getWidth(); x++) {
                assertNotNull(dungeon.getTile(x, y));
            }
        }
    }

    @Test
    public void testDungeonFloorStartAndFinish() {
//        DungeonFloor dungeon = new DungeonFloor(10, 10);
//
//        // check start
//        assertEquals(TileType.START, dungeon.getTile(1, dungeon.getHeight() / 2).getType());
//
//        // check finish
//        assertEquals(TileType.FINISH, dungeon.getTile(dungeon.getWidth() - 2, dungeon.getHeight() / 2).getType());
        DungeonFloor dungeon = new DungeonFloor(10, 10);

        // find the start and finish positions
        int[] start = dungeon.findTilePosition(TileType.START);
        int[] finish = dungeon.findTilePosition(TileType.FINISH);

        // make sure the floor has start and finish marks
        assertNotNull(start);
        assertNotNull(finish);

        // check walkable
        assertTrue(dungeon.getTile(start[0], start[1]).isWalkable());
        assertTrue(dungeon.getTile(finish[0], finish[1]).isWalkable());

        // make sure start and finish are not the same {x,y}
        assertNotEquals(start[0], finish[0]);
        assertNotEquals(start[1], finish[1]);
    }

    @Test
    public void testFloorBounds() {
        DungeonFloor dungeon = new DungeonFloor(10, 10);

        Tile outOfBounds = dungeon.getTile(-1, -1);
        assertEquals(TileType.WALL, outOfBounds.getType());

        outOfBounds = dungeon.getTile(10, 10);
        assertEquals(TileType.WALL, outOfBounds.getType());
    }

    @Test
    public void testPathPossible() {
        DungeonFloor dungeon = new DungeonFloor(10, 10);

        // check if the floor is passable
        assertTrue(dungeon.isPathPossible());
    }
}