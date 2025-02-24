package edu.depaul.rogue.stats;

public class StatsManager {
    private static StatsManager instance;
    private HealthStat healthStat;
    private ExperienceManager experienceManager;

    private StatsManager() {
        healthStat = new HealthStat(100);
        experienceManager = new ExperienceManager();
    }

    public static StatsManager getInstance() {
        if (instance == null) {
            instance = new StatsManager();
        }
        return instance;
    }

    public HealthStat getHealthStat() {
        return healthStat;
    }

    public ExperienceManager getExperienceManager() {
        return experienceManager;
    }
}
