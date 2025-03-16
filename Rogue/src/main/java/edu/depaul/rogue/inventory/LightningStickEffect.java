package edu.depaul.rogue.inventory;

import edu.depaul.rogue.dice.Dice;
import edu.depaul.rogue.monsters.Monster;

public class LightningStickEffect implements StickEffect{
    @Override
    public void apply(Monster monster) {
        if (monster != null) {
            int damage = Dice.roll(1, 8) + 3;
            monster.setHp(monster.getHp() - damage);
        }
    }
}
