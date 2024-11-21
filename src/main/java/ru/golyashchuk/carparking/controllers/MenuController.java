package ru.golyashchuk.carparking.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import ru.golyashchuk.carparking.CarParkingApplication;

import java.io.IOException;

public class MenuController {

    @FXML
    private Button exitButton;

    @FXML
    private Button playButton;

    @FXML
    private Button settingsButton;

    @FXML
    void startGame(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CarParkingApplication.class.getResource("game-menu.fxml"));
        Stage currentStage = (Stage) playButton.getScene().getWindow();
        Scene gameScene = new Scene(fxmlLoader.load());
        currentStage.setScene(gameScene);
        gameScene.getRoot().requestFocus();
    }

}
