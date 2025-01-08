package ru.golyashchuk.carparking.utils.arena;

import javafx.scene.shape.Shape;
import ru.golyashchuk.carparking.config.ConfigurationManager;
import ru.golyashchuk.carparking.controllers.GameController;
import ru.golyashchuk.carparking.models.AddableView;
import ru.golyashchuk.carparking.models.Collision;
import ru.golyashchuk.carparking.models.Model;
import ru.golyashchuk.carparking.models.arena.Arena;
import ru.golyashchuk.carparking.models.car.Car;
import ru.golyashchuk.carparking.models.car.MathCar;
import ru.golyashchuk.carparking.utils.Serializator;
import ru.golyashchuk.carparking.utils.ShapeHandler;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ArenaHandler {
    private ArenaHandler() {
    }

    public static Arena copyArena(Arena arena) {
        Arena newArena = new Arena();
        newArena.setWidth(arena.getWidth());
        newArena.setHeight(arena.getHeight());
        newArena.setFinish(ShapeHandler.copyRectangle(arena.getFinish()));
        newArena.setCars(ArenaHandler.copyCars(arena.getCars()));
        newArena.setCollisions(ArenaHandler.copyCollisions(arena.getCollisions()));
        newArena.setMainCar(ArenaHandler.findMainCar(newArena.getCars(), arena.getMainCar()));

        return newArena;
    }

    private static Car findMainCar(Set<Car> cars, Car mainCar) {
        for (Car car : cars) {
            if (car.getX() == mainCar.getX() && car.getY() == mainCar.getY()) {
                return car;
            }
        }
        return null;
    }

    private static Set<Collision> copyCollisions(Set<Collision> collisions) {
        Set<Collision> set = new HashSet<>();
        for (Collision collision : collisions) {
            set.add(ArenaHandler.copyCollision(collision));
        }
        return set;
    }

    private static Set<Car> copyCars(Set<Car> cars) {
        Set<Car> set = new HashSet<>();
        for (Car car : cars) {
            set.add(ArenaHandler.copyCar(car));
        }
        return set;
    }

    private static Car copyCar(Car car) {
        Car copyCar = new Car(car.getX(), car.getY(), Math.toDegrees(car.getCarOrientation()));
        copyCar.setAxleBase(car.getAxleBase());
        copyCar.setDistanceWheels(car.getDistanceWheels());
        return copyCar;
    }

    private static Collision copyCollision(Collision collision) {
        return new Collision(ShapeHandler.copyRectangle(collision.getCollision()));
    }

    public static Arena loadLevel(int level) {
        Arena arena = null;
        File file = new File(System.getProperty("user.dir") + "/src/main/resources/" + ConfigurationManager.getProperty("path.levels.passing") + level + ".ser");
        try {
            arena = Serializator.deserializeArena(file);
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
        return arena;
    }
}
