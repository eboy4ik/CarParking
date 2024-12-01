package ru.golyashchuk.carparking.models.car.listeners;

import javafx.scene.paint.Color;
import ru.golyashchuk.carparking.models.car.PhysicCar;

public class RedBoundsCollisionListener implements CollisionListener {

    @Override
    public void enterCollision(PhysicCar car) {
        car.getBounds().setFill(Color.rgb(255, 0, 0));
    }

    @Override
    public void outCollision(PhysicCar car) {
        car.getBounds().setFill(Color.rgb(0, 255, 255, 0));
    }
}
