package com.example.cursova;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Controller_for_login {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField Field_login;

    @FXML
    private PasswordField Field_password;

    @FXML
    private Button Login_button;

    @FXML
    private Button Registration_button;

    @FXML
    void initialize() {
        Login_button.setOnAction(event -> {

        });
    }

}
