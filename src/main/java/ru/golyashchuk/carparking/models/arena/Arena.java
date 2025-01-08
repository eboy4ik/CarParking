package ru.golyashchuk.carparking.models.arena;

import javafx.scene.shape.Rectangle;
import ru.golyashchuk.carparking.models.Collision;
import ru.golyashchuk.carparking.models.Model;
import ru.golyashchuk.carparking.models.car.Car;
import ru.golyashchuk.carparking.utils.Serializator;
import ru.golyashchuk.carparking.utils.collision.CollisionHandler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Arena implements Serializable, Model {
    private int width;
    private int height;

    private Car mainCar;
    private transient Rectangle finish;
    private Set<Car> cars = new HashSet<>();
    private Set<Collision> collisions = new HashSet<>();

    private Car focusedCar;

    public Arena() {
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Car getMainCar() {
        return mainCar;
    }

    public void setMainCar(Car mainCar) {
        this.mainCar = mainCar;
    }

    public Rectangle getFinish() {
        return finish;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    public Set<Collision> getCollisions() {
        return collisions;
    }

    public void setCollisions(Set<Collision> collisions) {
        this.collisions = collisions;
    }

    public Car getFocusedCar() {
        return focusedCar;
    }

    public void addCar(Car car) {
        if (!cars.contains(car)) {
            cars.add(car);
        }
    }

    public void addCollision(Collision obj) {
        collisions.add(obj);
    }

    public boolean checkEnd() {
        return CollisionHandler.containsShape(finish, mainCar.getCollision());
    }

    public void setFocusedCar(Car focusedCar) {
        this.focusedCar = focusedCar;
    }

    public void setFinish(Rectangle finish) {
        this.finish = finish;
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        Serializator.serializeRectangle(finish, oos);
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        finish = Serializator.deserializeRectangle(ois);
    }
}
