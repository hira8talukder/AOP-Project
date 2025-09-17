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

public class MedicineDeliveryController {

    @FXML
    private TextField medicineNameField;
    @FXML
    private TextField quantityField;
    @FXML
    private TextArea deliveryAddressArea;
    @FXML
    private Label statusMessageLabel;

    private OrderDAO orderDAO = new OrderDAO();

    @FXML
    private void handlePlaceOrderButtonAction() {
        String medicineName = medicineNameField.getText();
        String quantity = quantityField.getText();
        String deliveryAddress = deliveryAddressArea.getText();

        if (medicineName.isEmpty() || quantity.isEmpty() || deliveryAddress.isEmpty()) {
            statusMessageLabel.setText("Please fill in all fields.");
            return;
        }

        String username = MainApplication.getInstance().getCurrentUser().getUsername();
        String description = "Medicine: " + medicineName + ", Quantity: " + quantity + ", Address: " + deliveryAddress;
        Order newOrder = new Order(-1, username, "Medicine Delivery", description, "Pending", LocalDate.now());

        try {
            orderDAO.addOrder(newOrder);
            System.out.println("Medicine Delivery Order Placed: " + newOrder.getOrderId());
            statusMessageLabel.setText("Order placed successfully! Order ID: " + newOrder.getOrderId());
            // Clear fields after successful order
            medicineNameField.clear();
            quantityField.clear();
            deliveryAddressArea.clear();
        } catch (SQLException e) {
            e.printStackTrace();
            statusMessageLabel.setText("Error placing order: " + e.getMessage());
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
