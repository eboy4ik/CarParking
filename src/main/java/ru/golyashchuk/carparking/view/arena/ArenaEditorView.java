package ru.golyashchuk.carparking.view.arena;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import ru.golyashchuk.carparking.models.ModelType;
import ru.golyashchuk.carparking.models.arena.Arena;
import ru.golyashchuk.carparking.view.View;

public class ArenaEditorView implements View {
    private BorderPane view;
    private ModelType selectedModel;
    private EditorArenaView arenaView;
    private Focusable focusable;

    public ArenaEditorView(Arena arena) {
        arenaView = new EditorArenaView(arena);
        initialize();
    }

    public ModelType getSelectedModel() {
        return selectedModel;
    }

    public EditorArenaView getArenaView() {
        return arenaView;
    }

    public Focusable getFocusable() {
        return focusable;
    }

    public void setFocusable(Focusable focusable) {
        this.focusable = focusable;
    }

    @Override
    public BorderPane getView() {
        return view;
    }

    private void initialize() {
        view = new BorderPane();
        view.setCenter(arenaView.getView());

        FlowPane flowPane = new FlowPane();
        flowPane.setAlignment(Pos.CENTER);
        view.setTop(flowPane);

        Button noneButton = new Button("None");
        noneButton.setOnAction(event -> setSelectedModel(null));

        Button carButton = new Button("Car");
        carButton.setOnAction(event -> setSelectedModel(ModelType.CAR));

        Button finishButton = new Button("Finish");
        finishButton.setOnAction(event -> setSelectedModel(ModelType.FINISH));
        Button collisionButton = new Button("Collisional");
        collisionButton.setOnAction(event -> setSelectedModel(ModelType.COLLISION));

        flowPane.getChildren().addAll(noneButton, carButton, finishButton, collisionButton);
    }

    public void setSelectedModel(ModelType selectedModel) {
        this.selectedModel = selectedModel;
    }


}
