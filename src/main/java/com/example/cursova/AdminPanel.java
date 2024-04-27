package com.example.cursova;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminPanel {

    private static boolean isAdminLoggedIn = false;

    @FXML
    private TextField adminUsernameField;

    @FXML
    private PasswordField adminPasswordField;

    @FXML
    private Button adminLoginButton;

    @FXML
    void initialize() {
        adminLoginButton.setOnAction(event -> {
            String username = adminUsernameField.getText().trim();
            String password = adminPasswordField.getText().trim();

            try {
                if (validateAdmin(username, password)) {
                    isAdminLoggedIn = true;
                    openHomePage();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Login Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Ви не адмін!");
                    alert.showAndWait();
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    private boolean validateAdmin(String username, String password) throws SQLException, ClassNotFoundException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        String query = "SELECT * FROM admins WHERE username = ? AND password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet.next();
    }

    private void openHomePage() {
        adminLoginButton.getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Home_page.fxml"));
        try {
            Parent root = fxmlLoader.load();
            Scene loginScene = new Scene(root);
            Stage loginStage = new Stage();
            loginStage.setScene(loginScene);
            loginStage.setTitle("Головна сторінка");
            loginStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isAdminLoggedIn() {
        return isAdminLoggedIn;
    }

    public static void setAdminLoggedIn(boolean loggedIn) {
        isAdminLoggedIn = loggedIn;
    }
}