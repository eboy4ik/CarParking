package ru.golyashchuk.carparking.view.arena;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import ru.golyashchuk.carparking.models.Collision;
import ru.golyashchuk.carparking.models.Model;
import ru.golyashchuk.carparking.models.arena.Arena;
import ru.golyashchuk.carparking.models.car.Car;
import ru.golyashchuk.carparking.shape.UpdateListener;
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
    private ArenaViewUpdateListener updateListener;

    public ArenaViewUpdateListener getUpdateListener() {
        return updateListener;
    }

    public void setUpdateListener(ArenaViewUpdateListener updateListener) {
        this.updateListener = updateListener;
    }

    public EditorArenaView(Arena arena) {
        initialize(arena);
    }

    public void setFinish(Rectangle finish) {
        if (this.finish != null) {
            view.getChildren().remove(this.finish.getView());
        }
        this.finish = new FinishView(finish);
        this.finish.getView().setOnMouseDragged(this::onMouseDraggedFinish);
        view.getChildren().add(this.finish.getView());
    }

    private void onMouseDraggedFinish(MouseEvent mouseEvent) {
        if (this.finish == null) {
            return;
        }
        this.finish.getView().setX(mouseEvent.getX());
        this.finish.getView().setY(mouseEvent.getY());
        updateListener.onMove(finish, mouseEvent.getX(), mouseEvent.getY());
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
        bounds.getView().setUpdateListener(new UpdateListener() {
            @Override
            public void onResize(double newWidth, double newHeight) {
                if (updateListener != null) {
                    updateListener.onResize(bounds, newWidth, newHeight);
                }
            }

            @Override
            public void onMove(double newX, double newY) {
                if (updateListener != null) {
                    updateListener.onMove(bounds, newX, newY);
                }
            }
        });
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

    public void renderCars(List<Car> cars) {
        for (Car car : cars) {
            this.cars.get(car).renderCar(car);
        }
    }

    public void addCar(Car car) {
        CarView carView = CarEnum.GRAYCAR.getCarModel(car);
        carView.getView().setOnMouseDragged(event -> onMouseDraggedCar(event, car));
        cars.put(car, carView);
        this.view.getChildren().add(carView.getView());
        carView.renderCar(car);
    }

    private void onMouseDraggedCar(MouseEvent event, Car car) {
        System.out.println("drag: " + event.getX());
        updateListener.onMove(car, event.getX(), event.getY());
    }

    public void addCollision(Collision collision) {
        EditorCollisionView collisionView = new EditorCollisionView(collision);
        collisionView.getView().getRectangle().setOnMouseDragged(event -> onMouseDraggedCollision(event, collision));
        collisionView.getView().setUpdateListener(new UpdateListener() {
            @Override
            public void onResize(double newWidth, double newHeight) {
                if (updateListener != null) {
                    updateListener.onResize(collision, newWidth, newHeight);
                }
            }

            @Override
            public void onMove(double newX, double newY) {
                if (updateListener != null) {
                    updateListener.onMove(collision, newX, newY);
                }
            }
        });
        collisions.put(collision, collisionView);
        this.view.getChildren().add(collisionView.getView());
        collisionView.render(collision);
    }

    private void onMouseDraggedCollision(MouseEvent mouseEvent, Collision collision) {
        collisions.get(collision).getView().setX(mouseEvent.getX());
        collisions.get(collision).getView().setY(mouseEvent.getY());

        updateListener.onMove(collision, mouseEvent.getX(), mouseEvent.getY());
    }

}
