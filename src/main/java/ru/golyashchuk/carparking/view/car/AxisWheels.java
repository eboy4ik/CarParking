package ru.golyashchuk.carparking.view.car;

import javafx.scene.Group;
import ru.golyashchuk.carparking.view.View;

public class AxisWheels implements View {
    private Group view;
    private double distanceWheels;
    private Wheel leftWheel;
    private Wheel rightWheel;

    public AxisWheels(double x, double y, double distanceWheels) {
        double r = distanceWheels / 2;
        this.distanceWheels = distanceWheels;
        this.leftWheel = new Wheel(x, y - r, 20, 5);
        this.rightWheel = new Wheel(x, y + r, 20, 5);
        this.view = new Group();
        this.view.getChildren().add(leftWheel.getView());
        this.view.getChildren().add(rightWheel.getView());
    }

    public double getDistanceWheels() {
        return distanceWheels;
    }

    public Group getView() {
        return view;
    }

    public double getXCenterAxisWheels() {
        return (rightWheel.getCenterX() + leftWheel.getCenterX()) / 2;
    }

    public double getYCenterAxisWheels() {
        return (rightWheel.getCenterY() + leftWheel.getCenterY()) / 2;
    }


    public void rotateWheels(double angle) {
        leftWheel.rotate(angle);
        rightWheel.rotate(angle);
    }
}
