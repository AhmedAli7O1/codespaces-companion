package com.orbiktech.codespacescompanion;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class CodespacesCompanion extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        AppManager.setPrimaryStage(stage);
        AppManager.switchScene("login-view.fxml", "Login", 600, 650);
    }

    public static void main(String[] args) {
        launch();
    }
}