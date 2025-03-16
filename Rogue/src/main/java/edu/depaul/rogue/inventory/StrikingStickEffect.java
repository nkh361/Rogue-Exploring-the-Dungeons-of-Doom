package edu.depaul.rogue.inventory;

import edu.depaul.rogue.dice.Dice;
import edu.depaul.rogue.monsters.Monster;

public class StrikingStickEffect implements StickEffect {
    @Override
    public void apply(Monster monster) {
        if (monster != null) {
            int damage = Dice.roll(2, 8) + 4;
            if (Math.random() < 0.05) {
                damage = Dice.roll(3, 8) + 4;
            }
            monster.setHp(monster.getHp() - damage);
        }
    }
}
