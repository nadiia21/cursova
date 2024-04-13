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

public class Daffodils implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Btn_return;

    @FXML
    private WebView webView;

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        WebEngine engine = webView.getEngine();
        engine.load("https://www.google.com.ua/maps/place/%D0%94%D0%BE%D0%BB%D0%B8%D0%BD%D0%B0+%D0%9D%D0%B0%D1%80%D1%86%D0%B8%D1%81%D1%96%D0%B2/@48.1831414,23.3469652,14.76z/data=!4m6!3m5!1s0x47382a2e8e858679:0xb534122cd2d9c98d!8m2!3d48.1838889!4d23.3566667!16s%2Fg%2F1ymt5q46x?entry=ttu");

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
