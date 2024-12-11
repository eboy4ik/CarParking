package ru.golyashchuk.carparking.view.arena;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.shape.Rectangle;
import ru.golyashchuk.carparking.models.ModelType;
import ru.golyashchuk.carparking.models.arena.Arena;
import ru.golyashchuk.carparking.models.arena.ArenaEditor;
import ru.golyashchuk.carparking.models.car.Car;
import ru.golyashchuk.carparking.view.View;

public class ArenaEditorView implements View {
    private BorderPane view;
    private final ArenaEditor editor;

    public ArenaEditorView(ArenaEditor editor) {
        this.editor = editor;
        initialize();
    }

    @Override
    public BorderPane getView() {
        return view;
    }

    private void initialize() {
        view = new BorderPane();
        view.setCenter(editor.getArenaView().getView());

        FlowPane flowPane = new FlowPane();
        flowPane.setAlignment(Pos.CENTER);
        view.setTop(flowPane);

        Button noneButton = new Button("None");
        noneButton.setOnAction(event -> editor.setSelectedModel(null));

        Button carButton = new Button("Car");
        carButton.setOnAction(event -> editor.setSelectedModel(ModelType.CAR));

        Button finishButton = new Button("Finish");
        finishButton.setOnAction(event -> editor.setSelectedModel(ModelType.FINISH));
        Button collisionButton = new Button("Collisional");
        collisionButton.setOnAction(event -> editor.setSelectedModel(ModelType.COLLISION));

        flowPane.getChildren().addAll(noneButton, carButton, finishButton, collisionButton);
    }

}
