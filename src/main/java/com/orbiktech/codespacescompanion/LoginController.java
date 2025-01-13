package com.orbiktech.codespacescompanion;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.Map;

public class LoginController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        GithubAuth githubAuth = new GithubAuth();
        try {
            Map<String, String> response = githubAuth.requestDeviceCode();
            welcomeText.setText("Welcome to JavaFX Application! " + response.get("user_code") + " " + response.get("verification_uri"));
        }
        catch (Throwable e) {
            e.printStackTrace();
            ErrorHandler.showError(
                    "err",
                    "err",
                    e.getMessage()
            );
        }
    }

    public void handleOAuthCallback(String code) {

    }
}