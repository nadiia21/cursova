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

public class Palace implements Initializable {

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
        engine.load("https://www.google.com.ua/maps/place/%D0%9F%D0%B0%D0%BB%D0%B0%D1%86+%D0%B3%D1%80%D0%B0%D1%84%D1%96%D0%B2+%D0%A8%D0%B5%D0%BD%D0%B1%D0%BE%D1%80%D0%BD%D1%96%D0%B2+(%D0%97%D0%B0%D0%BC%D0%BE%D0%BA+%D0%91%D0%B5%D1%80%D0%B5%D0%B3%D0%B2%D0%B0%D1%80)/@48.5249484,22.8717663,17z/data=!3m1!4b1!4m6!3m5!1s0x4739af8f5ca35b9d:0xe4112030b55d244f!8m2!3d48.5249484!4d22.8743412!16s%2Fg%2F120ykm_8?entry=ttu");

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
