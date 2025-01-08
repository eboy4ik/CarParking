package ru.golyashchuk.carparking.controllers;

import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.input.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ru.golyashchuk.carparking.models.Collision;
import ru.golyashchuk.carparking.models.ModelType;
import ru.golyashchuk.carparking.models.arena.Arena;
import ru.golyashchuk.carparking.models.car.Car;
import ru.golyashchuk.carparking.utils.Serializator;
import ru.golyashchuk.carparking.utils.ShapeHandler;
import ru.golyashchuk.carparking.view.alert.ExitConfirmationAlert;
import ru.golyashchuk.carparking.view.arena.*;

import java.io.*;
import java.util.Map;
import java.util.Optional;

public class LevelEditorController implements Controller, ArenaViewUpdateListener {
    public static final String ARENA_DIR_PATH = "/src/main/resources/levels";
    public static final double ANGLE_STEP = 22.5;
    private Stage primaryStage;
    private Arena arena;
    private ArenaEditorView editorView;
    private BorderPane pane;

    public LevelEditorController(Stage primaryStage) {
        initializeScene(primaryStage);
    }

    @Override
    public void initializeScene(Stage stage) {
        this.primaryStage = stage;
        pane = new BorderPane();

        initializeArena();
        initializeEditorView();

        pane.setOnKeyPressed(this::onKeyPressed);
        pane.setOnMouseReleased(this::onMouseReleased);
        pane.setOnScroll(this::onScroll);
        Scene scene = new Scene(pane, stage.getScene().getWidth(), stage.getScene().getHeight());
        primaryStage.setScene(scene);
        primaryStage.show();

        pane.requestFocus();
    }

    private void onMouseReleased(MouseEvent mouseEvent) {
        editorView.getArenaView().render(arena);
    }

    private void onScroll(ScrollEvent scrollEvent) {
        onRotate(editorView.getFocusable(), Math.signum(scrollEvent.getDeltaY()) * ANGLE_STEP);
    }


    private void initializeArena() {
        arena = new Arena();
        arena.setWidth(500);
        arena.setHeight(500);
    }

    private void initializeEditorView() {
        editorView = new ArenaEditorView(arena);

        editorView.getArenaView().getView().setOnMousePressed(this::onMouseClicked);
        editorView.getArenaView().getView().setOnMouseDragged(this::onMouseDragged);
        editorView.getArenaView().setUpdateListener(this);

        editorView.getSaveArenaButton().setOnAction(event -> saveArena());
        editorView.getLoadArenaButton().setOnAction(event -> loadArena());
        editorView.getClearArenaButton().setOnAction(event -> clearArena());

        pane.setCenter(editorView.getView());
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
            deleteSelectedObject();
        }
    }

    public void addCar(Car car) {
        if (arena.getCars().isEmpty()) {
            arena.setMainCar(car);
        }
        arena.addCar(car);
        editorView.getArenaView().addCar(car);

    }

    public void setFinish(Rectangle finish) {
        arena.setFinish(finish);
        editorView.getArenaView().setFinish(ShapeHandler.copyRectangle(finish));
    }

    public void addCollision(Collision collision) {
        arena.addCollision(collision);
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

        editorView.setSelectedModel(null);
    }

    public void deleteSelectedObject() {
        Object focusable = editorView.getFocusable();
        if (focusable == null) {
            return;
        }
        if (focusable instanceof Car && arena.getCars().contains(focusable)) {
            arena.getCars().remove(focusable);
            editorView.getArenaView().deleteCar((Car) focusable);
        }

        if (focusable instanceof Collision && arena.getCollisions().contains(focusable)) {
            arena.getCollisions().remove(focusable);
            editorView.getArenaView().deleteCollision((Collision) focusable);
        }
        editorView.getArenaView().render(arena);
        editorView.setFocusable(null);
    }

    public void focus(double x, double y) {
        unfocus();

        if (arena.getFinish() != null && arena.getFinish().getBoundsInParent().contains(x, y)) {
            editorView.focus(arena.getFinish());
            return;
        }

        for (Collision collision : arena.getCollisions()) {
            if (collision.getCollision().getBoundsInParent().contains(x, y)) {
                editorView.focus(collision);
                return;
            }
        }

        for (Car car : arena.getCars()) {
            if (car.getCollision().getBoundsInParent().contains(x, y)) {
                editorView.focus(car);
                return;
            }
        }
    }

    private void unfocus() {
        if (editorView.getFocusable() != null) {
            editorView.unfocus();
            editorView.setFocusable(null);
        }
    }

    private void saveArena() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Сохраните файл");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("SER files (*.ser)", "*.ser");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir") + ARENA_DIR_PATH));
        fileChooser.setInitialFileName("newarena.ser");
        fileChooser.getExtensionFilters().add(extFilter);
        File fileToSave = fileChooser.showSaveDialog(primaryStage);
        if (fileToSave == null) {
            return;
        }

        try {
            Serializator.serializeArena(arena, fileToSave);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    private void loadArena() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите файл для загрузки");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir") + ARENA_DIR_PATH));
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("SER files (*.ser)", "*.ser");
        fileChooser.getExtensionFilters().add(extFilter);
        File selectedFile = fileChooser.showOpenDialog(primaryStage);

        if (selectedFile == null) {
            return;
        }

        try {
            arena = Serializator.deserializeArena(selectedFile);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        initializeEditorView();
    }

    private void clearArena() {
        initializeArena();
        initializeEditorView();
    }


    @Override
    public void onMove(Object object, double newX, double newY) {
        if (object instanceof Car && arena.getCars().contains(object)) {
            Car car = (Car) object;
            car.setX(newX);
            car.setY(newY);
            return;
        }

        if (object instanceof FinishView) {
            arena.getFinish().setX(newX);
            arena.getFinish().setY(newY);
            return;
        }

        if (object instanceof Collision && arena.getCollisions().contains(object)) {
            Collision collision = (Collision) object;
            collision.getCollision().setX(newX);
            collision.getCollision().setY(newY);
            return;
        }
    }

    @Override
    public void onResize(Object object, double newWidth, double newHeight) {
        if (object instanceof EditorArenaBoundsView) {
            arena.setWidth((int) newWidth);
            arena.setHeight((int) newHeight);
            return;
        }

        if (object instanceof Collision) {
            for (Collision collision : arena.getCollisions()) {
                if (collision == object) {
                    collision.setCollision(new Rectangle(collision.getCollision().getX(), collision.getCollision().getY(), newWidth, newHeight));
                }
            }
        }
    }

    @Override
    public void onRotate(Object object, double addAngle) {
        if (object == null) {
            return;
        }

        if (object instanceof Car && arena.getCars().contains(object)) {
            Car car = (Car) object;
            car.setRotate(car.getRotate() + addAngle);
        }

        if (object instanceof FinishView) {
            arena.getFinish().setRotate(arena.getFinish().getRotate() + addAngle);
        }

        if (object instanceof Collision && arena.getCollisions().contains(object)) {
            Collision collision = (Collision) object;
            collision.getCollision().setRotate(collision.getCollision().getRotate() + addAngle);
        }

        editorView.getArenaView().render(arena);
    }
}
