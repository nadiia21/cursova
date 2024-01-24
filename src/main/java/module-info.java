module com.example.cursova {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.cursova to javafx.fxml;
    exports com.example.cursova;
}