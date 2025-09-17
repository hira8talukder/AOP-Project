package com.example.aopproject.model;

import java.time.LocalDate;

public class Order {
    private int orderId;
    private String username;
    private String serviceType;
    private String description;
    private String status;
    private LocalDate orderDate;

    public Order(int orderId, String username, String serviceType, String description, String status, LocalDate orderDate) {
        this.orderId = orderId;
        this.username = username;
        this.serviceType = serviceType;
        this.description = description;
        this.status = status;
        this.orderDate = orderDate;
    }

    // Getters and Setters
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }
}
