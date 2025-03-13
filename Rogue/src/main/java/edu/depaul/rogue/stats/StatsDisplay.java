package edu.depaul.rogue.stats;

import edu.depaul.rogue.character.CharacterPlayer;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class StatsDisplay extends VBox {
    private Label healthLabel;
    private Label xpLabel;
    private Label levelLabel;
    private Label armorLabel;

    public StatsDisplay(CharacterPlayer player) {
        StatsManager statsManager = StatsManager.getInstance();

        healthLabel = new Label();
        healthLabel.textProperty().bind(statsManager.getHealthStat().currentHealthProperty().asString());

        xpLabel = new Label();
        xpLabel.textProperty().bind(statsManager.getExperienceManager().currentXPProperty().asString());

        levelLabel = new Label();
        levelLabel.textProperty().bind(statsManager.getExperienceManager().levelProperty().asString());

        armorLabel = new Label("Armor: None");
        armorLabel.textProperty().bind(player.currentArmorProperty());
        this.getChildren().addAll(healthLabel, xpLabel, levelLabel, armorLabel);
    }
}
