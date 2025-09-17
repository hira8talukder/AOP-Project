package com.example.aopproject.controller;

import com.example.aopproject.MainApplication;
import com.example.aopproject.model.User;
import com.example.aopproject.model.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class UserProfileController {

    @FXML
    private TextField usernameField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField addressField;
    @FXML
    private Label statusMessageLabel;

    private User currentUser;
    private UserDAO userDAO = new UserDAO();

    public void setUser(User user) {
        this.currentUser = user;
        usernameField.setText(user.getUsername());
        nameField.setText(user.getName());
        emailField.setText(user.getEmail());
        addressField.setText(user.getAddress());
    }

    @FXML
    private void handleSaveChangesButtonAction() {
        if (currentUser != null) {
            currentUser.setName(nameField.getText());
            currentUser.setEmail(emailField.getText());
            currentUser.setAddress(addressField.getText());
            try {
                userDAO.updateUser(currentUser);
                statusMessageLabel.setText("Profile updated successfully!");
                System.out.println("User " + currentUser.getUsername() + " profile updated.");
            } catch (SQLException e) {
                e.printStackTrace();
                statusMessageLabel.setText("Error updating profile: " + e.getMessage());
            }
        } else {
            statusMessageLabel.setText("Error: No user loaded.");
        }
    }

    @FXML
    private void handleBackToHomeButtonAction() {
        try {
            MainApplication.getInstance().showHomePage(currentUser.getUsername());
        } catch (IOException e) {
            e.printStackTrace();
            statusMessageLabel.setText("Error loading home page.");
        }
    }
}
