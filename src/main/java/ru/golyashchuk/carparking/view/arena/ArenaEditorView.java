package ru.golyashchuk.carparking.view.arena;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import ru.golyashchuk.carparking.models.Collision;
import ru.golyashchuk.carparking.models.Model;
import ru.golyashchuk.carparking.models.ModelType;
import ru.golyashchuk.carparking.models.arena.Arena;
import ru.golyashchuk.carparking.models.car.Car;
import ru.golyashchuk.carparking.view.View;

public class ArenaEditorView implements View {
    private BorderPane view;
    private ModelType selectedModel;
    private EditorArenaView arenaView;
    private Object focusable;
    private Button saveArenaButton;
    private Button loadArenaButton;
    private Button clearArenaButton;
    private Pane toolsBar;
    private Pane fileGroup;

    public ArenaEditorView(Arena arena) {
        arenaView = new EditorArenaView(arena);
        initialize();
    }

    private void initialize() {
        view = new BorderPane();

        toolsBar = initializeToolsBar();
        fileGroup = initializeFileGroup();

        view.setCenter(arenaView.getView());
        view.setTop(toolsBar);
        view.setRight(fileGroup);

    }

    public ModelType getSelectedModel() {
        return selectedModel;
    }

    public EditorArenaView getArenaView() {
        return arenaView;
    }

    public Button getSaveArenaButton() {
        return saveArenaButton;
    }

    public Button getLoadArenaButton() {
        return loadArenaButton;
    }

    public Button getClearArenaButton() {
        return clearArenaButton;
    }

    public Pane getToolsBar() {
        return toolsBar;
    }

    public Object getFocusable() {
        return focusable;
    }

    public void setFocusable(Model model) {
        this.focusable = model;
    }

    @Override
    public BorderPane getView() {
        return view;
    }


    private VBox initializeFileGroup() {
        VBox box = new VBox();
        box.setAlignment(Pos.CENTER_LEFT);
        saveArenaButton = new Button("Сохранить");
        loadArenaButton = new Button("Загрузить");
        clearArenaButton = new Button("Очистить");

        box.getChildren().addAll(saveArenaButton, loadArenaButton, clearArenaButton);
        return box;
    }

    private FlowPane initializeToolsBar() {
        FlowPane toolsBar = new FlowPane();
        toolsBar.setAlignment(Pos.CENTER);
        Button noneButton = new Button("Указатель");
        noneButton.setOnAction(event -> setSelectedModel(null));
        Button carButton = new Button("Машина");
        carButton.setOnAction(event -> setSelectedModel(ModelType.CAR));
        Button finishButton = new Button("Финиш");
        finishButton.setOnAction(event -> setSelectedModel(ModelType.FINISH));
        Button collisionButton = new Button("Препятствие");
        collisionButton.setOnAction(event -> setSelectedModel(ModelType.COLLISION));
        toolsBar.getChildren().addAll(noneButton, carButton, finishButton, collisionButton);
        return toolsBar;
    }

    public void setSelectedModel(ModelType selectedModel) {
        this.selectedModel = selectedModel;
    }


    public void focus(Object model) {
        if (model instanceof Collision) {
            arenaView.getCollisions().get(model).focus();
        }

        if (model instanceof Car) {
            arenaView.getCars().get(model).focus();
        }

        if (model instanceof Rectangle) {
            arenaView.getFinish().focus();
            focusable = arenaView.getFinish();
            return;
        }

        focusable = model;
    }

    public void unfocus() {
        if (focusable instanceof Collision) {
            arenaView.getCollisions().get((Collision) focusable).unfocus();
        }
        if (focusable instanceof Car) {
            arenaView.getCars().get((Car) focusable).unfocus();
        }
        if (focusable instanceof FinishView) {
            arenaView.getFinish().unfocus();
        }

        focusable = null;
    }
}
