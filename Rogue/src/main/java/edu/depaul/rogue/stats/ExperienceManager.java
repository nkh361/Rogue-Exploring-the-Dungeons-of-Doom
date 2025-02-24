package edu.depaul.rogue.stats;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class ExperienceManager {
    private IntegerProperty currentXP = new SimpleIntegerProperty(0);
    private IntegerProperty xpToNextLevel = new SimpleIntegerProperty(50);
    private IntegerProperty level = new SimpleIntegerProperty(1);

    public void gainXP(int xp) {
        currentXP.set(currentXP.get() + xp);
        while (currentXP.get() >= xpToNextLevel.get()) {
            levelUp();
        }
    }

    private void levelUp() {
        currentXP.set(currentXP.get() - xpToNextLevel.get());
        level.set(level.get() + 1);
        xpToNextLevel.set(xpToNextLevel.get() * 2);
        StatsManager.getInstance().getHealthStat().increaseMaxHealth(50);
    }

    public IntegerProperty currentXPProperty() {
        return currentXP;
    }

    public IntegerProperty xpToNextLevelProperty() {
        return xpToNextLevel;
    }

    public IntegerProperty levelProperty() {
        return level;
    }
}
