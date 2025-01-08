package ru.golyashchuk.carparking.controllers;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ru.golyashchuk.carparking.config.ConfigurationManager;
import ru.golyashchuk.carparking.models.arena.Arena;
import ru.golyashchuk.carparking.models.car.listeners.LevelCompletedListener;
import ru.golyashchuk.carparking.utils.Serializator;
import ru.golyashchuk.carparking.utils.arena.ArenaHandler;
import ru.golyashchuk.carparking.view.alert.ExitConfirmationAlert;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class GameController implements Controller, LevelCompletedListener {
    private Stage primaryStage;
    private BorderPane gamePane;
    private ArenaController arenaController;
    private Arena startArena;
    private int level = -1;

    public GameController(Stage primaryStage) {
        level = 0;
        this.primaryStage = primaryStage;
        loadNextLevel();
    }

    public GameController(Arena arena, Stage primaryStage) {
        arenaController = new ArenaController(ArenaHandler.copyArena(arena));
        startArena = arena;
        initializeScene(primaryStage);
    }

    @Override
    public void initializeScene(Stage stage) {
        this.primaryStage = stage;
        gamePane = new BorderPane();

        gamePane.setCenter(arenaController.getArenaView().getView());

        gamePane.setOnKeyPressed(this::onKeyPressed);
        Scene gameScene = new Scene(gamePane, primaryStage.getScene().getWidth(), primaryStage.getScene().getHeight());
        primaryStage.setScene(gameScene);
        primaryStage.show();

        arenaController.setLevelCompletedListener(this);
        arenaController.getArenaView().getView().requestFocus();
    }

    private void onKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ESCAPE) {
            ExitConfirmationAlert alert = new ExitConfirmationAlert();
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                arenaController.stopGame();
                new MenuController(primaryStage);
            }
        }

        if (event.getCode() == KeyCode.R) {
            restartGame();
        }
    }

    private void restartGame() {
        arenaController.stopGame();
        arenaController = new ArenaController(ArenaHandler.copyArena(startArena));
        initializeScene(primaryStage);
    }

    @Override
    public void completedLevel() {
        arenaController.stopGame();
        Platform.runLater(() -> showLevelEndMenu(primaryStage));

    }

    private void showLevelEndMenu(Stage owner) {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(owner);
        dialog.initStyle(StageStyle.UNDECORATED);
        BorderPane pane = new BorderPane();
        HBox hbox = new HBox(20);
        hbox.setAlignment(Pos.CENTER);
        Button restartButton = new Button();
        ImageView restartImageView = new ImageView(new Image(ConfigurationManager.getProperty("path.program.restart")));
        restartImageView.setFitWidth(80);
        restartImageView.setFitHeight(80);
        restartButton.setGraphic(restartImageView);
        Button nextLevelButton = new Button();
        ImageView nextLevelImageView = new ImageView(new Image(ConfigurationManager.getProperty("path.program.nextlevel")));
        nextLevelImageView.setFitWidth(80);
        nextLevelImageView.setFitHeight(80);
        nextLevelButton.setGraphic(nextLevelImageView);
        nextLevelButton.requestFocus();

        restartButton.setOnAction(event -> {
            dialog.close();
            restartGame();
        });

        nextLevelButton.setOnAction(event -> {
            dialog.close();
            loadNextLevel();
        });

        hbox.getChildren().addAll(restartButton, nextLevelButton);

        Label levelCompletedLabel = new Label("Уровень пройден!");
        levelCompletedLabel.setFont(new javafx.scene.text.Font("System Bold", 18));

        pane.setCenter(levelCompletedLabel);
        pane.setBottom(hbox);

        Scene dialogScene = new Scene(pane, 300, 200);
        dialog.setScene(dialogScene);
        dialog.setTitle("Уровень завершён");
        dialog.showAndWait();
    }

    private void showAllLevelsCompleted() {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(primaryStage);
        dialog.initStyle(StageStyle.UNDECORATED);
        BorderPane pane = new BorderPane();
        HBox hbox = new HBox(20);
        hbox.setAlignment(Pos.CENTER);
        Button restartButton = new Button();
        ImageView restartImageView = new ImageView(new Image(ConfigurationManager.getProperty("path.program.restart")));
        restartImageView.setFitWidth(80);
        restartImageView.setFitHeight(80);
        restartButton.setGraphic(restartImageView);
        Button exitButton = new Button();
        ImageView exit = new ImageView(new Image(ConfigurationManager.getProperty("path.program.exit")));
        exit.setFitWidth(80);
        exit.setFitHeight(80);
        exitButton.setGraphic(exit);
        exitButton.requestFocus();

        restartButton.setOnAction(event -> {
            dialog.close();
            level = 0;
            loadNextLevel();
        });

        exitButton.setOnAction(event -> {
            dialog.close();
            new MenuController(primaryStage);
        });

        hbox.getChildren().addAll(restartButton, exitButton);

        Label levelCompletedLabel = new Label("Все уровни пройдены! Начать сначала?");
        levelCompletedLabel.setFont(new javafx.scene.text.Font("System Bold", 18));

        pane.setCenter(levelCompletedLabel);
        pane.setBottom(hbox);

        Scene dialogScene = new Scene(pane, 400, 200);
        dialog.setScene(dialogScene);
        dialog.setTitle("Уровень завершён");
        dialog.showAndWait();
    }

    private void loadNextLevel() {
        if (level == -1) {
            userLevelPlay();
            return;
        }
        ++level;
        startArena = ArenaHandler.loadLevel(level);
        if (startArena == null) {
            Platform.runLater(() -> showAllLevelsCompleted());
            return;
        }
        arenaController = new ArenaController(ArenaHandler.copyArena(startArena));
        initializeScene(primaryStage);

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
}
