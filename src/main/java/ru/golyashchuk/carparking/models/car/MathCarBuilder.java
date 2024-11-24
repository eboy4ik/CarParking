package ru.golyashchuk.carparking.models.car;

public class MathCarBuilder {
    private double x;
    private double y;

    private double carOrientation; // radians
    private double axleBase;// length between front and roar wheel axles
    private double distanceWheels; // length between wheels
    private double wheelsOrientation; // regarding carOrientation (radians)
    private double speed; // linear speed of the car

    public MathCarBuilder() {
        super();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getCarOrientation() {
        return carOrientation;
    }

    public double getAxleBase() {
        return axleBase;
    }

    public double getDistanceWheels() {
        return distanceWheels;
    }

    public double getWheelsOrientation() {
        return wheelsOrientation;
    }

    public double getSpeed() {
        return speed;
    }

    public MathCarBuilder x(double x) {
        this.x = x;
        return this;
    }

    public MathCarBuilder y(double y) {
        this.y = y;
        return this;
    }

    public MathCarBuilder carOrientation(double carOrientation) {
        this.carOrientation = carOrientation;
        return this;
    }

    public MathCarBuilder axleBase(double axleBase) {
        this.axleBase = axleBase;
        return this;
    }

    public MathCarBuilder distanceWheels(double distanceWheels) {
        this.distanceWheels = distanceWheels;
        return this;
    }

    public MathCarBuilder wheelsOrientation(double wheelsOrientation) {
        this.wheelsOrientation = wheelsOrientation;
        return this;
    }

    public MathCarBuilder speed(double speed) {
        this.speed = speed;
        return this;
    }

    public MathCar build() {
        if (validateMathCar()) {
            return new MathCar(this);
        }
        return null;
    }

    private boolean validateMathCar() {
        return (axleBase > 0 && distanceWheels > 0);
    }

}
