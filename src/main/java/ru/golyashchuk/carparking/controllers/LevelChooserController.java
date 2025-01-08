package ru.golyashchuk.carparking.controllers;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ru.golyashchuk.carparking.config.SettingsConfiguration;
import ru.golyashchuk.carparking.models.arena.Arena;
import ru.golyashchuk.carparking.utils.Serializator;

import java.io.File;
import java.io.IOException;

public class LevelChooserController implements Controller {
    private Stage primaryStage;

    public LevelChooserController(Stage primaryStage) {
        initializeScene(primaryStage);
    }

    @Override
    public void initializeScene(Stage stage) {
        this.primaryStage = stage;
        // Создаем VBox для кнопок
        VBox menuLayout = new VBox();
        menuLayout.setSpacing(10);
        menuLayout.setPadding(new javafx.geometry.Insets(20, 20, 20, 20));
        menuLayout.setAlignment(javafx.geometry.Pos.CENTER);

        // Создаем кнопки
        Button gameLevelPlayButton = new Button("Прохождение");
        gameLevelPlayButton.setFont(new javafx.scene.text.Font("System Bold", 18));
        gameLevelPlayButton.setOnAction(e -> {
            try {
                startGame();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        Button userLevelPlayButton = new Button("Пользовательский уровень");
        userLevelPlayButton.setFont(new javafx.scene.text.Font("System Bold", 18));
        userLevelPlayButton.setOnAction(e -> userLevelPlay());

        // Добавляем кнопки в VBox
        menuLayout.getChildren().addAll(gameLevelPlayButton, userLevelPlayButton);

        // Устанавливаем сцену на Stage
        Scene menuScene;

        if (primaryStage.getScene() == null) {
            menuScene = new Scene(menuLayout, SettingsConfiguration.getWindowWidth(), SettingsConfiguration.getWindowHeight());
        } else {
            menuScene = new Scene(menuLayout, primaryStage.getScene().getWidth(), primaryStage.getScene().getHeight());
        }
        menuScene.setOnKeyReleased(this::onKeyReleased);
        primaryStage.setScene(menuScene);
        primaryStage.show();
    }

    private void onKeyReleased(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ESCAPE) {
            new MenuController(primaryStage);
        }
    }

    private void userLevelPlay() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите файл для загрузки");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir") + LevelEditorController.ARENA_DIR_PATH));
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("SER files (*.ser)", "*.ser");
        fileChooser.getExtensionFilters().add(extFilter);
        File selectedFile = fileChooser.showOpenDialog(primaryStage);
        if (selectedFile == null) {
            return;
        }
        Arena arena = null;
        try {
            arena = Serializator.deserializeArena(selectedFile);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (arena != null) {
            new GameController(arena, primaryStage);
        }
    }

    private void startGame() throws IOException {
        new GameController(primaryStage);
    }


}
