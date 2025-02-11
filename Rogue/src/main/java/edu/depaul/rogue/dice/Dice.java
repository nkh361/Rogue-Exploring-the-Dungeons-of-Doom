package edu.depaul.rogue.dice;

import java.util.Random;

public class Dice {
	
	private static final Random random = new Random();
	
	public static int roll(int nDice, int nSides) {
		int total = 0;
		for (int i = 0; i < nDice; i++) {
			total += random.nextInt(nSides);
		}
		return total;
	}
}
