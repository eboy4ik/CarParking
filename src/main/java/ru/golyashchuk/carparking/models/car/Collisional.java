package ru.golyashchuk.carparking.models.car;

import javafx.scene.shape.Rectangle;
import ru.golyashchuk.carparking.models.Model;


public interface Collisional extends Model {
    Rectangle getCollision();
}
