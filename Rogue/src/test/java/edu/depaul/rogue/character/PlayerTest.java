package edu.depaul.rogue.character;

import edu.depaul.rogue.EventManager;
import edu.depaul.rogue.floor.DungeonFloor;
import edu.depaul.rogue.floor.Floor;
import edu.depaul.rogue.floor.FloorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {
    private Floor floor;
    private CharacterPlayer player;
    private EventManager eventManager = new EventManager();

    @BeforeEach
    void setup() {
        floor = FloorFactory.createFloor("dungeon", 10, 10, eventManager);

        player = new CharacterPlayer(floor, 2, 2);
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
