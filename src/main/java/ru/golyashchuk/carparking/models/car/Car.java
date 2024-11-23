package ru.golyashchuk.carparking.models.car;

import javafx.scene.Group;

public class Car extends MathCar {
//    private double x, y;
//    private double orientation; // в градусах
//    private double currentSteeringAngle; // в градусах
//    private double speed; // в пикселях
    private double maxSteeringAngle = 30; // в градусах
    private double maxSpeed = 500; // в пикселях

    private CarModel carModel;

    public Car(double x, double y, double orientation) {
        super();
        this.x = x;
        this.y = y;
        this.carOrientation = Math.toRadians(orientation);
    }

    public CarModel getCarModel() {
        return carModel;
    }

    public void setCarModel(CarModel carModel) {
        this.carModel = carModel;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @Override
    public void updateCarPosition(double t) {
        super.updateCarPosition(t);

        System.out.println(x + " " + y);

        carModel.setPosition(x, y);
        carModel.rotate(Math.toDegrees(carOrientation));
    }

    public void move(double t) {
        carModel.turn(Math.toDegrees(wheelsOrientation));
        updateCarPosition(t);
    }

    public void moveForward(double t) {
        this.speed = this.maxSpeed;
        move(t);
    }

    public void moveBackward(double t) {
        this.speed = -this.maxSpeed;
        move(t);
    }

    public void turnLeft() {
        wheelsOrientation = Math.toRadians(-maxSteeringAngle);
        carModel.turn(Math.toDegrees(wheelsOrientation));
    }

    public void turnRight() {
        wheelsOrientation = Math.toRadians(maxSteeringAngle);
        carModel.turn(Math.toDegrees(wheelsOrientation));
    }

    public void straightWheels() {
        wheelsOrientation = 0;
        carModel.turn(Math.toDegrees(wheelsOrientation));
    }
}
