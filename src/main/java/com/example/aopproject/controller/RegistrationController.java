package com.example.aopproject.controller;

import com.example.aopproject.MainApplication;
import com.example.aopproject.model.User;
import com.example.aopproject.model.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class RegistrationController {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField addressField;
    @FXML
    private Label errorMessageLabel;

    private UserDAO userDAO = new UserDAO();

    @FXML
    private void handleRegisterButtonAction() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String name = nameField.getText();
        String email = emailField.getText();
        String address = addressField.getText();

        if (username.isEmpty() || password.isEmpty() || name.isEmpty() || email.isEmpty() || address.isEmpty()) {
            errorMessageLabel.setText("Please fill in all fields.");
            return;
        }

        try {
            if (userDAO.getUserByUsername(username).isPresent()) {
                errorMessageLabel.setText("Username already exists. Please choose another.");
                return;
            }
            User newUser = new User(username, password, name, email, address);
            userDAO.addUser(newUser);
            System.out.println("Registered new user: " + newUser.getUsername());
            errorMessageLabel.setText("Registration successful!");

            // Optionally, clear fields after successful registration
            usernameField.clear();
            passwordField.clear();
            nameField.clear();
            emailField.clear();
            addressField.clear();

            // Navigate to login page after successful registration
            MainApplication.getInstance().showLoginPage();

        } catch (SQLException | IOException e) {
            e.printStackTrace();
            errorMessageLabel.setText("Database error: " + e.getMessage());
        }
    }

    @FXML
    private void handleBackToLoginButtonAction() {
        try {
            MainApplication.getInstance().showLoginPage();
        } catch (IOException e) {
            e.printStackTrace();
            errorMessageLabel.setText("Error loading login page.");
        }
    }
}
