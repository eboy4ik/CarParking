package ru.golyashchuk.carparking.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ru.golyashchuk.carparking.CarParkingApplication;
import ru.golyashchuk.carparking.config.SettingsConfiguration;

import java.io.IOException;

public class MenuController {
    private final Stage primaryStage;

    public MenuController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        initialize();
    }

    private void initialize() {
        // Создаем VBox для кнопок
        VBox menuLayout = new VBox();
        menuLayout.setSpacing(10);
        menuLayout.setPadding(new javafx.geometry.Insets(20, 20, 20, 20));
        menuLayout.setAlignment(javafx.geometry.Pos.CENTER);

        // Создаем кнопки
        Button playButton = new Button("Играть");
        playButton.setFont(new javafx.scene.text.Font("System Bold", 18));
        playButton.setOnAction(e -> {
            try {
                startGame();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        Button createLevelButton = new Button("Cоздать уровень");
        createLevelButton.setFont(new javafx.scene.text.Font("System Bold", 18));
        createLevelButton.setOnAction(e -> startLevelEditor());
        
        Button settingsButton = new Button("Настройки");
        settingsButton.setFont(new javafx.scene.text.Font("System Bold", 18));
        settingsButton.setOnAction(e -> openSettings());

        Button exitButton = new Button("Выход");
        exitButton.setFont(new javafx.scene.text.Font("System Bold", 18));
        exitButton.setOnAction(e -> System.exit(0));

        // Добавляем кнопки в VBox
        menuLayout.getChildren().addAll(playButton, createLevelButton, settingsButton, exitButton);

        // Устанавливаем сцену на Stage
        Scene menuScene = new Scene(menuLayout, SettingsConfiguration.getWindowWidth(), SettingsConfiguration.getWindowHeight());
        primaryStage.setScene(menuScene);
        primaryStage.setTitle("Car Parking");
        primaryStage.show();
    }

    private void startLevelEditor() {
        new LevelEditorController(primaryStage);
    }

    private void startGame() throws IOException {
        new GameController(primaryStage);
    }

    private void openSettings() {
        System.out.println("Открытие настроек...");
    }
}
