package edu.depaul.rogue.stats;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class StatsDisplay extends VBox {
    private Label healthLabel;
    private Label xpLabel;
    private Label levelLabel;

    public StatsDisplay() {
        StatsManager statsManager = StatsManager.getInstance();

        healthLabel = new Label();
        healthLabel.textProperty().bind(statsManager.getHealthStat().currentHealthProperty().asString());

        xpLabel = new Label();
        xpLabel.textProperty().bind(statsManager.getExperienceManager().currentXPProperty().asString());

        levelLabel = new Label();
        levelLabel.textProperty().bind(statsManager.getExperienceManager().levelProperty().asString());

        this.getChildren().addAll(healthLabel, xpLabel, levelLabel);
    }
}
