package com.example.aopproject;

import com.example.aopproject.model.DatabaseManager;
import com.example.aopproject.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class CheckDatabase {

    public static void main(String[] args) {
        System.out.println("Checking users table...");
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id, username, password, name, email, address FROM users")) {

            if (!rs.isBeforeFirst()) {
                System.out.println("No users found in the database.");
                return;
            }

            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String address = rs.getString("address");
                System.out.println("User ID: " + id + ", Username: " + username + ", Password: " + password + ", Name: " + name + ", Email: " + email + ", Address: " + address);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error accessing database: " + e.getMessage());
        }
    }
}
