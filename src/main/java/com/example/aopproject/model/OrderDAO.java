package com.example.aopproject.model;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    public void addOrder(Order order) throws SQLException {
        String sql = "INSERT INTO orders (username, service_type, description, status, order_date) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, order.getUsername());
            pstmt.setString(2, order.getServiceType());
            pstmt.setString(3, order.getDescription());
            pstmt.setString(4, order.getStatus());
            pstmt.setDate(5, Date.valueOf(order.getOrderDate()));
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    order.setOrderId(generatedKeys.getInt(1));
                }
            }
        }
    }

    public List<Order> getOrdersByUsername(String username) throws SQLException {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT order_id, username, service_type, description, status, order_date FROM orders WHERE username = ? ORDER BY order_date DESC";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    orders.add(new Order(
                            rs.getInt("order_id"),
                            rs.getString("username"),
                            rs.getString("service_type"),
                            rs.getString("description"),
                            rs.getString("status"),
                            rs.getDate("order_date").toLocalDate()
                    ));
                }
            }
        }
        return orders;
    }
}
