package com.orbiktech.codespacescompanion;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class AppManager {
    private static Stage primaryStage;
    private static double height;
    private static double width;

    public static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
        height = Screen.getPrimary().getBounds().getHeight() * 0.75;
        width = Screen.getPrimary().getBounds().getWidth() * 0.75;
    }

    public static void switchScene(String fxmlFile, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(AppManager.class.getResource(fxmlFile));
            Parent root = loader.load();
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Codespaces Companion - " + title);
            primaryStage.setHeight(height);
            primaryStage.setWidth(width);
            primaryStage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
