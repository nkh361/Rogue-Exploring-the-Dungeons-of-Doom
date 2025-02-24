package edu.depaul.rogue.stats;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class HealthStat {
    private IntegerProperty currentHealth;
    private IntegerProperty maxHealth;

    public HealthStat(int initialHealth) {
        this.maxHealth = new SimpleIntegerProperty(initialHealth);
        this.currentHealth = new SimpleIntegerProperty(initialHealth);
    }

    public void takeDamage(int damage) {
        currentHealth.set(Math.max(currentHealth.get() - damage, 0));
    }

    public void heal(int amount) {
        currentHealth.set(Math.min(currentHealth.get() + amount, maxHealth.get()));
    }

    public void increaseMaxHealth(int amount) {
        maxHealth.set(maxHealth.get() + amount);
        heal(amount);  // Optionally heal the player upon increasing max health
    }

    public IntegerProperty currentHealthProperty() {
        return currentHealth;
    }

    public IntegerProperty maxHealthProperty() {
        return maxHealth;
    }
}
