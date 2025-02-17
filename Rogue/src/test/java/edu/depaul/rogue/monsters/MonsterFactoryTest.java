package edu.depaul.rogue.monsters;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MonsterFactoryTest {

    @Test
    public void testCreateMonster() {
        Monster aquator = MonsterFactory.createMonster('A');
        assertNotNull(aquator);
        assertEquals(aquator.getMonsterInfo().contains("Flag: 0"), true);
        assertEquals(aquator.getMonsterInfo().contains("Lvls: 1-6"), true);
    }

    @Test
    public void testCreateRandomMonster() {
        Monster randomMonster = MonsterFactory.createRandomMonster();
        assertNotNull(randomMonster);
    }

    @Test
    public void testInvalidMonsterType() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            MonsterFactory.createMonster('1');
        });
        assertTrue(exception.getMessage().contains("Invalid monster type"));
    }

    @Test
    public void testMonsterTakesDamage() {
        Monster bat = MonsterFactory.createMonster('B');
        int initialHP = bat.getCurrentHealth();
        bat.takeDamage(1);
        assertEquals(bat.getCurrentHealth(), initialHP - 1);
    }

    @Test public void testMonsterDeath() {
        Monster centaur = MonsterFactory.createMonster('C');
        int initialHP = centaur.getCurrentHealth();
        centaur.takeDamage(initialHP);
        assertEquals(true, centaur.isDead());
    }
}