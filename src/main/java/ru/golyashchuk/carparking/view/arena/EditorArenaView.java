package ru.golyashchuk.carparking.view.arena;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import ru.golyashchuk.carparking.models.Collision;
import ru.golyashchuk.carparking.models.Model;
import ru.golyashchuk.carparking.models.arena.Arena;
import ru.golyashchuk.carparking.models.car.Car;
import ru.golyashchuk.carparking.models.car.Collisional;
import ru.golyashchuk.carparking.view.Renderer;
import ru.golyashchuk.carparking.view.View;
import ru.golyashchuk.carparking.view.car.CarEnum;
import ru.golyashchuk.carparking.view.car.CarView;

import java.util.LinkedList;
import java.util.List;

public class EditorArenaView implements Renderer, View {
    private Pane view;
    private EditorArenaBoundsView bounds;
    private FinishView finish;
    private List<CarView> cars = new LinkedList<>();
    private List<EditorCollisionView> collisions = new LinkedList<>();

    public EditorArenaView(Arena arena) {
        initialize(arena);
    }

    public void setFinish(Rectangle finish) {
        if (this.finish != null) {
            view.getChildren().remove(this.finish.getView());
        }
        this.finish = new FinishView(finish);
        view.getChildren().add(this.finish.getView());
    }

    private void initialize(Arena arena) {
        this.view = new Pane();
        bounds = new EditorArenaBoundsView(new Rectangle(arena.getWidth(), arena.getHeight()));

        view.maxWidthProperty().bind(bounds.getView().getRectangle().widthProperty());
        view.maxHeightProperty().bind(bounds.getView().getRectangle().heightProperty());
        bounds.getView().getRectangle().setFill(Color.TRANSPARENT);
        view.getChildren().addAll(bounds.getView());
    }

    public EditorArenaBoundsView getBounds() {
        return bounds;
    }

    public FinishView getFinish() {
        return finish;
    }

    public List<CarView> getCars() {
        return cars;
    }

    public List<EditorCollisionView> getCollisions() {
        return collisions;
    }

    @Override
    public Pane getView() {
        return view;
    }

    @Override
    public void render(Model model) {
        bounds.render(model);
    }

    public void addCar(Car car) {
        CarView carView = CarEnum.GRAYCAR.getCarModel(car);
        cars.add(carView);
        this.view.getChildren().add(carView.getView());
        carView.renderCar(car);
    }

    public void addCollision(Collision collisional) {
        EditorCollisionView collisionView = new EditorCollisionView(collisional);
        collisions.add(collisionView);
        this.view.getChildren().add(collisionView.getView());
        collisionView.render(collisional);
    }
}
