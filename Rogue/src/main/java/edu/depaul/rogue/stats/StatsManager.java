package edu.depaul.rogue.stats;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class StatsManager {
    private HealthStat healthStat;
    private IntegerProperty gold; 

    public StatsManager(int maxHealth) {
        this.healthStat = new HealthStat(maxHealth);
        this.gold = new SimpleIntegerProperty(0);
    }

    public HealthStat getHealthStat() {
        return healthStat;
    }

    public IntegerProperty goldProperty() { 
        return gold;
    }

    public void addGold(int amount) {
        gold.set(gold.get() + amount);
    }

    public int getGold() { 
        return gold.get();
    }
}
