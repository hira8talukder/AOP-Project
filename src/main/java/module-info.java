module com.example.aopproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.aopproject to javafx.fxml;
    exports com.example.aopproject;
}