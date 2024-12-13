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
import ru.golyashchuk.carparking.models.Collision;
import ru.golyashchuk.carparking.models.ModelType;
import ru.golyashchuk.carparking.models.arena.Arena;
import ru.golyashchuk.carparking.models.car.Car;
import ru.golyashchuk.carparking.shape.ResizableRectangle;
import ru.golyashchuk.carparking.utils.ShapeHandler;
import ru.golyashchuk.carparking.view.alert.ExitConfirmationAlert;
import ru.golyashchuk.carparking.view.arena.ArenaEditorView;
import ru.golyashchuk.carparking.view.arena.ArenaView;
import ru.golyashchuk.carparking.view.arena.EditorCollisionView;
import ru.golyashchuk.carparking.view.car.CarView;

import java.util.Optional;

public class LevelEditorController implements Controller {
    private Stage primaryStage;
    private Arena arena;
    private ArenaEditorView editorView;

    public LevelEditorController(Stage primaryStage) {
        initializeScene(primaryStage);
    }

    @Override
    public void initializeScene(Stage stage) {
        this.primaryStage = stage;
        BorderPane pane = new BorderPane();

        arena = new Arena();
        arena.setWidth(500);
        arena.setHeight(500);
        editorView = new ArenaEditorView(arena);
        pane.setCenter(editorView.getView());

        editorView.getArenaView().getView().setOnMouseClicked(this::onMouseClicked);
        editorView.getArenaView().getView().setOnMouseDragged(this::onMouseDragged);

        pane.setOnKeyPressed(this::onKeyPressed);
        Scene scene = new Scene(pane, SettingsConfiguration.getWindowWidth(), SettingsConfiguration.getWindowHeight());
        primaryStage.setScene(scene);
        primaryStage.show();

        pane.requestFocus();
    }

    private void onMouseDragged(MouseEvent mouseEvent) {
        editorView.getFocusable();
    }


    private void onMouseClicked(MouseEvent mouseEvent) {
        if (editorView.getSelectedModel() == null) {
            focus(mouseEvent.getX(), mouseEvent.getY());
            return;
        }

        addObject(mouseEvent.getX(), mouseEvent.getY());
    }


    private void onKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ESCAPE) {
            ExitConfirmationAlert alert = new ExitConfirmationAlert();

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                new MenuController(primaryStage);
            }
        }

        if (keyEvent.getCode() == KeyCode.DELETE) {
            if (editorView.getFocusable() == null) {
                return;
            }

        }
    }

    public void addCar(Car car) {
        arena.addCar(car);
        editorView.getArenaView().addCar(car);
    }

    public void setFinish(Rectangle finish) {
        arena.setFinish(finish);
        editorView.getArenaView().setFinish(ShapeHandler.copyRectangle(finish));
    }

    public void addCollision(Collision collision) {
        arena.addCollisional(collision);
        editorView.getArenaView().addCollision(collision);
    }

    public void addObject(double x, double y) {
        if (editorView.getSelectedModel() == null)
            return;

        if (editorView.getSelectedModel() == ModelType.CAR) {
            Car car = new Car(x, y, 0);
            addCar(car);
        }
        if (editorView.getSelectedModel() == ModelType.FINISH) {
            Rectangle finish = new Rectangle(x, y, 70, 120);
            setFinish(finish);
        }

        if (editorView.getSelectedModel() == ModelType.COLLISION) {
            Collision collision = new Collision(new Rectangle(x, y, 100, 100));
            addCollision(collision);
        }
    }

    public void focus(double x, double y) {
        unfocus();

//        for (CarView carView : arena.getCars()) {
//            if (carView.getCar().getBounds().contains(x, y)) {
//                carView.focus();
//                focusable = carView;
//            }
//        }

        for (EditorCollisionView collision : editorView.getArenaView().getCollisions()) {
            if (collision.getView().getRectangle().contains(x, y)) {
                collision.focus();
                editorView.setFocusable(collision);
                return;
            }
        }
    }

    private void unfocus() {
        if (editorView.getFocusable() != null) {
            editorView.getFocusable().unfocus();
            editorView.setFocusable(null);
        }
    }

}
