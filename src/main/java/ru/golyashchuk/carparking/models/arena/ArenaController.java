package ru.golyashchuk.carparking.models.arena;

import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;
import ru.golyashchuk.carparking.models.car.Car;
import ru.golyashchuk.carparking.models.car.Direction;
import ru.golyashchuk.carparking.utils.collision.CollisionHandler;
import ru.golyashchuk.carparking.view.arena.ArenaView;

import java.util.Set;

public class ArenaController {
    private final Arena arena;
    private final ArenaView arenaView;

    public ArenaView getArenaView() {
        return arenaView;
    }

    public ArenaController(Arena arena) {
        this.arena = arena;
        this.arenaView = new ArenaView(arena);
    }

    public void focusCar(double x, double y) {
        Point2D point = new Point2D(x, y);
        System.out.println(point);
        for (Car car : arena.getCars()) {
            System.out.println(car.getCollision());
            if (car.getCollision().contains(point)) {
                focusCar(car);
            }
        }
    }

    private void focusCar(Car car) {
        arenaView.unfocusCar(arena.getFocusedCar());
        arena.setFocusedCar(car);
        arenaView.focusCar(car);
    }

    public void moveFocusCar(Set<Direction> direction, double t) {
        Car focusedCar = arena.getFocusedCar();
        focusedCar.straightWheels();
        focusedCar.stop();
        if (direction.contains(Direction.LEFT)) {
            focusedCar.turnLeft();
        }
        if (direction.contains(Direction.RIGHT)) {
            focusedCar.turnRight();
        }
        if (direction.contains(Direction.BACK)) {
            focusedCar.back();
        }
        if (direction.contains(Direction.FORWARD)) {
            focusedCar.forward();
        }

        if (willCollisionWithOther(focusedCar, t) || willBehindArena(focusedCar, t)) {
            return;
        }

        focusedCar.move(t);
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
}
