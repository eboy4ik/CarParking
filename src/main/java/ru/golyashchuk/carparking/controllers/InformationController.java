package ru.golyashchuk.carparking.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;


public class InformationController {
    private final Stage primaryStage;

    public InformationController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/information.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, primaryStage.getScene().getWidth(), primaryStage.getScene().getHeight());
            root.setOnKeyPressed(this::onKeyPressed);
            root.requestFocus();
            this.primaryStage.setScene(scene);
            this.primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ESCAPE) {
            new MenuController(primaryStage);
        }
    }


}
