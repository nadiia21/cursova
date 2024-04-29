package com.example.cursova;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
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
    private Button Btn_return;

    @FXML
    void initialize() {
        img_lake.setOnMouseClicked(event -> openWindow(img_lake, "Synevyr.fxml", "Озеро Синевир"));
        img_deer.setOnMouseClicked(event -> openWindow(img_deer, "Deer.fxml", "Оленяча ферма"));
        img_waterfall.setOnMouseClicked(event -> openWindow(img_waterfall, "Waterfall.fxml", "Водоспад Шипіт"));
        img_palace.setOnMouseClicked(event -> openWindow(img_palace, "Palace.fxml", "Палац Шенборнів"));
        img_daffodils.setOnMouseClicked(event -> openWindow(img_daffodils, "Daffodils.fxml", "Долина нарцисів"));
        img_ostriches.setOnMouseClicked(event -> openWindow(img_ostriches, "Ostriches.fxml", "Страусина ферма"));

        Btn_return.setOnAction(event -> {
            if (AdminPanel.isAdminLoggedIn()) {
                AdminPanel.setAdminLoggedIn(false);
            }
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login_window.fxml"));
                Parent root = fxmlLoader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) Btn_return.getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Вхід");
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void openWindow(ImageView imageView, String fxmlFile, String title) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = fxmlLoader.load();
            Object controller = fxmlLoader.getController();
            if (controller instanceof Deer) {
                Deer deerController = (Deer) controller;
                deerController.loadImage(Deer.imagePath1, deerController.imageView1);
                deerController.loadImage(Deer.imagePath2, deerController.imageView2);
                deerController.loadImage(Deer.imagePath3, deerController.imageView3);
            } else if (controller instanceof Synevyr) {
                Synevyr synevyrController = (Synevyr) controller;
                synevyrController.loadImage(Synevyr.imagePath1, synevyrController.imageView1);
                synevyrController.loadImage(Synevyr.imagePath2, synevyrController.imageView2);
                synevyrController.loadImage(Synevyr.imagePath3, synevyrController.imageView3);
            }else if (controller instanceof Waterfall) {
                Waterfall waterfallController = (Waterfall) controller;
                waterfallController.loadImage(Waterfall.imagePath1, waterfallController.imageView1);
                waterfallController.loadImage(Waterfall.imagePath2, waterfallController.imageView2);
                waterfallController.loadImage(Waterfall.imagePath3, waterfallController.imageView3);
            }else if (controller instanceof Palace) {
                Palace palaceController = (Palace) controller;
                palaceController.loadImage(Palace.imagePath1, palaceController.imageView1);
                palaceController.loadImage(Palace.imagePath2, palaceController.imageView2);
                palaceController.loadImage(Palace.imagePath3, palaceController.imageView3);
            }else if (controller instanceof Ostriches) {
                Ostriches ostrichesController = (Ostriches) controller;
                ostrichesController.loadImage(Ostriches.imagePath1, ostrichesController.imageView1);
                ostrichesController.loadImage(Ostriches.imagePath2, ostrichesController.imageView2);
                ostrichesController.loadImage(Ostriches.imagePath3, ostrichesController.imageView3);
            }else if (controller instanceof Daffodils) {
                Daffodils daffodilsController = (Daffodils) controller;
                daffodilsController.loadImage(Daffodils.imagePath1, daffodilsController.imageView1);
                daffodilsController.loadImage(Daffodils.imagePath2, daffodilsController.imageView2);
                daffodilsController.loadImage(Daffodils.imagePath3, daffodilsController.imageView3);
            }
            Scene scene = imageView.getScene();
            scene.setRoot(root);
            Stage stage = (Stage) scene.getWindow();
            stage.setTitle(title);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
