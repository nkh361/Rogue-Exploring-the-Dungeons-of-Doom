package edu.depaul.rogue.character;
import edu.depaul.rogue.floor.DungeonFloor;
import edu.depaul.rogue.floor.Floor;
import edu.depaul.rogue.floor.Tile;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import edu.depaul.rogue.combat.Fighter;
import edu.depaul.rogue.monsters.Monster;

public class CharacterPlayer extends Fighter{
    private int x, y;
    private Floor floor;

    public CharacterPlayer(Floor floor, int startX, int startY) {
        this.floor = floor;
        this.x = startX;
        this.y = startY;
        // FIXME: player's starting stats
        super.setFighter(1, 1, 100, 10, 1, 10);
    }

    /**
     * Moves the player based on input direction.
     * Ensures movement is within walkable areas (FLOOR tiles).
     */
    public void move(int dx, int dy) {
        int newX = x + dx;
        int newY = y + dy;

        if (floor.getTile(newX, newY).isWalkable()) {
            x = newX;
            y = newY;
        }
    }

    public int getX() { return x; }
    public int getY() { return y; }
    
    public void moveToStart() {
    	if (floor instanceof DungeonFloor) {
    		DungeonFloor dungeon = (DungeonFloor) floor;
    		Tile start = dungeon.start;
    		int[] startPos = start.getTilePosition();
    		this.x = startPos[0];
    		this.y = startPos[1];
    	}
    }
    
    public int[][] getSurrounding() {
    	int[][] surrounding = new int[8][2];
    	int i = 0;
    	int playX = this.x;
    	int playY = this.y;
    	for (int thatX = playX - 1; thatX < playX + 2; playX++) {
    		for (int thatY = playY - 1; thatY < playY + 2; playY++) {
    			if (thatX != playX || thatY != playY) {
    				surrounding[i][0] = thatX;
    				surrounding[i][1] = thatY;
    				i++;
    			}
    		}
    	}
    	return surrounding;
    }
    
    @Override
    public void attack(Fighter enemy) {
    	super.attack(enemy);
    	if (!enemy.isDead()) {
    		enemy.attack(this);
    	}
    }
    
    public void attackMonster(HashMap<List<Integer>, Monster> monsters) {
    	for (int[] position:this.getSurrounding()) {
    		if(monsters.containsKey(Arrays.asList(position))) {
    			Monster thisMonster = monsters.get(Arrays.asList(position));
    			if (!thisMonster.getFlag()) {
    				thisMonster.attack(this);
    			}
    			if (!this.isDead()) {
    				this.attack(thisMonster);
    			}
    			if (thisMonster.isDead()) {
    				monsters.remove(Arrays.asList(position));
    			}
    		}
    	}
    }
}