package com.example.aopproject.controller;

import com.example.aopproject.MainApplication;
import com.example.aopproject.model.Order;
import com.example.aopproject.model.OrderDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class OrderHistoryController {

    @FXML
    private TableView<Order> orderTable;
    @FXML
    private TableColumn<Order, String> serviceTypeColumn;
    @FXML
    private TableColumn<Order, String> descriptionColumn;
    @FXML
    private TableColumn<Order, String> statusColumn;
    @FXML
    private TableColumn<Order, LocalDate> orderDateColumn;
    @FXML
    private Label statusMessageLabel;

    private String loggedInUsername;
    private OrderDAO orderDAO = new OrderDAO();

    public void setLoggedInUsername(String username) {
        this.loggedInUsername = username;
        loadOrderHistory();
    }

    @FXML
    public void initialize() {
        serviceTypeColumn.setCellValueFactory(new PropertyValueFactory<>("serviceType"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        orderDateColumn.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
    }

    private void loadOrderHistory() {
        try {
            List<Order> userOrders = orderDAO.getOrdersByUsername(loggedInUsername);
            ObservableList<Order> orders = FXCollections.observableArrayList(userOrders);
            orderTable.setItems(orders);
            if (orders.isEmpty()) {
                statusMessageLabel.setText("No order history found.");
            } else {
                statusMessageLabel.setText("");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            statusMessageLabel.setText("Error loading order history: " + e.getMessage());
        }
    }

    @FXML
    private void handleBackToHomeButtonAction() {
        try {
            MainApplication.getInstance().showHomePage(loggedInUsername);
        } catch (IOException e) {
            e.printStackTrace();
            statusMessageLabel.setText("Error loading home page.");
        }
    }
}
