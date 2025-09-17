package com.example.aopproject.controller;

import com.example.aopproject.MainApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class HomeController {

    @FXML
    private Label welcomeMessageLabel;

    private String loggedInUsername;

    public void setLoggedInUsername(String username) {
        this.loggedInUsername = username;
        welcomeMessageLabel.setText("Welcome, " + username + "!");
    }

    @FXML
    private void handleMedicineDelivery() {
        try {
            MainApplication.getInstance().showMedicineDeliveryPage();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle error
        }
    }

    @FXML
    private void handlePlumberServices() {
        try {
            MainApplication.getInstance().showPlumberServicePage();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle error
        }
    }

    @FXML
    private void handleElectricDeviceServices() {
        try {
            MainApplication.getInstance().showElectricDeviceServicePage();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle error
        }
    }

    @FXML
    private void handleUserProfile() {
        try {
            MainApplication.getInstance().showUserProfilePage();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle error
        }
    }

    @FXML
    private void handleUserDashboard() {
        try {
            MainApplication.getInstance().showUserDashboardPage();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle error
        }
    }

    @FXML
    private void handleOrderHistory() {
        try {
            MainApplication.getInstance().showOrderHistoryPage();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle error
        }
    }

    @FXML
    private void handleLogout() {
        try {
            MainApplication.getInstance().showLoginPage();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle error, e.g., show an alert
        }
    }
}
