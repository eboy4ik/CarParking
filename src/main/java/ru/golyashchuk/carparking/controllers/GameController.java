package ru.golyashchuk.carparking.controllers;

import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import ru.golyashchuk.carparking.config.SettingsConfiguration;
import ru.golyashchuk.carparking.models.arena.ArenaController;
import ru.golyashchuk.carparking.models.arena.ArenaLevel1;
import ru.golyashchuk.carparking.models.car.Direction;
import ru.golyashchuk.carparking.view.alert.ExitConfirmationAlert;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class GameController implements Controller {
    private Stage primaryStage;
    private ArenaController arenaController;
    private final Set<KeyCode> pressedKeys = new HashSet<>();
    private BorderPane gamePane;

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
        gamePane.setOnKeyReleased(this::onKeyReleased);
        arenaController.getArenaView().getView().setOnMouseClicked(this::onMouseClicked);

        final LongProperty lastUpdateTime = new SimpleLongProperty();
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {

                double elapsedSeconds = (now - lastUpdateTime.get()) / 1_000_000_000.0;
                if (arenaController.checkWin()) {
                    System.out.println("WIIIN");
                }
                updateCarMovement(elapsedSeconds);
                arenaController.getArenaView().renderAll();
                lastUpdateTime.set(now);
            }
        };
        timer.start();

        Scene gameScene = new Scene(gamePane, primaryStage.getScene().getWidth(), primaryStage.getScene().getHeight());
        primaryStage.setScene(gameScene);
        primaryStage.show();

        gamePane.requestFocus();
    }

    private void onMouseClicked(MouseEvent mouseEvent) {
        arenaController.focusCar(mouseEvent.getX(), mouseEvent.getY());
    }

    private void onKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ESCAPE) {
            pressedKeys.clear();
            ExitConfirmationAlert alert = new ExitConfirmationAlert();

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                new MenuController(primaryStage);
            }
        }
        pressedKeys.add(event.getCode());
    }

    private void onKeyReleased(KeyEvent event) {
        pressedKeys.remove(event.getCode());
    }

    private void updateCarMovement(double time) {
        HashSet<Direction> directions = new HashSet<>();
        if (pressedKeys.contains(KeyCode.W)) {
            directions.add(Direction.FORWARD);
        }
        if (pressedKeys.contains(KeyCode.S)) {
            directions.add(Direction.BACK);
        }
        if (pressedKeys.contains(KeyCode.A)) {
            directions.add(Direction.LEFT);
        }
        if (pressedKeys.contains(KeyCode.D)) {
            directions.add(Direction.RIGHT);
        }
        if (pressedKeys.contains(KeyCode.SPACE)) {
            directions.add(Direction.BRAKE);
        }

        arenaController.moveFocusCar(directions, time);
    }
}
