package ru.golyashchuk.carparking.models.car;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Wheel {
    private Rectangle wheelModel;

    public Rectangle getWheelModel() {
        return wheelModel;
    }

    public Wheel(double x, double y, int length, int width) {
        this.wheelModel = new Rectangle(x, y, length, width);
        this.wheelModel.setFill(Color.BLACK);
        this.setPosition(x, y);
    }

    public void setPosition(double x, double y) {
        this.wheelModel.setX(x - wheelModel.getWidth() / 2);
        this.wheelModel.setY(y - wheelModel.getHeight() / 2);
    }

    public double getCenterX() {
        return wheelModel.getX() + wheelModel.getWidth() / 2;
    }

    public double getCenterY() {
        return wheelModel.getY();
    }

    public void rotate(double angle) {
        this.wheelModel.setRotate(angle);
    }
}
