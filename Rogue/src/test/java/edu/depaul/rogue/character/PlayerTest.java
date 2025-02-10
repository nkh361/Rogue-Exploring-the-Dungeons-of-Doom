package edu.depaul.rogue.character;

import edu.depaul.rogue.floor.DungeonFloor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    private DungeonFloor dungeon;
    private Player player;

    @BeforeEach
    void setup() {
        dungeon = new DungeonFloor(10, 10);
        player = new Player(dungeon, 2, 2);
    }

    @Test
    void testValidMove() {
        player.move(1, 0);
        assertEquals(3, player.getX());
    }

    @Test
    void testInvalidMove() {
        player.move(-10, 0);
        assertEquals(2, player.getX()); // Should not move into walls
    }
}
