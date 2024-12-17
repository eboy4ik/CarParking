package ru.golyashchuk.carparking.models;

import javafx.scene.shape.Rectangle;
import ru.golyashchuk.carparking.utils.Serializator;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Collision implements Serializable, ru.golyashchuk.carparking.models.car.Collisional, Model {
    private transient Rectangle collision;

    public Collision(Rectangle collision) {
        this.collision = collision;
    }

    @Override
    public Rectangle getCollision() {
        return collision;
    }

    public void setCollision(Rectangle collision) {
        this.collision = collision;
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        Serializator.serializeRectangle(collision, oos);
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        collision = Serializator.deserializeRectangle(ois);
    }
}
