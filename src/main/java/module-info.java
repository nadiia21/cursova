module com.example.cursova {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;
    requires javafx.web;


    opens com.example.cursova to javafx.fxml;
    exports com.example.cursova;
}