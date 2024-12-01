package ru.golyashchuk.carparking.models.car;


public class Car extends PhysicCar {
    private double maxSteeringAngle = 30; // в градусах
    private double maxSpeed = 200; // в пикселях

    public Car(double x, double y, double orientation) {
        super();
        setX(x);
        setY(y);
        setCarOrientation(Math.toRadians(orientation));
    }


    @Override
    public void updateCarPosition(double t) {
        super.updateCarPosition(t);
    }

    public void move(double t) {
        updateCarPosition(t);
    }

    public void forward() {
        setSpeed(this.maxSpeed);
    }

    public void back() {
        setSpeed(-this.maxSpeed);
    }

    public void stop() {
        setSpeed(0);
    }

    public void turnLeft() {
        setWheelsOrientation(Math.toRadians(-maxSteeringAngle));
    }

    public void turnRight() {
        setWheelsOrientation(Math.toRadians(maxSteeringAngle));
    }

    public void straightWheels() {
        setWheelsOrientation(0);
    }
}
