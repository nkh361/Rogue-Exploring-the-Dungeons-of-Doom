package edu.depaul.rogue.monsters;

import edu.depaul.rogue.dice.Dice;

public class Monster {
    private final MonsterType type;
    private int currentHealth;
    private boolean isDead;
    private int currentDamage;
    private int[] lvlsFound;
    private int x, y;

    public Monster(MonsterType type, int x, int y) {
        this.type = type;
        int[] hpt = type.getHpt();
        int[] dmg = type.getDmg();
        this.lvlsFound = type.getLvlsFound();
        this.currentHealth = Dice.roll(hpt[0], hpt[1]);
        this.currentDamage = Dice.roll(dmg[0], dmg[1]);
        this.lvlsFound = type.getLvlsFound();
        this.isDead = false;
        this.x = x;
        this.y = y;
    }

    public void takeDamage(int damage) {
        currentHealth -= damage;
        if (currentHealth <= 0) {
            currentHealth = 0;
            isDead = true;
        }
    }

    public boolean isDead() {
        return isDead;
    }

    public String getMonsterInfo() {
        return String.format(
                "%s (Level %d) - HP: %d, Armor: %d, Damage: %d, Flag: %d, Lvls: %d-%d, Location: %d, %d",
                type.getName(), type.getLvl(), currentHealth, type.getArmr(),
                currentDamage, type.getFlag(), lvlsFound[0], lvlsFound[1], x, y
        );
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getCurrentDamage() {
        return currentDamage;
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

}
