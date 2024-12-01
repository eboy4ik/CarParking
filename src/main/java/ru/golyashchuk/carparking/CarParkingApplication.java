package ru.golyashchuk.carparking;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.golyashchuk.carparking.config.ConfigurationManager;
import ru.golyashchuk.carparking.controllers.MenuController;

import java.io.IOException;

public class CarParkingApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        new MenuController(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}