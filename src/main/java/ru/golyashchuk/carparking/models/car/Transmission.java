package ru.golyashchuk.carparking.models.car;

import javafx.geometry.Point2D;
import javafx.scene.Group;

public class Transmission {
    private Group model;
    private AxisWheels frontAxisWheels;
    private AxisWheels rearAxisWheels;

    public Transmission() {
    }

    public void rebuild() {
        model = new Group();
        model.getChildren().add(frontAxisWheels.getAxisModel());
        model.getChildren().add(rearAxisWheels.getAxisModel());
    }

    public Group getModel() {
        return model;
    }

    public AxisWheels getFrontAxisWheels() {
        return frontAxisWheels;
    }

    public void setFrontAxisWheels(AxisWheels frontAxisWheels) {
        this.frontAxisWheels = frontAxisWheels;
    }

    public AxisWheels getRearAxisWheels() {
        return rearAxisWheels;
    }

    public void setRearAxisWheels(AxisWheels rearAxisWheels) {
        this.rearAxisWheels = rearAxisWheels;
    }

    public double getLength() {
        Point2D pointFront = new Point2D(frontAxisWheels.getXCenterAxisWheels(), frontAxisWheels.getYCenterAxisWheels());
        Point2D pointRear = new Point2D(rearAxisWheels.getXCenterAxisWheels(), rearAxisWheels.getYCenterAxisWheels());
        return pointFront.distance(pointRear);
    }

    public double getWidth() {
        return this.frontAxisWheels.getDistanceWheels();
    }

    public void rotate(double steeringAngle) {
        frontAxisWheels.rotateWheels(steeringAngle);
    }
}
