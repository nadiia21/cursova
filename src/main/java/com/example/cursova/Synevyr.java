package com.example.cursova;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import java.net.URL;
import java.util.ResourceBundle;

public class Synevyr implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private WebView webView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        WebEngine engine = webView.getEngine();
        engine.load("https://www.google.com.ua/maps/place/%D0%A1%D0%B8%D0%BD%D0%B5%D0%B2%D0%B8%D1%80/@48.616999,23.6737002,15z/data=!3m1!4b1!4m6!3m5!1s0x4739e3e3f179593f:0xbc869f803c94acef!8m2!3d48.617!4d23.6839999!16s%2Fm%2F027kppy?entry=ttu");
    }
}

