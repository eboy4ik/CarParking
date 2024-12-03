package ru.golyashchuk.carparking.controllers;

import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ru.golyashchuk.carparking.config.SettingsConfiguration;
import ru.golyashchuk.carparking.models.arena.ArenaEditor;
import ru.golyashchuk.carparking.models.car.Car;

public class LevelEditorController {
    private final Stage primaryStage;
    ArenaEditor editor;

    public LevelEditorController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        initialize();
    }

    private void initialize() {
        BorderPane pane = new BorderPane();
        editor = new ArenaEditor();
        pane.setCenter(editor.getArenaView().getView());
//        gamePane.setCenter(arenaController.getArenaView().getView());
//
//        gamePane.setOnKeyPressed(this::onKeyPressed);
//        gamePane.setOnKeyReleased(this::onKeyReleased);
        editor.getArenaView().getView().setOnMouseClicked(this::onMouseClicked);
        editor.getArenaView().getView().setOnMouseDragged(this::onMouseDragged);
//

        Scene scene = new Scene(pane, SettingsConfiguration.getWindowWidth(), SettingsConfiguration.getWindowHeight());
        primaryStage.setScene(scene);
        primaryStage.show();

        pane.requestFocus();
    }

    private void onMouseDragged(MouseEvent mouseEvent) {

    }

    private void onMouseClicked(MouseEvent mouseEvent) {
        Car car = new Car(mouseEvent.getX(), mouseEvent.getY(), 0);
        editor.addCar(car);
        editor.getArenaView().render();
    }
}
