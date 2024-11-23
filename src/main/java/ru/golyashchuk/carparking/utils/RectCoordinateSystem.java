package ru.golyashchuk.carparking.utils;

import javafx.geometry.Point2D;

public class RectCoordinateSystem {
    private double relativeX;
    private double relativeY;
    private double angleRadians;

    public RectCoordinateSystem(double relativeX, double relativeY, double angle) {
        this.relativeX = relativeX;
        this.relativeY = relativeY;
        this.angleRadians = angle;
    }

    public Point2D getAnotherRCSPoint(double x, double y) {
        double xc = x * Math.cos(angleRadians) + y * Math.sin(angleRadians) + relativeX;
        double yc = x * Math.sin(angleRadians) - y * Math.cos(angleRadians) + relativeY;
        return new Point2D(xc, yc);
    }
}
