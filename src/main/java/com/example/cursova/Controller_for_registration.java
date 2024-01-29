
package com.example.cursova;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller_for_registration {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Registration_button;

    @FXML
    private TextField Registration_login;

    @FXML
    private TextField Registration_name;

    @FXML
    private PasswordField Registration_password;

    @FXML
    private TextField Registration_surname;

    @FXML
    private Button Return_button;

    @FXML
    void initialize() {

        Registration_button.setOnAction(event ->{
            RegistrationNewUser();
        });

        Return_button.setOnAction(event -> {
            Return_button.getScene().getWindow().hide();

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Login_window.fxml"));

            try{
                fxmlLoader.load();
            } catch (IOException e){
                e.printStackTrace();
            }

            Parent root = fxmlLoader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });
    }

    private void RegistrationNewUser() {
        DatabaseConnection DatabaseHandler = new DatabaseConnection();

        String UserName = Registration_login.getText();
        String FirstName = Registration_name.getText();
        String Surname = Registration_surname.getText();
        String Password = Registration_password.getText();

        User NewUser = new User(UserName, FirstName, Surname, Password);

        try {
            DatabaseHandler.RegisterUser(NewUser);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
