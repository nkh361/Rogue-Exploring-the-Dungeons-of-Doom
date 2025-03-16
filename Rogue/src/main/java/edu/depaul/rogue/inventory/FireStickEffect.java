package edu.depaul.rogue.inventory;

import edu.depaul.rogue.dice.Dice;
import edu.depaul.rogue.monsters.Monster;
import edu.depaul.rogue.monsters.MonsterType;

public class FireStickEffect implements StickEffect {
    @Override
    public void apply(Monster monster) {
        if (monster != null && monster.getType() != MonsterType.D.getChar()) {
            int damage = Dice.roll(6, 6);
            monster.setHp(monster.getHp() - damage);
        } else {
            System.out.println("Dragon is immune to fire stick!");
        }
    }
}
