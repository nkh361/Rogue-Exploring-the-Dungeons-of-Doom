package edu.depaul.rogue.monsters;

import java.util.Random;

public class MonsterFactory {
    private static final Random random = new Random();

    public static Monster createMonster(char typeChar) {
        if (typeChar < 'A' || typeChar > 'E') {
            throw new IllegalArgumentException("Invalid monster type: " + typeChar);
        }
        MonsterType type = MonsterType.valueOf(String.valueOf(typeChar));
        return new Monster(type);
    }

    public static Monster createRandomMonster() {
        MonsterType[] types = MonsterType.values();
        return new Monster(types[random.nextInt(types.length)]);
    }
}
