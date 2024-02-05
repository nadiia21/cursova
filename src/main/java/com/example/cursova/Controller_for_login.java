package com.example.cursova;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.xml.transform.SourceLocator;

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
        Login_button.setOnAction(event ->{
            String textLogin = Field_login.getText().trim();
            String textPassword = Field_password.getText().trim();

        if(!textLogin.equals("") && !textPassword.equals("")){
            UserLogin(textLogin, textPassword);
        }else System.out.println("Дані не введено");
        });

        Registration_button.setOnAction(event -> {
            OpenNewScene("Registration_window.fxml");
        });
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
            System.out.println("Успішно!");
        }
    }

    private void OpenNewScene (String NewWindow){
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
        stage.showAndWait();
    }
}
