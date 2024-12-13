package ru.golyashchuk.carparking.models;

import javafx.scene.shape.Rectangle;
import ru.golyashchuk.carparking.models.car.Collisional;

public class Collision implements Collisional, Model {
    private Rectangle collision;

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
}
