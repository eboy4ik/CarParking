package ru.golyashchuk.carparking.view.arena;


import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import ru.golyashchuk.carparking.models.arena.Arena;
import ru.golyashchuk.carparking.models.car.Car;
import ru.golyashchuk.carparking.models.car.Collisional;
import ru.golyashchuk.carparking.view.car.CarEnum;
import ru.golyashchuk.carparking.view.Renderer;
import ru.golyashchuk.carparking.view.View;
import ru.golyashchuk.carparking.view.car.CarView;

import java.util.LinkedList;
import java.util.List;

public class ArenaView implements Renderer, View {
    private Arena arena;
    private Pane view;
    private ArenaBoundsView arenaBounds;
    private List<CarView> cars = new LinkedList<>();
    private FinishView finish;
    private List<CollisionView> collisions = new LinkedList<>();

    public ArenaView(Arena arena) {
        this.arena = arena;
        this.view = new Pane();
        view.setMaxWidth(arena.getWidth());
        view.setMaxHeight(arena.getHeight());
        initializeBounds();
        initializeFinish();
        initializeCars();
        initializeCollisions();
    }

    public void updateAllViews() {
        updateCarViews();
    }

    private void updateCarViews() {
        cars.clear();
        initializeCars();
    }

    public void focusCar(Car car) {
        for (CarView carView : cars) {
            if (carView.getCar() == car) {
                carView.focus();
            }
        }
    }

    public void unfocusCar(Car car) {
        for (CarView carView : cars) {
            if (carView.getCar() == car) {
                carView.unfocus();
            }
        }
    }

    public void mainCar(Car car) {
        for (CarView carView : cars) {
            if (carView.getCar() == car) {
                carView.mainCar();
            }
        }
    }

    @Override
    public Pane getView() {
        return view;
    }

    @Override
    public void render() {
        renderAll();
    }

    public void renderAll() {
        renderArenaBounds();
        renderCars();
        if (finish != null) {
            finish.render();
        }
        renderCollisions();
    }


    private void initializeBounds() {
        int w = arena.getWidth();
        int h = arena.getHeight();
        this.arenaBounds = new ArenaBoundsView(new Rectangle(w, h));
        this.view.getChildren().add(arenaBounds.getView());
    }

    private void initializeFinish() {
        if (arena.getFinish() != null) {
            this.finish = new FinishView(arena.getFinish());
            this.view.getChildren().add(finish.getView());
        }
    }

    private void initializeCars() {
        for (Car car : arena.getCars()) {
            CarView carView = CarEnum.GRAYCAR.getCarModel(car);
            cars.add(carView);
            this.view.getChildren().add(carView.getView());
        }
    }

    private void deleteCarView(CarView carView) {
        cars.remove(carView);
    }

    private void initializeCollisions() {
        if (arena.getCollisions() == null) {
            return;
        }

        for (Collisional collision : arena.getCollisions()) {
            collisions.add(new CollisionView(collision.getCollision()));
        }
    }

    private void renderArenaBounds() {

    }

    private void renderCars() {
        for (CarView carView : cars) {
            carView.render();
        }
    }

    private void renderCollisions() {
        for (CollisionView collisionView : collisions) {
            collisionView.render();
        }
    }

}
