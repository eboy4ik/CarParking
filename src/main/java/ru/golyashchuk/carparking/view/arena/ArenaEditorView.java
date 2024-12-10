package ru.golyashchuk.carparking.view.arena;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.shape.Rectangle;
import ru.golyashchuk.carparking.models.arena.Arena;
import ru.golyashchuk.carparking.models.arena.ArenaEditor;
import ru.golyashchuk.carparking.models.car.Car;
import ru.golyashchuk.carparking.view.View;

public class ArenaEditorView implements View {
    private BorderPane view;
    private final ArenaEditor editor;
    private Car car;

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
        Button carButton = new Button("Car");
        Button finishButton = new Button("Finish");
        Button collisionButton = new Button("Collisional");
        flowPane.getChildren().addAll(carButton, finishButton, collisionButton);

        editor.getArenaView().getView().setOnMouseClicked(this::onMouseClicked);
//        editor.getArenaView().getView().setOnMouseDragged(this::onMouseDragged);
    }

    private void onMouseDragged(MouseEvent mouseEvent) {
        car.setX(mouseEvent.getX());
        car.setY(mouseEvent.getY());
//        editor.getArenaView().render();
        Rectangle rect = new Rectangle();
//        rect.set
    }

    private void onMouseClicked(MouseEvent mouseEvent) {
        System.out.println(mouseEvent.getX() + "  " + mouseEvent.getY());
        this.car = new Car(mouseEvent.getX(), mouseEvent.getY(), 0);
        editor.addCar(this.car);
//        editor.getArenaView().render();
    }

}
