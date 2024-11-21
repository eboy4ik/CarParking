package ru.golyashchuk.carparking.models.car;

import javafx.scene.Group;

public class AxisWheels {
    private Group axisModel;
    private double distanceWheels;
    private Wheel leftWheel;
    private Wheel rightWheel;
    private int angleRotation;

    public AxisWheels(double x, double y, double distanceWheels) {
        double r = distanceWheels / 2;
        this.distanceWheels = distanceWheels;
        this.leftWheel = new Wheel(x, y - r, 20, 5);
        this.rightWheel = new Wheel(x, y + r, 20, 5);
        this.axisModel = new Group();
        this.axisModel.getChildren().add(leftWheel.getWheelModel());
        this.axisModel.getChildren().add(rightWheel.getWheelModel());
    }

    public double getDistanceWheels() {
        return distanceWheels;
    }

    public Group getAxisModel() {
        return axisModel;
    }

    public double getXCenterAxisWheels() {
        return (rightWheel.getCenterX() + leftWheel.getCenterX()) / 2;
    }

    public double getYCenterAxisWheels() {
        return (rightWheel.getCenterY() + leftWheel.getCenterY()) / 2;
    }

    public Wheel getLeftWheel() {
        return leftWheel;
    }

    public Wheel getRightWheel() {
        return rightWheel;
    }

    public void rotateWheels(double angle) {
        leftWheel.rotate(angle);
        rightWheel.rotate(angle);
    }
}
