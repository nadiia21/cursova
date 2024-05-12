package com.example.cursova;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
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
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.scene.control.Alert;
import com.example.cursova.AdminPanel;

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
    private Button Admin_panel_button;

    @FXML
    void initialize() {
        Login_button.setOnAction(event ->{
            String textLogin = Field_login.getText().trim();
            String textPassword = Field_password.getText().trim();

            if(!textLogin.equals("") && !textPassword.equals("")){
                UserLogin(textLogin, textPassword);
            }else shakeField(Field_login);
            shakeField(Field_password);
        });

        Registration_button.setOnAction(event -> {
            OpenNewScene("Registration_window.fxml", "Реєстрація");
        });

        Admin_panel_button.setOnAction(event -> {
            OpenNewScene("AdminPanel.fxml", "Адмінська панель");
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

    private void UserLogin(String textLogin, String textPassword) {
        DatabaseConnection dbConnection = new DatabaseConnection();
        User NewUser = new User();
        NewUser.setUserName(textLogin);
        NewUser.setPassword(textPassword);
        ResultSet resSet = dbConnection.GetUser(NewUser);

        int Counter = 0;
        try {
            while (resSet.next()){
                Counter++;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        if (Counter >= 1) {
            OpenNewScene("Home_page.fxml", "Головна сторінка");
        } else {
            showAlert("Помилка", "Ви не зареєстровані", "Будь ласка, перевірте ваші логін та пароль.");
        }
    }

    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void OpenNewScene(String NewWindow, String title){
        Registration_button.getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(NewWindow));

        try{
            fxmlLoader.load();
        } catch (IOException e){
            e.printStackTrace();
        }

        Parent root = fxmlLoader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle(title);
        stage.showAndWait();
    }
}