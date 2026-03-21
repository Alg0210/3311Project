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
        primaryStage.setResizable(true);
        switchScene("LoginPg");
    }

    public static void switchScene(String fxmlName) {
        try {
            Parent root = FXMLLoader.load(
                    MainApp.class.getResource("/fxml/" + fxmlName + ".fxml")
            );
            Scene existing = primaryStage.getScene();
            if (existing != null) {
                // Reuse the existing scene – just swap the root to preserve size
                existing.setRoot(root);
            } else {
                Scene scene = new Scene(root, 1000, 700);
                scene.getStylesheets().add(
                        MainApp.class.getResource("/styles/main.css").toExternalForm()
                );
                primaryStage.setScene(scene);
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
