package com.example.cursova;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class Controller_for_homePage {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView img_lake;

    @FXML
    private ImageView img_deer;

    @FXML
    private ImageView img_daffodils;

    @FXML
    private ImageView img_ostriches;

    @FXML
    private ImageView img_waterfall;

    @FXML
    private ImageView img_palace;

    @FXML
    void initialize() {
        img_lake.setOnMouseClicked(event -> openScene("Synevyr.fxml", "Озеро Синевир"));
        img_deer.setOnMouseClicked(event -> openScene("Deer.fxml", "Оленяча ферма"));
        img_waterfall.setOnMouseClicked(event -> openScene("Waterfall.fxml", "Водоспад Шипіт"));
    }

    private void openScene(String fxmlFile, String title) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) img_lake.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle(title);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

