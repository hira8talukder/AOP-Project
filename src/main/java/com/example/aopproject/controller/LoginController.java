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
import java.util.Optional;

public class LoginController {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label errorMessageLabel;

    private UserDAO userDAO = new UserDAO();

    @FXML
    private void handleLoginButtonAction() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        try {
            Optional<User> userOptional = userDAO.getUserByUsername(username);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                if (user.getPassword().equals(password)) { // In a real app, use hashed passwords
                    errorMessageLabel.setText("");
                    MainApplication.getInstance().setCurrentUser(user); // Set the current user in MainApplication
                    MainApplication.getInstance().showHomePage(username);
                } else {
                    errorMessageLabel.setText("Invalid username or password.");
                }
            } else {
                errorMessageLabel.setText("Invalid username or password.");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            errorMessageLabel.setText("Database error: " + e.getMessage());
        }
    }

    @FXML
    private void handleRegisterButtonAction() {
        try {
            MainApplication.getInstance().showRegistrationPage();
        } catch (IOException e) {
            e.printStackTrace();
            errorMessageLabel.setText("Error loading registration page.");
        }
    }
}
