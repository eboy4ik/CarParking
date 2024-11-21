package ru.golyashchuk.carparking;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.golyashchuk.carparking.controllers.MenuController;

import java.io.IOException;

public class CarParkingApplication extends Application {
    private static int windowWidth = 1920;
    private static int windowHeight = 1080;

    public static int getWindowWidth() {
        return windowWidth;
    }

    public void setWindowWidth(int windowWidth) {
        CarParkingApplication.windowWidth = windowWidth;
    }

    public static int getWindowHeight() {
        return windowHeight;
    }

    public void setWindowHeight(int windowHeight) {
        CarParkingApplication.windowHeight = windowHeight;
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CarParkingApplication.class.getResource("main-menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), windowWidth, windowHeight);
        stage.setTitle("Car Parking");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}