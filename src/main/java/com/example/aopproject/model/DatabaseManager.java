package com.example.aopproject.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:h2:./aop_project_db"; // H2 database file
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    public static void initializeDatabase() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {

            // Create Users table
            String createUsersTableSQL = "CREATE TABLE IF NOT EXISTS users (" +
                                         "id INT AUTO_INCREMENT PRIMARY KEY," +
                                         "username VARCHAR(50) NOT NULL UNIQUE," +
                                         "password VARCHAR(255) NOT NULL," +
                                         "name VARCHAR(100)," +
                                         "email VARCHAR(100)," +
                                         "address VARCHAR(255)" +
                                         ");";
            stmt.execute(createUsersTableSQL);
            System.out.println("Users table created or already exists.");

            // Create Orders table
            String createOrdersTableSQL = "CREATE TABLE IF NOT EXISTS orders (" +
                                         "order_id INT AUTO_INCREMENT PRIMARY KEY," +
                                         "username VARCHAR(50) NOT NULL," +
                                         "service_type VARCHAR(100) NOT NULL," +
                                         "description VARCHAR(500)," +
                                         "status VARCHAR(50)," +
                                         "order_date DATE," +
                                         "FOREIGN KEY (username) REFERENCES users(username)" +
                                         ");";
            stmt.execute(createOrdersTableSQL);
            System.out.println("Orders table created or already exists.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
