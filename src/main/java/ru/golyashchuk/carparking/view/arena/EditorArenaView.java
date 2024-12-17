package ru.golyashchuk.carparking.view.arena;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import ru.golyashchuk.carparking.models.Collision;
import ru.golyashchuk.carparking.models.Model;
import ru.golyashchuk.carparking.models.arena.Arena;
import ru.golyashchuk.carparking.models.car.Car;
import ru.golyashchuk.carparking.view.Renderer;
import ru.golyashchuk.carparking.view.View;
import ru.golyashchuk.carparking.view.car.CarEnum;
import ru.golyashchuk.carparking.view.car.CarView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EditorArenaView implements Renderer, View {
    private Pane view;
    private EditorArenaBoundsView bounds;
    private FinishView finish;
    private Map<Car, CarView> cars = new HashMap<>();
    private Map<Collision, EditorCollisionView> collisions = new HashMap<>();

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
        initializeBounds(arena.getWidth(), arena.getHeight());
        initializeCars(arena.getCars());
        initializeCollisions(arena.getCollisions());
        if (arena.getFinish() != null) {
            initializeFinish(arena.getFinish());
        }

    }

    private void initializeBounds(int width, int height) {
        bounds = new EditorArenaBoundsView(new Rectangle(width, height));
        view.maxWidthProperty().bind(bounds.getView().getRectangle().widthProperty());
        view.maxHeightProperty().bind(bounds.getView().getRectangle().heightProperty());
        bounds.getView().getRectangle().setFill(Color.TRANSPARENT);
        view.getChildren().add(bounds.getView());
    }

    private void initializeFinish(Rectangle finish) {
        setFinish(finish);
    }

    private void initializeCollisions(List<Collision> collisions) {
        for (Collision collision : collisions) {
            addCollision(collision);
        }
    }

    private void initializeCars(List<Car> cars) {
        for (Car car : cars) {
            addCar(car);
        }
    }

    public Map<Car, CarView> getCars() {
        return cars;
    }

    public Map<Collision, EditorCollisionView> getCollisions() {
        return collisions;
    }

    public EditorArenaBoundsView getBounds() {
        return bounds;
    }

    public FinishView getFinish() {
        return finish;
    }


    @Override
    public Pane getView() {
        return view;
    }

    @Override
    public void render(Model model) {
        Arena arena = (Arena) model;

        renderCars(arena.getCars());
//        bounds.render();
    }

    private void renderCars(List<Car> cars) {
        for (Car car : cars) {
            this.cars.get(car).renderCar(car);
        }
    }

    public void addCar(Car car) {
        CarView carView = CarEnum.GRAYCAR.getCarModel(car);
        cars.put(car, carView);
        this.view.getChildren().add(carView.getView());
        carView.renderCar(car);
    }

    public void addCollision(Collision collision) {
        EditorCollisionView collisionView = new EditorCollisionView(collision);
        collisions.put(collision, collisionView);
        this.view.getChildren().add(collisionView.getView());
        collisionView.render(collision);
    }

}
