module com.example.aopproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.base;

    opens com.example.aopproject to javafx.fxml;
    exports com.example.aopproject;
    exports com.example.aopproject.controller;
    opens com.example.aopproject.controller to javafx.fxml;
    opens com.example.aopproject.model to javafx.base;
}