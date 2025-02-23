package edu.depaul.rogue.monsters;

import edu.depaul.rogue.dice.Dice;
import edu.depaul.rogue.combat.Fighter;

public class Monster extends Fighter{
    private final MonsterType type;
    private boolean isDead;

    private int[] lvlsFound;
    private int x, y;

    public Monster(MonsterType type, int x, int y) {
        this.type = type;
        int[] hpt = type.getHpt();
        int[] dmg = type.getDmg();
        int currentHealth = Dice.roll(hpt[0], hpt[1]);
        this.lvlsFound = type.getLvlsFound();
        this.lvlsFound = type.getLvlsFound();
        this.isDead = false;
        this.x = x;
        this.y = y;
        super.setFighter(type.getLvl(), type.getArmr(), currentHealth, 10, dmg[0], dmg[1]);
    }

//    public void takeDamage(int damage) {
//        currentHealth -= damage;
//        if (currentHealth <= 0) {
//            currentHealth = 0;
//            isDead = true;
//        }
//    }
//
//    public boolean isDead() {
//        return isDead;
//    }

    // removed damage stat because it is not a constant
    public String getMonsterInfo() {
        return String.format(
                "%s (Level %d) - HP: %d, Armor: %d, Flag: %d, Lvls: %d-%d, Location: %d, %d",
                type.getName(), type.getLvl(), super.getHp(), type.getArmr(),
                type.getFlag(), lvlsFound[0], lvlsFound[1], x, y
        );
    }

    public int[] getLvlsFound() {
        return lvlsFound;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char getType() {
        return type.getChar();
    }
    
    public boolean getFlag() {
    	return (type.getFlag()== 1);
    }

}
