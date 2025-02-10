package edu.depaul.rogue.stats;

public class StatsManager {
    private HealthStat healthStat;

    public StatsManager(int maxHealth) {
        this.healthStat = new HealthStat(maxHealth);
    }

    public HealthStat getHealthStat() {
        return healthStat;
    }
}