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
        img_lake.setOnMouseClicked(event -> {
            Scene scene = img_lake.getScene();
            Stage stage = (Stage) scene.getWindow();
            stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Synevyr.fxml"));

            try {
                Parent root = fxmlLoader.load();
                Scene loginScene = new Scene(root);
                stage.setScene(loginScene);
                stage.setTitle("Озеро Синевир");
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}

