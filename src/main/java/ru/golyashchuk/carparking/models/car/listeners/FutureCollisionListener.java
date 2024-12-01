package ru.golyashchuk.carparking.models.car.listeners;

import ru.golyashchuk.carparking.models.car.PhysicCar;

public interface FutureCollisionListener extends Listener {
    boolean willCollision(PhysicCar car);
    boolean notWillCollision(PhysicCar car);
}
