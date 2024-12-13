package ru.golyashchuk.carparking.controllers;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ru.golyashchuk.carparking.models.arena.ArenaController;
import ru.golyashchuk.carparking.models.arena.ArenaLevel1;
import ru.golyashchuk.carparking.view.alert.ExitConfirmationAlert;

import java.util.Optional;

public class GameController implements Controller {
    private Stage primaryStage;
    private BorderPane gamePane;
    private ArenaController arenaController;

    public GameController(Stage primaryStage) {
        initializeScene(primaryStage);
    }

    @Override
    public void initializeScene(Stage stage) {
        this.primaryStage = stage;
        gamePane = new BorderPane();

        arenaController = new ArenaController(new ArenaLevel1());

        gamePane.setCenter(arenaController.getArenaView().getView());

        gamePane.setOnKeyPressed(this::onKeyPressed);
        Scene gameScene = new Scene(gamePane, primaryStage.getScene().getWidth(), primaryStage.getScene().getHeight());
        primaryStage.setScene(gameScene);
        primaryStage.show();

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
    }


}
