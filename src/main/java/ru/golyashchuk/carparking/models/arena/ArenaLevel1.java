package ru.golyashchuk.carparking.models.arena;

import javafx.scene.shape.Rectangle;
import ru.golyashchuk.carparking.models.car.Car;

public class ArenaLevel1 extends Arena {

    public ArenaLevel1() {
        Car car = new Car(500, 200, 0);
        Car car2 = new Car(700, 200, 0);
        Car car3 = new Car(500, 400, 0);

        setWidth(1000);
        setHeight(500);


//        --- FINISH
        Rectangle finish = new Rectangle(50, 100, 70, 120);
        setFinish(finish);
        addCar(car);
        addCar(car2);
        addCar(car3);
        setMainCar(car);
        setFocusedCar(car);
    }
}
