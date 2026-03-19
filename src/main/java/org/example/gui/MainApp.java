package org.example.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        primaryStage.setTitle("YorkU Lab Reservation");
        primaryStage.setWidth(1000);
        primaryStage.setHeight(700);
        primaryStage.setResizable(false);
        switchScene("Login");
    }

    public static void switchScene(String fxmlName) {
        try {
            Parent root = FXMLLoader.load(
                    MainApp.class.getResource("/fxml/" + fxmlName + ".fxml")
            );
            Scene scene = new Scene(root);
            scene.getStylesheets().add(
                    MainApp.class.getResource("/styles/main.css").toExternalForm()
            );
            primaryStage.setScene(scene);
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
