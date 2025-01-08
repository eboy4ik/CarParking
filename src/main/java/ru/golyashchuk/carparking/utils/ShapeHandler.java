package ru.golyashchuk.carparking.utils;

import javafx.scene.shape.Rectangle;

public class ShapeHandler {
    private ShapeHandler() {
    }

    public static Rectangle copyRectangle(Rectangle rectangle) {
        Rectangle res = new Rectangle(rectangle.getWidth(), rectangle.getHeight());
        res.setX(rectangle.getX());
        res.setY(rectangle.getY());
        res.setArcHeight(rectangle.getArcHeight());
        res.setArcWidth(rectangle.getArcWidth());
        res.setRotate(rectangle.getRotate());

        return res;
    }
}
