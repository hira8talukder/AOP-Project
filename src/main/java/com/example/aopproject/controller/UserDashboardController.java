package com.example.aopproject.controller;

import com.example.aopproject.MainApplication;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class UserDashboardController {

    @FXML
    private VBox activityList;

    private String loggedInUsername;

    public void setLoggedInUsername(String username) {
        this.loggedInUsername = username;
        // In a real application, load user-specific activities here
        System.out.println("Loading dashboard for user: " + username);
    }

    @FXML
    private void handleBackToHomeButtonAction() {
        try {
            MainApplication.getInstance().showHomePage(loggedInUsername);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle error
        }
    }
}
