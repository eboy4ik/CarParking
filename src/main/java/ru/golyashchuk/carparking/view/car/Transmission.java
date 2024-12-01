package ru.golyashchuk.carparking.view.car;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import ru.golyashchuk.carparking.view.View;

public class Transmission implements View {
    private Group view;
    private AxisWheels frontAxisWheels;
    private AxisWheels rearAxisWheels;

    public Transmission() {
    }

    public void rebuild() {
        view = new Group();
        view.getChildren().add(frontAxisWheels.getView());
        view.getChildren().add(rearAxisWheels.getView());
    }

    public Group getView() {
        return view;
    }

    public void setFrontAxisWheels(AxisWheels frontAxisWheels) {
        this.frontAxisWheels = frontAxisWheels;
    }


    public void setRearAxisWheels(AxisWheels rearAxisWheels) {
        this.rearAxisWheels = rearAxisWheels;
    }

    public double getLength() {
        Point2D pointFront = new Point2D(frontAxisWheels.getXCenterAxisWheels(), frontAxisWheels.getYCenterAxisWheels());
        Point2D pointRear = new Point2D(rearAxisWheels.getXCenterAxisWheels(), rearAxisWheels.getYCenterAxisWheels());
        return pointFront.distance(pointRear);
    }

    public void rotate(double steeringAngle) {
        frontAxisWheels.rotateWheels(steeringAngle);
    }
}
