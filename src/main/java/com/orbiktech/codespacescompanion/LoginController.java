package com.orbiktech.codespacescompanion;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.net.URI;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LoginController {

    @FXML
    private ProgressIndicator generalProgressIndicator;

    @FXML
    private VBox loginWelcomeVBox;

    @FXML
    private VBox deviceCodeVBox;

    @FXML
    private Label deviceCodeLabel;

    @FXML
    private ProgressIndicator verifyProgressIndicator;

    @FXML
    private Hyperlink urlLabel;

    @FXML
    private Button copyButton;

    @FXML
    protected void getDeviceCode() {
        GithubAuth githubAuth = new GithubAuth();

        loginWelcomeVBox.setVisible(false);
        generalProgressIndicator.setVisible(true);

        CompletableFuture.runAsync(() -> {

            try {
                Map<String, String> response = githubAuth.requestDeviceCode();
//                welcomeText.setText("Welcome to JavaFX Application! " + response.get("user_code") + " " + response.get("verification_uri"));

                Platform.runLater(() -> {
                    deviceCodeLabel.setText(response.get("user_code"));
                    urlLabel.setText(response.get("verification_uri"));
                    generalProgressIndicator.setVisible(false);
                    deviceCodeVBox.setVisible(true);
                    verifyProgressIndicator.setVisible(true);
                });
            }
            catch(Throwable e) {
                e.printStackTrace();
                ErrorHandler.showError(
                        "err",
                        "err",
                        e.getMessage()
                );
            }

        });
    }

    @FXML
    protected void openVerifyUrl() {
        try {
            Desktop.getDesktop().browse(new URI(urlLabel.getText()));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void copyCode() {
        String code = deviceCodeLabel.getText();

        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        content.putString(code);
        clipboard.setContent(content);

        copyButton.setText("Copied!");

        new Thread(() -> {
            try {
                Thread.sleep(1000);
            }
            catch(InterruptedException e) {
                e.printStackTrace();
            }

            Platform.runLater(() -> {
                copyButton.setText("Copy");
            });
        }).start();

    }

    public void handleOAuthCallback(String code) {

    }
}