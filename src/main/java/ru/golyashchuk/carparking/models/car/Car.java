package ru.golyashchuk.carparking.models.car;


import ru.golyashchuk.carparking.models.Model;

import java.io.Serializable;

public class Car extends PhysicCar implements Serializable, Model {
    private double maxSteeringAngle = 30; // в градусах
    private double maxSpeed = 300; // в пикселях
    private double a = 200;
    private double aTormoz = 400;

    public Car(double x, double y, double orientation) {
        super();
        setX(x);
        setY(y);
        setCarOrientation(Math.toRadians(orientation));
    }


    public void move(double t) {
        updateCarPosition(t);
    }

    public void forward(double t) {
        if (getSpeed() < 0) {
            setSpeed(getSpeed() + aTormoz * t);
            return;
        }
        setSpeed(Math.min(this.maxSpeed, getSpeed() + a * t));
    }

    public void back(double t) {
        if (getSpeed() > 0) {
            setSpeed(getSpeed() - aTormoz * t);
            return;
        }
        setSpeed(Math.max(-this.maxSpeed, getSpeed() - a * t));
    }

    public void slowly(double t) {
        if (getSpeed() > 0) {
            setSpeed(Math.max(0, getSpeed() - a * t));
            return;
        }
        if (getSpeed() < 0) {
            setSpeed(Math.min(0, getSpeed() + a * t));
        }
    }

    public void stop() {
        setSpeed(0);
    }

    public void brake(double t) {
        if (getSpeed() > 0) {
            setSpeed(Math.max(0, getSpeed() - aTormoz * t));
        } else {
            setSpeed(Math.min(0, getSpeed() + aTormoz * t));
        }
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
