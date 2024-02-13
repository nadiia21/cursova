
package com.example.cursova;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;


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
            String Register_name = Registration_name.getText().trim();
            String Register_surname = Registration_surname.getText().trim();
            String Register_login = Registration_login.getText().trim();
            String Register_password = Registration_password.getText().trim();

            if(!Register_name.equals("") && !Register_surname.equals("") && !Register_login.equals("") && !Register_password.equals("")){
                RegistrationNewUser();
            } else {
                shakeField(Registration_name);
                shakeField(Registration_surname);
                shakeField(Registration_login);
                shakeField(Registration_password);
            }
        });

        Return_button.setOnAction(event -> {
            Scene scene = Return_button.getScene();
            Stage stage = (Stage) scene.getWindow();
            stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login_window.fxml"));

            try {
                Parent root = fxmlLoader.load();
                Scene loginScene = new Scene(root);
                stage.setScene(loginScene);
                stage.setTitle("Вхід");
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void shakeField(javafx.scene.Node node){
        Timeline timeline = new Timeline();
        timeline.setCycleCount(2);
        timeline.setAutoReverse(true);

        KeyValue keyValue1 = new KeyValue(node.translateXProperty(), -10);
        KeyValue keyValue2 = new KeyValue(node.translateXProperty(), 10);
        KeyFrame keyFrame1 = new KeyFrame(Duration.seconds(0.05), keyValue1);
        KeyFrame keyFrame2 = new KeyFrame(Duration.seconds(0.1), keyValue2);

        timeline.getKeyFrames().addAll(keyFrame1, keyFrame2);
        timeline.play();
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
