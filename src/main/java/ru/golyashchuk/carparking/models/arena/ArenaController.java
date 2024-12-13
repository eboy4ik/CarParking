package ru.golyashchuk.carparking.models.arena;

import javafx.animation.AnimationTimer;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import ru.golyashchuk.carparking.models.car.Car;
import ru.golyashchuk.carparking.models.car.Direction;
import ru.golyashchuk.carparking.utils.collision.CollisionHandler;
import ru.golyashchuk.carparking.view.arena.ArenaView;

import java.util.HashSet;
import java.util.Set;

public class ArenaController {
    private final Arena arena;
    private final ArenaView arenaView;

    private final Set<KeyCode> pressedKeys = new HashSet<>();
    private final AnimationTimer timer;

    public ArenaView getArenaView() {
        return arenaView;
    }

    public ArenaController(Arena arena) {
        this.arena = arena;
        this.arenaView = new ArenaView(arena);

        arenaView.getView().setOnMouseClicked(this::onMouseClicked);
        arenaView.getView().setOnKeyPressed(this::onKeyPressed);
        arenaView.getView().setOnKeyReleased(this::onKeyReleased);

        arenaView.render(arena);
        final LongProperty lastUpdateTime = new SimpleLongProperty();
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {

                double elapsedSeconds = (now - lastUpdateTime.get()) / 1_000_000_000.0;
                if (checkWin()) {
                    System.out.println("WIIIN");
                }
                updateFocusedCarState(elapsedSeconds);
                moveCars(elapsedSeconds);
                arenaView.renderCars(arena.getCars());
                lastUpdateTime.set(now);
            }
        };
        timer.start();
    }


    public void focusCar(double x, double y) {
        Point2D point = new Point2D(x, y);
        for (Car car : arena.getCars()) {
            if (car.getCollision().getBoundsInParent().contains(point)) {
                focusCar(car);
            }
        }
    }

    private void focusCar(Car car) {
        arenaView.unfocusCar(arena.getFocusedCar());
        arena.setFocusedCar(car);
        arenaView.focusCar(car);
    }

    private void updateFocusedCarState(double time) {
        HashSet<Direction> directions = new HashSet<>();
        if (pressedKeys.contains(KeyCode.W)) {
            directions.add(Direction.FORWARD);
        }
        if (pressedKeys.contains(KeyCode.S)) {
            directions.add(Direction.BACK);
        }
        if (pressedKeys.contains(KeyCode.A)) {
            directions.add(Direction.LEFT);
        }
        if (pressedKeys.contains(KeyCode.D)) {
            directions.add(Direction.RIGHT);
        }
        if (pressedKeys.contains(KeyCode.SPACE)) {
            directions.add(Direction.BRAKE);
        }
        setDirectionsFocusCar(directions, time);
    }

    private void setDirectionsFocusCar(Set<Direction> direction, double t) {
        Car focusedCar = arena.getFocusedCar();
        focusedCar.straightWheels();
        if (direction.contains(Direction.LEFT)) {
            focusedCar.turnLeft();
        }
        if (direction.contains(Direction.RIGHT)) {
            focusedCar.turnRight();
        }
        if (direction.contains(Direction.BACK)) {
            focusedCar.back(t);
        }
        if (direction.contains(Direction.FORWARD)) {
            focusedCar.forward(t);
        }

        if (direction.contains(Direction.BRAKE)) {
            focusedCar.brake(t);
        }

        if (!direction.contains(Direction.FORWARD) && !direction.contains(Direction.BACK)) {
            focusedCar.slowly(t);
        }
    }

    private void moveCars(double time) {
        for (Car car : arena.getCars()) {
            if (willCollisionWithOther(car, time) || willBehindArena(car, time)) {
                car.stop();
            } else {
                car.move(time);
            }

            if (car != arena.getFocusedCar()) {
                car.slowly(time);
            }
        }
    }

    private boolean willBehindArena(Car focusedCar, double time) {
        Rectangle arenaBounds = new Rectangle(arena.getWidth(), arena.getHeight());
        return !CollisionHandler.containsShape(arenaBounds, focusedCar.getFutureBounds(time));
    }

    private boolean willCollisionWithOther(Car focusedCar, double time) {
        for (Car car : arena.getCars()) {
            if (car != focusedCar && focusedCar.willCollision(car, time)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkWin() {
        return CollisionHandler.containsShape(arena.getFinish(), arena.getMainCar().getCollision());
    }

    private void onMouseClicked(MouseEvent mouseEvent) {
        focusCar(mouseEvent.getX(), mouseEvent.getY());
    }

    private void onKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ESCAPE) {
            pressedKeys.clear();
        }
        pressedKeys.add(event.getCode());
    }

    private void onKeyReleased(KeyEvent event) {
        pressedKeys.remove(event.getCode());
    }

    public void stopGame() {
        timer.stop();
    }
}
