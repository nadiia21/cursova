package com.example.cursova;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class Synevyr implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private WebView webView;

    @FXML
    private Button Btn_return;

    @FXML
    private Button saveButton;

    @FXML
    private TextArea textArea;

    @FXML
    private TextArea textArea2;

    @FXML
    private TextArea textArea3;

    @FXML
    private TextArea textArea4;

    @FXML
    private TextArea textArea5;

    @FXML
    public ImageView imageView1;

    @FXML
    public ImageView imageView2;

    @FXML
    public ImageView imageView3;

    private String savedText = "";
    private boolean isAdminLoggedIn = false;

    public static String imagePath1 = "";
    public static String imagePath2 = "";
    public static String imagePath3 = "";


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        WebEngine engine = webView.getEngine();
        engine.load("https://www.google.com.ua/maps/place/%D0%A1%D0%B8%D0%BD%D0%B5%D0%B2%D0%B8%D1%80/@48.616999,23.6737002,15z/data=!3m1!4b1!4m6!3m5!1s0x4739e3e3f179593f:0xbc869f803c94acef!8m2!3d48.617!4d23.6839999!16s%2Fm%2F027kppy?entry=ttu");

        Btn_return.setOnAction(event -> {
            closeWindowAndOpenHomePage();
        });

        loadSavedText();
        checkAdminStatus();
    }

    public void loadImage(String imagePath, ImageView imageView) {
        if (!imagePath.isEmpty()) {
            Image image = new Image(imagePath);
            imageView.setImage(image);
        }
    }

    private void replaceImage(ImageView imageView) {
        File initialDirectory = new File("src/main/resources/img/");

        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(initialDirectory);
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));

        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            Path selectedFilePath = Paths.get(selectedFile.toURI());
            Path imgFolderPath = Paths.get(initialDirectory.toURI());

            if (selectedFilePath.startsWith(imgFolderPath)) {
                if (selectedFile.getName().endsWith(".png") || selectedFile.getName().endsWith(".jpg") || selectedFile.getName().endsWith(".jpeg")) {
                    Image newImage = new Image(selectedFile.toURI().toString());
                    imageView.setImage(newImage);

                    String imagePath = selectedFile.toURI().toString();
                    if (imageView == imageView1) {
                        imagePath1 = imagePath;
                    } else if (imageView == imageView2) {
                        imagePath2 = imagePath;
                    } else if (imageView == imageView3) {
                        imagePath3 = imagePath;
                    }
                }
            }
        }
    }
    private void closeWindowAndOpenHomePage() {
        Stage stage = (Stage) Btn_return.getScene().getWindow();
        stage.close();

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

    private void loadSavedText() {
        try {
            savedText = "";
            StringBuilder content = new StringBuilder();
            Files.readAllLines(Paths.get("src/main/java/com/example/cursova/Saved_text_Synevyr.txt")).forEach(line -> content.append(line).append("\n"));
            String[] parts = content.toString().split("\n\n\n", 5);
            if (parts.length > 0) {
                textArea.setText(parts[0]);
            }
            if (parts.length > 1) {
                textArea2.setText(parts[1].trim());
            }
            if (parts.length > 2) {
                textArea3.setText(parts[2].trim());
            }
            if (parts.length > 3) {
                textArea4.setText(parts[3].trim());
            }
            if (parts.length > 4) {
                textArea5.setText(parts[4].trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void checkAdminStatus() {
        isAdminLoggedIn = AdminPanel.isAdminLoggedIn();
        if (isAdminLoggedIn) {
            textArea.setEditable(true);
            textArea2.setEditable(true);
            textArea3.setEditable(true);
            textArea4.setEditable(true);
            textArea5.setEditable(true);
            saveButton.setVisible(true);
            imageView1.setOnMouseClicked(event -> replaceImage(imageView1));
            imageView2.setOnMouseClicked(event -> replaceImage(imageView2));
            imageView3.setOnMouseClicked(event -> replaceImage(imageView3));
        } else {
            textArea.setEditable(false);
            textArea2.setEditable(false);
            textArea3.setEditable(false);
            textArea4.setEditable(false);
            textArea5.setEditable(false);
            saveButton.setVisible(false);
            imageView1.setDisable(true);
            imageView2.setDisable(true);
            imageView3.setDisable(true);
        }
    }

    @FXML
    private void saveButtonClicked() {
        try {
            String text1 = textArea.getText();
            String text2 = textArea2.getText();
            String text3 = textArea3.getText();
            String text4 = textArea4.getText();
            String text5 = textArea5.getText();

            String combinedText = text1 + "\n\n\n" + text2 + "\n\n\n" + text3 + "\n\n\n" + text4 + "\n\n\n" + text5;

            FileWriter writer = new FileWriter("src/main/java/com/example/cursova/Saved_text_Synevyr.txt");
            writer.write(combinedText);
            writer.close();

            updateFXMLImagePath(imagePath1, "imageView1");
            updateFXMLImagePath(imagePath2, "imageView2");
            updateFXMLImagePath(imagePath3, "imageView3");

        } catch (IOException e) {
            e.printStackTrace();
        }
        closeWindowAndOpenHomePage();
    }

    private void updateFXMLImagePath(String newImagePath, String imageViewID) {
        try {
            if (!newImagePath.isEmpty()) {
                File fxmlFile = new File("src/main/resources/com/example/cursova/Synevyr.fxml");
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(fxmlFile);
                doc.getDocumentElement().normalize();

                NodeList imageViews = doc.getElementsByTagName("ImageView");
                for (int i = 0; i < imageViews.getLength(); i++) {
                    Node node = imageViews.item(i);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) node;
                        String id = element.getAttribute("fx:id");
                        if (id.equals(imageViewID)) {
                            NodeList imageList = element.getElementsByTagName("Image");
                            Element imageElement = (Element) imageList.item(0);

                            String[] pathParts = newImagePath.split("/");
                            String newImageName = pathParts[pathParts.length - 1];

                            String newPath = "@../../../img/" + newImageName;
                            imageElement.setAttribute("url", newPath);
                            break;
                        }
                    }
                }

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(fxmlFile);
                transformer.transform(source, result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
