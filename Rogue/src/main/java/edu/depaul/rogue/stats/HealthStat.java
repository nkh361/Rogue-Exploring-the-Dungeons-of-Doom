package edu.depaul.rogue.stats;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class HealthStat {
    private IntegerProperty currentHealth;
    private int maxHealth;

    public HealthStat(int maxHealth) {
        this.maxHealth = maxHealth;
        this.currentHealth = new SimpleIntegerProperty(maxHealth);
    }

    public void takeDamage(int damage) {
        currentHealth.set(Math.max(0, currentHealth.get() - damage));
    }

    public void heal(int amount) {
        currentHealth.set(Math.min(maxHealth, currentHealth.get() + amount));
    }

    public IntegerProperty currentHealthProperty() {
        return currentHealth;
    }

    public int getMaxHealth() {
        return maxHealth;
    }
}