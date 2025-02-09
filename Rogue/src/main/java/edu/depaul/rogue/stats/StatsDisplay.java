package edu.depaul.rogue.stats;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class StatsDisplay extends Application {
    private ProgressBar healthBar;
    private Label healthLabel;

    @Override
    public void start(Stage primaryStage) throws Exception {
        StatsManager statsManager = new StatsManager(100);  //  max health

        healthBar = new ProgressBar();
        healthBar.progressProperty().bind(statsManager.getHealthStat().currentHealthProperty().divide(100.0));
        healthBar.setPrefWidth(200);

        healthLabel = new Label();
        healthLabel.textProperty().bind(statsManager.getHealthStat().currentHealthProperty().asString("HP: %d/100"));

        BorderPane root = new BorderPane();
        root.setBottom(healthLabel);
        BorderPane.setAlignment(healthLabel, javafx.geometry.Pos.BOTTOM_LEFT);

        Scene scene = new Scene(root, 300, 150);
        primaryStage.setTitle("Character Stats");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}