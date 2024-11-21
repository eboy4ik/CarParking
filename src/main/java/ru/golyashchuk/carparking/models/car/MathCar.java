package ru.golyashchuk.carparking.models.car;


import javafx.geometry.Point2D;

public class MathCar {
    // center of the car
    double x;
    double y;

    double carOrientation; // radians
    double axleBase = 62; // length between front and roar wheel axles
    double distanceWheels = 43; // length between wheels
    double wheelsOrientation; // regarding carOrientation (radians)
    double speed; // linear speed of the car

    public MathCar() {
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getCarOrientation() {
        return carOrientation;
    }

    public void setCarOrientation(double carOrientation) {
        this.carOrientation = carOrientation;
    }

    public double getAxleBase() {
        return axleBase;
    }

    public void setAxleBase(double axleBase) {
        this.axleBase = axleBase;
    }

    public double getDistanceWheels() {
        return distanceWheels;
    }

    public void setDistanceWheels(double distanceWheels) {
        this.distanceWheels = distanceWheels;
    }

    public double getWheelsOrientation() {
        return wheelsOrientation;
    }

    public void setWheelsOrientation(double wheelsOrientation) {
        this.wheelsOrientation = wheelsOrientation;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void updateCarPosition(double time) {
        if (Math.abs(wheelsOrientation) <= 0.001) {
            moveForward(time);
            return;
        }

        moveAndTurn(time);
    }

    private void moveAndTurn(double time) {
        double rCenter = calculateRadiusCenterCar();
        double w = calculateRotationalSpeed();
        double dopAlpha = Math.asin(axleBase / 2 / rCenter);
        Point2D circleCenterPoint = calculateCircleCenter();
        carOrientation += w * time;
        x = circleCenterPoint.getX() + rCenter * Math.sin(carOrientation + dopAlpha);
        y = circleCenterPoint.getY() - rCenter * Math.cos(carOrientation + dopAlpha);
    }

    private void moveForward(double time) {
        x += speed * Math.cos(carOrientation) * time;
        y += speed * Math.sin(carOrientation) * time;

    }

    private double calculateRotationalSpeed() {
        return speed / calculateRadiusFrontWheel();
    }

    private double calculateRadiusFrontWheel() {
        return axleBase / Math.sin(wheelsOrientation);
    }

    private double calculateRadiusCenterCar() {
        return Math.sqrt(Math.pow(distanceWheels / 2 + axleBase / Math.tan(Math.abs(wheelsOrientation)), 2) + Math.pow(axleBase / 2, 2));
    }

    private Point2D calculateCircleCenter() {
        double xcCar = distanceWheels / 2 + axleBase / Math.abs(Math.tan(wheelsOrientation));
        if (wheelsOrientation < 0) {
            xcCar = -xcCar;
        }

        double ycCar = -axleBase / 2;

        double xc = -xcCar * Math.sin(carOrientation) + ycCar * Math.cos(carOrientation) + x;
        double yc = xcCar * Math.cos(carOrientation) + ycCar * Math.sin(carOrientation) + y;

        return new Point2D(xc, yc);
    }
}
