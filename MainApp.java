package com.lasn;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainGui.fxml"));
            AnchorPane root = loader.load();

            // Create the Scene with the loaded FXML file
            Scene scene = new Scene(root);

            // Set the scene on the primary stage
            primaryStage.setScene(scene);
            primaryStage.setTitle("Impresora de Numeros");
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        // Launch the JavaFX application
        launch(args);
    }
}
