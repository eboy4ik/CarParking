package ru.golyashchuk.carparking.view.car;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import ru.golyashchuk.carparking.view.View;

public class Wheel implements View {
    private Rectangle view;

    public Rectangle getView() {
        return view;
    }

    public Wheel(double x, double y, int length, int width) {
        this.view = new Rectangle(x, y, length, width);
        this.view.setFill(Color.BLACK);
        this.setPosition(x, y);
    }

    public void setPosition(double x, double y) {
        this.view.setX(x - view.getWidth() / 2);
        this.view.setY(y - view.getHeight() / 2);
    }

    public double getCenterX() {
        return view.getX() + view.getWidth() / 2;
    }

    public double getCenterY() {
        return view.getY();
    }

    public void rotate(double angle) {
        this.view.setRotate(angle);
    }
}
