package ru.golyashchuk.carparking.controllers;

import javafx.animation.AnimationTimer;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import ru.golyashchuk.carparking.models.car.Car;
import ru.golyashchuk.carparking.models.car.CarEnum;

import java.util.HashSet;
import java.util.Set;

public class GameController {
    private Car focusedCar;
    private final Set<KeyCode> pressedKeys = new HashSet<>();

    @FXML
    private Pane gamePane;

//    @FXML
//    private Button pauseButton;

//    @FXML
//    void onPause(ActionEvent event) {
//        // Оставьте пустым или добавьте логику для паузы
//    }

    @FXML
    void onKeyPressed(KeyEvent event) {
        pressedKeys.add(event.getCode());
    }

    @FXML
    void onKeyReleased(KeyEvent event) {
        pressedKeys.remove(event.getCode());
    }

    @FXML
    public void initialize() {
//        gamePane.getScene().getRoot().requestFocus();

        Car car = new Car(500, 500, 0);
        car.setCarModel(CarEnum.YELLOWCAR.getCarModel());
        this.gamePane.getChildren().add(car.getBounds());

        this.gamePane.getChildren().add(car.getCarModel().getCarModel());
        focusedCar = car;

        Car car2 = new Car(800, 500, 0);
        car2.setCarModel(CarEnum.YELLOWCAR.getCarModel());
        this.gamePane.getChildren().add(car2.getBounds());
        this.gamePane.getChildren().add(car2.getCarModel().getCarModel());


//        while (true) {
//            updateCarMovement();
//        }
        final long startNanoTime = System.nanoTime();
        long prevNanoTime = startNanoTime;
        final LongProperty lastUpdateTime = new SimpleLongProperty();
        focusedCar.updateCarPosition(0);
        car2.updateCarPosition(0);
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                long t = now - lastUpdateTime.get();
                updateCarMovement((double) t / 1000000000);
//                updateCarMovement();
//                System.out.println(focusedCar.getCarModel().getCarModel().getBoundsInParent());
                if (checkCollistion(car, car2)) {
                    System.out.println("COLLISTION");
                }
                lastUpdateTime.set(now);
            }

        };
        timer.start();
    }

    private boolean checkCollistion(Car car, Car car2) {
        Shape shape = Shape.intersect(car.getBounds(), car2.getBounds());
        return shape.getBoundsInParent().getWidth() > 0;
    }

    private void updateCarMovement(double t) {
        if (pressedKeys.contains(KeyCode.W)) {
            focusedCar.moveForward(t);
        }
        if (pressedKeys.contains(KeyCode.S)) {
            focusedCar.moveBackward(t);
        }
        if (pressedKeys.contains(KeyCode.A)) {
            focusedCar.turnLeft();
        }
        if (pressedKeys.contains(KeyCode.D)) {
            focusedCar.turnRight();
        }
        // Если ни "A" ни "D" не нажаты, выравниваем колеса
        if (!pressedKeys.contains(KeyCode.A) && !pressedKeys.contains(KeyCode.D)) {
            focusedCar.straightWheels();
        }
    }
}