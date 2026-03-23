package org.example.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainApp extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        primaryStage.setTitle("YorkU Lab Reservation");
        primaryStage.setWidth(1000);
        primaryStage.setHeight(750);
        primaryStage.setResizable(true);

        Font.loadFont(MainApp.class.getResourceAsStream("/fonts/Inter-Regular.ttf"), 14);
        Font.loadFont(MainApp.class.getResourceAsStream("/fonts/Inter-Bold.ttf"), 14);
        Font.loadFont(MainApp.class.getResourceAsStream("/fonts/Inter-SemiBold.ttf"), 14);
        switchScene("LoginPg");
    }

    public static void switchScene(String fxmlName) {
        try {
            Parent root = FXMLLoader.load(
                    MainApp.class.getResource("/fxml/" + fxmlName + ".fxml"));
            Scene existing = primaryStage.getScene();
            if (existing != null) {
                existing.setRoot(root);
            } else {
                Scene scene = new Scene(root, 1000, 750);
                scene.getStylesheets().add(
                        MainApp.class.getResource("/styles/main.css").toExternalForm());
                primaryStage.setScene(scene);
                existing = scene;
            }

            String hcStylesheet = MainApp.class.getResource("/styles/headcoordinator.css").toExternalForm();
            if ("HeadCoordinator".equals(fxmlName)) {
                if (!existing.getStylesheets().contains(hcStylesheet)) {
                    existing.getStylesheets().add(hcStylesheet);
                }
            } else {
                existing.getStylesheets().remove(hcStylesheet);
            }

            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
