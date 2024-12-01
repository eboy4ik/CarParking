package ru.golyashchuk.carparking.models.car.listeners;

import ru.golyashchuk.carparking.models.car.PhysicCar;

public interface CollisionListener extends Listener {
    void enterCollision(PhysicCar car);

    void outCollision(PhysicCar car);

}
