package ru.golyashchuk.carparking.controllers;

import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import ru.golyashchuk.carparking.config.SettingsConfiguration;
import ru.golyashchuk.carparking.models.ModelType;
import ru.golyashchuk.carparking.models.arena.Arena;
import ru.golyashchuk.carparking.models.arena.ArenaEditor;
import ru.golyashchuk.carparking.models.car.Car;
import ru.golyashchuk.carparking.shape.ResizableRectangle;
import ru.golyashchuk.carparking.view.alert.ExitConfirmationAlert;
import ru.golyashchuk.carparking.view.arena.ArenaEditorView;
import ru.golyashchuk.carparking.view.arena.ArenaView;
import ru.golyashchuk.carparking.view.car.CarView;

import java.util.Optional;

public class LevelEditorController implements Controller {
    private Stage primaryStage;
    private ArenaEditor editor;

    public LevelEditorController(Stage primaryStage) {
        initializeScene(primaryStage);
    }

    @Override
    public void initializeScene(Stage stage) {
        this.primaryStage = stage;
        BorderPane pane = new BorderPane();

        editor = new ArenaEditor();
        ArenaEditorView arenaEditorView = new ArenaEditorView(editor);
        pane.setCenter(arenaEditorView.getView());

        editor.getArenaView().getView().setOnMouseClicked(this::onMouseClicked);


        pane.setOnKeyPressed(this::onKeyPressed);
        Scene scene = new Scene(pane, SettingsConfiguration.getWindowWidth(), SettingsConfiguration.getWindowHeight());
        primaryStage.setScene(scene);
        primaryStage.show();

        pane.requestFocus();
    }


    private void onMouseClicked(MouseEvent mouseEvent) {
        if (editor.getSelectedModel() == null) {
            editor.focus(mouseEvent.getX(), mouseEvent.getY());
            return;
        }

        editor.addObject(mouseEvent.getX(), mouseEvent.getY());
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ESCAPE) {
            ExitConfirmationAlert alert = new ExitConfirmationAlert();

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                new MenuController(primaryStage);
            }
        }
    }
}
