package ru.golyashchuk.carparking.models.car;


import javafx.geometry.Point2D;
import ru.golyashchuk.carparking.utils.Beam;
import ru.golyashchuk.carparking.utils.RectCoordinateSystem;

public class MathCar {
    public static final MathCarBuilder DEFAULT_MATHCAR_BUILDER = new MathCarBuilder() {
        {
            axleBase(60).distanceWheels(35);
        }
    };
    public static final MathCar DEFAULT_MATHCAR = new MathCar(DEFAULT_MATHCAR_BUILDER);
    private static final double WHEELS_ORIENTATION_EPSILON = 0.001;
    // center of the car
    private double x;
    private double y;

    private double carOrientation; // radians
    private double axleBase = 63; // length between front and roar wheel axles
    private double distanceWheels = 42; // length between wheels
    private double wheelsOrientation; // regarding carOrientation (radians)
    private double speed; // linear speed of the car


    public MathCar() {
    }

    public MathCar(MathCarBuilder mathCarBuilder) {
        this.x = mathCarBuilder.getX();
        this.y = mathCarBuilder.getY();
        this.carOrientation = mathCarBuilder.getCarOrientation();
        this.axleBase = mathCarBuilder.getAxleBase();
        this.distanceWheels = mathCarBuilder.getDistanceWheels();
        this.wheelsOrientation = mathCarBuilder.getWheelsOrientation();
        this.speed = mathCarBuilder.getSpeed();
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
        moveTo(getNewCoordinates(time));
    }

    public Beam getCurrentCoordinates() {
        return new Beam(x, y, carOrientation);
    }

    public Beam getNewCoordinates(double time) {
        if (Math.abs(wheelsOrientation) <= MathCar.WHEELS_ORIENTATION_EPSILON) {
            return getBeamIfMoveForward(time);
        }
        return getBeamIfMoveAndTurn(time);
    }

    public void moveTo(Beam beam) {
        carOrientation = beam.getOrientation();
        x = beam.getX();
        y = beam.getY();
    }

    private Beam getBeamIfMoveAndTurn(double time) {
        double rCenter = calculateRadiusCenterCar();
        double w = calculateRotationalSpeed();
        double dopAlpha = Math.asin(axleBase / 2 / rCenter);
        Point2D circleCenterPoint = calculateCircleCenter();
        double newOrientation = carOrientation + w * time;
        double newX = circleCenterPoint.getX() + rCenter * Math.sin(newOrientation + dopAlpha);
        double newY = circleCenterPoint.getY() - rCenter * Math.cos(newOrientation + dopAlpha);
        return new Beam(newX, newY, newOrientation);
    }

    private Beam getBeamIfMoveForward(double time) {
        double newX = x + speed * Math.cos(carOrientation) * time;
        double newY = y + speed * Math.sin(carOrientation) * time;
        return new Beam(newX, newY, carOrientation);
    }

    private double calculateRotationalSpeed() {
        return speed / calculateRadiusFrontWheel();
    }

    private double calculateRadiusFrontWheel() {
        return axleBase / Math.sin(wheelsOrientation);
    }

    private double calculateRadiusCenterCar() {
        double r = Math.sqrt(Math.pow(distanceWheels / 2 + axleBase / Math.tan(Math.abs(wheelsOrientation)), 2) + Math.pow(axleBase / 2, 2));
        if (wheelsOrientation < 0) {
            return -r;
        }
        return r;
    }

    private Point2D calculateCircleCenter() {
        double xcCar = distanceWheels / 2 + axleBase / Math.abs(Math.tan(wheelsOrientation));
        double ycCar = -axleBase / 2;

        if (wheelsOrientation < 0) {
            xcCar = -xcCar;

            RectCoordinateSystem rcs = new RectCoordinateSystem(x, y, Math.toRadians(90) + carOrientation);
            return rcs.getAnotherRCSPoint(xcCar, ycCar);
        }
        RectCoordinateSystem rcs = new RectCoordinateSystem(x, y, Math.toRadians(90) + carOrientation);
        return rcs.getAnotherRCSPoint(xcCar, ycCar);
    }
}
