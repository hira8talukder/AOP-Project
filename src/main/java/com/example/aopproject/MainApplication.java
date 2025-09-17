package com.example.aopproject;

import com.example.aopproject.controller.HomeController;
import com.example.aopproject.controller.OrderHistoryController;
import com.example.aopproject.controller.UserDashboardController;
import com.example.aopproject.controller.UserProfileController;
import com.example.aopproject.model.DatabaseManager;
import com.example.aopproject.model.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    private static MainApplication instance;
    private Stage primaryStage;
    private User currentUser;

    public MainApplication() {
        instance = this;
    }

    public static MainApplication getInstance() {
        return instance;
    }

    @Override
    public void start(Stage stage) throws IOException {
        this.primaryStage = stage;
        primaryStage.setTitle("AOP Project");
        DatabaseManager.initializeDatabase(); // Initialize database
        showLoginPage();
    }

    public void showLoginPage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showRegistrationPage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("registration-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    public void showHomePage(String username) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("home-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
        HomeController homeController = fxmlLoader.getController();
        homeController.setLoggedInUsername(username);
    }

    public void showUserProfilePage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("user-profile-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
        UserProfileController userProfileController = fxmlLoader.getController();
        userProfileController.setUser(currentUser);
    }

    public void showUserDashboardPage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("user-dashboard-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
        UserDashboardController userDashboardController = fxmlLoader.getController();
        userDashboardController.setLoggedInUsername(currentUser.getUsername());
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void showMedicineDeliveryPage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("medicine-delivery-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showPlumberServicePage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("plumber-service-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showElectricDeviceServicePage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("electric-device-service-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showOrderHistoryPage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("order-history-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
        OrderHistoryController orderHistoryController = fxmlLoader.getController();
        orderHistoryController.setLoggedInUsername(currentUser.getUsername());
    }

    public static void main(String[] args) {
        launch();
    }
}
