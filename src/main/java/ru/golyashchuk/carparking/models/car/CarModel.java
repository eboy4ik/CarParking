package ru.golyashchuk.carparking.models.car;

import javafx.scene.Group;
import javafx.scene.image.ImageView;


public class CarModel {
    private Group carModel;
    private ImageView body;
    private Transmission transmission;


    public CarModel() {
    }

    public void rebuild() {
        this.carModel = new Group();
        carModel.getChildren().add(transmission.getModel());
        carModel.getChildren().add(body);
    }

    public Group getCarModel() {
        return carModel;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public ImageView getBody() {
        return body;
    }

    public void setBody(ImageView body) {
        this.body = body;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setPosition(double x, double y) {
        carModel.setLayoutX(x);
        carModel.setLayoutY(y);
    }

    public void rotate(double angle) {
        carModel.setRotate(angle);
    }

    public void turn(double steeringAngle) {
        transmission.rotate(steeringAngle);
    }

    public double getTransmissionLength() {
        return transmission.getLength();
    }
}
