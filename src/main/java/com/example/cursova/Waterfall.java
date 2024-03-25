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

public class Waterfall implements Initializable {

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
        engine.load("https://www.google.com.ua/maps/place/%D0%92%D0%BE%D0%B4%D0%BE%D1%81%D0%BF%D0%B0%D0%B4+%D0%A8%D0%B8%D0%BF%D1%96%D1%82/@48.656386,23.2663938,17z/data=!3m1!4b1!4m6!3m5!1s0x47399521188b20cd:0x2e629a1a9da3ce7b!8m2!3d48.656386!4d23.2689687!16s%2Fg%2F12hn2ngq_?entry=ttu");

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
