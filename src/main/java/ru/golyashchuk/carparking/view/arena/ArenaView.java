package ru.golyashchuk.carparking.view.arena;


import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import ru.golyashchuk.carparking.models.Collision;
import ru.golyashchuk.carparking.models.Model;
import ru.golyashchuk.carparking.models.arena.Arena;
import ru.golyashchuk.carparking.models.car.Car;
import ru.golyashchuk.carparking.view.car.CarEnum;
import ru.golyashchuk.carparking.view.Renderer;
import ru.golyashchuk.carparking.view.View;
import ru.golyashchuk.carparking.view.car.CarView;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ArenaView implements Renderer, View {
    private Pane view;
    private ArenaBoundsView arenaBounds;
    private Map<Car, CarView> cars = new HashMap<>();
    private FinishView finish;
    private List<CollisionView> collisions = new LinkedList<>();

    public ArenaView(Arena arena) {
        this.view = new Pane();
        view.setMaxWidth(arena.getWidth());
        view.setMaxHeight(arena.getHeight());
        initializeBounds(arena);
        initializeFinish(arena);
        initializeCars(arena);
        initializeCollisions(arena);
        cars.get(arena.getMainCar()).mainCar();
    }

    public void focusCar(Car car) {
        CarView carView = cars.get(car);
        carView.focus();
        carView.renderCar(car);
    }

    public void unfocusCar(Car car) {
        CarView carView = cars.get(car);
        carView.unfocus();
        carView.renderCar(car);
    }

    public void setMainCar(Car car) {
        CarView carView = cars.get(car);
        carView.mainCar();
    }

    @Override
    public Pane getView() {
        return view;
    }

    @Override
    public void render(Model model) {
        Arena arena = (Arena) model;
        renderArenaBounds();

        renderCars(arena.getCars());
        if (finish != null) {
//            finish.render();
        }
        renderCollisions();
    }

    private void renderArenaBounds() {

    }

    public void renderCars(List<Car> cars) {
        for (Car car : cars) {
            this.cars.get(car).renderCar(car);
        }
    }

    private void initializeBounds(Arena arena) {
        int w = arena.getWidth();
        int h = arena.getHeight();
        this.arenaBounds = new ArenaBoundsView(new Rectangle(w, h));
        this.view.getChildren().add(arenaBounds.getView());
    }

    private void initializeFinish(Arena arena) {
        if (arena.getFinish() != null) {
            this.finish = new FinishView(arena.getFinish());
            this.view.getChildren().add(finish.getView());
        }
    }

    private void initializeCars(Arena arena) {
        for (Car car : arena.getCars()) {
            CarView carView = CarEnum.GRAYCAR.getCarModel(car);
            cars.put(car, carView);
            this.view.getChildren().add(carView.getView());
        }
    }

    private void deleteCarView(CarView carView) {
        cars.remove(carView);
    }

    private void initializeCollisions(Arena arena) {
        if (arena.getCollisions() == null) {
            return;
        }

        for (Collision collision : arena.getCollisions()) {
            addCollision(collision);
        }
    }

    private void addCollision(Collision collision) {
        CollisionView collisionView = new CollisionView(collision.getCollision());
        collisions.add(collisionView);
        this.view.getChildren().add(collisionView.getView());
    }


    private void renderCollisions() {
        for (CollisionView collisionView : collisions) {
//            collisionView.render();
        }
    }

}
