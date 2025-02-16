package edu.depaul.rogue.combat;

import edu.depaul.rogue.dice.Dice;

public abstract class Fighter {

	protected int lvl, amr, hp, str;
	protected int[] atk, dmg;
	
	// getters and setters
	
	public int getLvl() {	return this.lvl;	}
	public int getAmr() {	return this.amr;	}
	public int getHp() {	return this.hp;		}
	public int getStr() {	return this.str;	}
	public int[] getAtk() {	return this.atk;	}
	public int[] getDmg() {	return this.dmg;	}
	
	public void setLvl(int lvl) {	this.lvl = lvl;		}
	public void setAmr(int amr) {	this.amr = amr;		}
	public void setHp(int hp) {		this.hp = hp;		}
	public void setStr(int str) {	this.str = str;		}
	public void setAtk(int nDice, int nSides) {
		atk = new int[2];
		atk[0] = nDice;
		atk[1] = nSides;
	}
	public void setDmg(int nDice, int nSides) {
		dmg = new int[2];
		dmg[0] = nDice;
		dmg[1] = nSides;
	}
	
	public void setFighter(int lvl, int amr, int hp, int str, int atk1, int atk2, int dmg1, int dmg2) {
		setLvl(lvl);
		setAmr(amr);
		setHp(hp);
		setStr(str);
		setAtk(atk1, atk2);
		setDmg(dmg1, dmg2);
	}
	
	public void attack(Fighter enemy) {
		int atkRoll = Dice.roll(atk[0], atk[1]) + this.atkBonus();
		int atkSuccess = 20 - lvl - enemy.getAmr();
		if (atkRoll > atkSuccess) {
			int dmgTotal = Dice.roll(dmg[0], dmg[1]) + this.dmgBonus();
			enemy.setHp(enemy.getHp() - dmgTotal);
			if (enemy.getHp() < 1) {	enemy.dead();	}
		}
	}
	
	public abstract void dead();
	
	protected int atkBonus() {
		int atkB;
		if (str < 8) {	atkB = -3;	}
		else if (str < 17) {	atkB = 0;	}
		else if (str < 19) {	atkB = 1;	}
		else if (str < 21) {	atkB = 2;	}
		else {	atkB = 3;	}
		
		return atkB;
	}
	
	protected int dmgBonus() {
		int dmgB;
		if (str < 8) {	dmgB = -1;	}
		else if (str < 16) {	dmgB = 0;	}
		else if (str < 17) {	dmgB = 1;	}
		else if (str < 18) {	dmgB = 2;	}
		else if (str < 20) {	dmgB = 3;	}
		else if (str < 22) {	dmgB = 4;	}
		else {	dmgB = 5;	}
		
		return dmgB;
	}
	
}
