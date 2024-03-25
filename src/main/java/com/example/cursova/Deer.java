package com.example.cursova;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Deer implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private WebView webView;

    @FXML
    private Button Btn_return;

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        WebEngine engine = webView.getEngine();
        engine.load("https://www.google.com.ua/maps/place/%D0%9E%D0%BB%D0%B5%D0%BD%D1%8F%D1%87%D0%B0+%D1%84%D0%B5%D1%80%D0%BC%D0%B0/@48.2334516,23.3877536,17z/data=!3m1!4b1!4m6!3m5!1s0x47382adff4db3d15:0x6286a73dfe5f80b3!8m2!3d48.2334516!4d23.3903285!16s%2Fg%2F1jkvsqql6?entry=ttu");

        Btn_return.setOnAction(event -> {
            Scene scene = Btn_return.getScene();
            Stage stage = (Stage) scene.getWindow();
            stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Home_page.fxml"));

            try {
                Parent root = fxmlLoader.load();
                Scene loginScene = new Scene(root);
                stage.setScene(loginScene);
                stage.setTitle("Довідник туриста");
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
