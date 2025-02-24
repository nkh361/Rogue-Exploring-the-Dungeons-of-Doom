package edu.depaul.rogue.combat;

import edu.depaul.rogue.character.CharacterPlayer;
import edu.depaul.rogue.monsters.*;
import edu.depaul.rogue.floor.*;
import edu.depaul.rogue.EventManager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CombatTest {
	private DungeonFloor floor;
	private HashMap<List<Integer>, Monster> presentMonsters;
	private CharacterPlayer player;
	private Monster monster1, monster2;
	
	@BeforeEach
	void setup() {
		presentMonsters = new HashMap<List<Integer>, Monster>();
		floor = new DungeonFloor(5, 5, new EventManager());
		for (int y = 0; y < 5; y++) {
			for (int x = 0; x < 5; x++) {
				floor.getTile(x,y).setType(TileType.FLOOR);
			}
		}
		
		player = new CharacterPlayer(floor, 2, 2);
		monster1 = new Monster(MonsterType.A, 1, 1); // next to player
		Integer[] pos1 = {1, 1};
		monster2 = new Monster(MonsterType.A, 0, 0); // NOT next to player
		Integer[] pos2 = {0, 0};
		presentMonsters.put(Arrays.asList(pos1), monster1);
		presentMonsters.put(Arrays.asList(pos2), monster2);
	}
	
	@Test
	void TestSetters() {
		int[] expectedDmg = {1, 10};
		int[] updatedDmg = {5, 5};
		assertEquals(1, player.getLvl(), "player level not initialized");
		assertEquals(1, player.getAmr(), "player amr not initialized");
		assertEquals(100, player.getHp(), "player hp not initialized");
		assertEquals(10, player.getStr(), "player str not initialized");
		assertArrayEquals(expectedDmg, player.getDmg(), "player dmg not initialized");
		
		player.setLvl(5);
		player.setAmr(5);
		player.setHp(5);
		player.setStr(5);
		player.setDmg(5,5);
		
		assertEquals(5, player.getLvl(), "player level not updated");
		assertEquals(5, player.getAmr(), "player amr not updated");
		assertEquals(5, player.getHp(), "player hp not updated");
		assertEquals(5, player.getStr(), "player str not updated");
		assertArrayEquals(updatedDmg, player.getDmg(), "player dmg not updated");
		
	}
	
	@Test
	void TestGetSurrounding() {
		Monster nearby = monster2;
		for (Integer[] position:player.getSurrounding()) {
			if (presentMonsters.containsKey(Arrays.asList(position))) {
				nearby = presentMonsters.get(Arrays.asList(position));
			}
		}
		
		assertSame(monster1, nearby, "getSurrounding does not work");
	}
	
	@Test
	void TestAtkAndDmgBonus() {
		assertEquals(0, player.atkBonus(), "incorrect atkBonus");
		assertEquals(0, player.dmgBonus(), "incorrect dmgBonus");
	}
	
	@Test
	void TestAttackSuccess() {
		int initialMonsterHp = monster1.getHp();
		player.setLvl(20); // all attacks hit when player level >= 20
		player.attack(monster1);
		assertNotEquals(initialMonsterHp, monster1.getHp(), "attack should hit");
	}
	
	@Test
	void TestAttackMiss() {
		int initialMonsterHp = monster1.getHp();
		monster1.setAmr(0);
		player.setLvl(-1);
		player.attack(monster1);
		assertEquals(initialMonsterHp, monster1.getHp(), "attack should miss");
	}
	
	@Test
	void TestMonsterDies() {
		monster1.setHp(1);
		player.setLvl(20);
		player.attack(monster1);
		assertTrue(monster1.isDead(), "monster isDead is incorrect");
	}
	
	@Test
	void TestPlayerDies() {
		player.setHp(1);
		monster1.setLvl(20);
		monster1.setDmg(1,10);
		monster1.attack(player);
		assertTrue(player.isDead(), "player isDead is incorrect");	
	}
	
	@Test
	void TestAttackUpdatesHp() {
		monster1.setLvl(20);
		monster1.setDmg(10, 1);
		monster1.attack(player);
		assertEquals(90, player.getHp(), "attack does not update hp");
	}
	
	
}
