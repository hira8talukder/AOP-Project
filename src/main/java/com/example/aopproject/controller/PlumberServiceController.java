package com.example.aopproject.controller;

import com.example.aopproject.MainApplication;
import com.example.aopproject.model.Order;
import com.example.aopproject.model.OrderDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class PlumberServiceController {

    @FXML
    private TextField issueTypeField;
    @FXML
    private TextArea descriptionArea;
    @FXML
    private TextArea serviceAddressArea;
    @FXML
    private Label statusMessageLabel;

    private OrderDAO orderDAO = new OrderDAO();

    @FXML
    private void handleRequestServiceButtonAction() {
        String issueType = issueTypeField.getText();
        String description = descriptionArea.getText();
        String serviceAddress = serviceAddressArea.getText();

        if (issueType.isEmpty() || description.isEmpty() || serviceAddress.isEmpty()) {
            statusMessageLabel.setText("Please fill in all fields.");
            return;
        }

        String username = MainApplication.getInstance().getCurrentUser().getUsername();
        String orderDescription = "Issue: " + issueType + ", Details: " + description + ", Address: " + serviceAddress;
        Order newOrder = new Order(-1, username, "Plumber Service", orderDescription, "Pending", LocalDate.now());

        try {
            orderDAO.addOrder(newOrder);
            System.out.println("Plumber Service Request Placed: " + newOrder.getOrderId());
            statusMessageLabel.setText("Service requested successfully! Order ID: " + newOrder.getOrderId());
            // Clear fields after successful request
            issueTypeField.clear();
            descriptionArea.clear();
            serviceAddressArea.clear();
        } catch (SQLException e) {
            e.printStackTrace();
            statusMessageLabel.setText("Error requesting service: " + e.getMessage());
        }
    }

    @FXML
    private void handleBackToHomeButtonAction() {
        try {
            MainApplication.getInstance().showHomePage(MainApplication.getInstance().getCurrentUser().getUsername());
        } catch (IOException e) {
            e.printStackTrace();
            statusMessageLabel.setText("Error loading home page.");
        }
    }
}
