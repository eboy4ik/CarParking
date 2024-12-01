package ru.golyashchuk.carparking.view.car;

import ru.golyashchuk.carparking.models.car.Car;

public interface ICar {
    public CarView getCarModel(Car car);
}
