package edu.depaul.rogue;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/depaul/rogue/hello-view.fxml"));
        Pane root = loader.load();
        HelloController controller = loader.getController();

        Scene scene = new Scene(root);
        scene.setOnKeyPressed(controller::handleKeyPress); // Listen for key presses

        stage.setScene(scene);
        stage.setTitle("Rogue - Dungeon Adventure");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
