package ru.golyashchuk.carparking.view.arena;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import ru.golyashchuk.carparking.models.arena.Arena;
import ru.golyashchuk.carparking.models.car.Car;
import ru.golyashchuk.carparking.view.Renderer;
import ru.golyashchuk.carparking.view.View;
import ru.golyashchuk.carparking.view.car.CarEnum;
import ru.golyashchuk.carparking.view.car.CarView;

import java.util.LinkedList;
import java.util.List;

public class EditorArenaView implements Renderer, View {
    private Arena arena;
    private Pane view;
    private EditorArenaBoundsView bounds;
    private List<CarView> cars = new LinkedList<>();

    public EditorArenaView(Arena arena) {
        this.arena = arena;
        initialize();
    }

    private void initialize() {
        this.view = new Pane();
        bounds = new EditorArenaBoundsView(new Rectangle(arena.getWidth(), arena.getHeight()));

        view.maxWidthProperty().bind(bounds.getView().getRectangle().widthProperty());
        view.maxHeightProperty().bind(bounds.getView().getRectangle().heightProperty());
        bounds.getView().getRectangle().setFill(Color.TRANSPARENT);
        view.getChildren().add(bounds.getView());
    }

    @Override
    public Pane getView() {
        return view;
    }

    @Override
    public void render() {

    }

    public void addCar(Car car) {
        CarView carView = CarEnum.GRAYCAR.getCarModel(car);
        cars.add(carView);
        this.view.getChildren().add(carView.getView());
        carView.render();
    }

    private void initializeCars() {
        for (Car car : arena.getCars()) {
            addCar(car);
        }
    }
}
