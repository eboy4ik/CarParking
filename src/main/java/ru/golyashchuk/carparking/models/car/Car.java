package ru.golyashchuk.carparking.models.car;

import javafx.scene.Group;

public class Car extends MathCar {
//    private double x, y;
//    private double orientation; // в градусах
//    private double currentSteeringAngle; // в градусах
//    private double speed; // в пикселях
    private double maxSteeringAngle = 30; // в градусах
    private double maxSpeed = 500; // в пикселях

    private CarModel carModel;

    public Car(double x, double y, double orientation) {
        super();
        this.x = x;
        this.y = y;
        this.carOrientation = Math.toRadians(orientation);
    }

    public CarModel getCarModel() {
        return carModel;
    }

    public void setCarModel(CarModel carModel) {
        this.carModel = carModel;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @Override
    public void updateCarPosition(double t) {
        super.updateCarPosition(t);

        System.out.println(x + " " + y);

        carModel.setPosition(x, y);
        carModel.rotate(Math.toDegrees(carOrientation));
    }
//    @Override
//    public void updateCarPosition(double t) {
//        double currentSteeringAngle = Math.toDegrees(wheelsOrientation);
//        double orientation = Math.toDegrees(carOrientation);
//        double aplha = Math.toRadians(currentSteeringAngle);
//        double orientationRadians = Math.toRadians(orientation);
//        double x0 = getX();
//        double y0 = getY();
//
//        double a = currentSteeringAngle;
//        double aRadians = Math.toRadians(a);
//        double b = 360 - orientation;
//        double bRadians = Math.toRadians(b);
//        double width = carModel.getTransmission().getWidth();
//        double l = carModel.getTransmission().getLength();
//        double rf = Math.abs(l / Math.sin(aRadians));
//        if (Math.abs(currentSteeringAngle) <= 0.1) {
//            x += speed * Math.cos(orientationRadians) * t;
//            y += speed * Math.sin(orientationRadians) * t;
//            carModel.setPosition(x, y);
//            double xd = x0 - (l / 2) * Math.cos(bRadians);
//            double yd = y0 + (l / 2) * Math.sin(bRadians);
//            System.out.println("x0 = " + x0 + " | y0 = " + y0);
//            return;
//        }
//        double phiRadians = Math.atan(carModel.getTransmissionLength() / carModel.getTransmission().getWidth());
//
//        double ac = carModel.getTransmission().getWidth() / Math.cos(phiRadians);
//        double aq = ac / 2;
//
//        double qaoRadians = Math.PI - phiRadians - Math.abs(Math.toRadians(a));
//        double r0 = Math.abs(Math.sqrt(Math.pow(rf, 2) + Math.pow(aq, 2) - 2 * aq * rf * Math.cos(qaoRadians)));
//        System.out.println("r0 = " + r0);
//        double r01 = Math.sqrt(Math.pow(width / 2 + l / Math.tan(Math.abs(aRadians)), 2) + Math.pow(l / 2, 2));
//
//        System.out.println("r0 = " + r0);
////        System.out.println("xd = " + xd + " | yd = " + yd);
//        double xcCar = width / 2 + l / Math.tan(aRadians);
//        double ycCar = -l / 2;
////        System.out.println("L = " + l);
////        System.out.println("W = " + width);
//        double beta = Math.toRadians(orientation) - 3 * Math.PI / 2; // = Math.PI / 2 - Math.PI * 2 + orientation - 3 / 2 * Math.PI()
////        double beta = Math.toRadians(b);
//        double xc = xcCar * Math.cos(beta) + ycCar * Math.sin(beta) + x;
//        double yc = xcCar * Math.sin(beta) - ycCar * Math.cos(beta) + y;
//        double w = speed / rf;
//        double dopAlpha = Math.asin(l / 2 / r0);
//        double newX = xc + r0 * Math.sin(orientationRadians + w * t + dopAlpha);
//        double newY = yc - r0 * Math.cos(orientationRadians + w * t + dopAlpha);
//        System.out.println("xc = " + xc + " | yc = " + yc);
//
//
//        x = newX;
//        y = newY;
//
//        double newOrientation = Math.toRadians(orientation) + w * t;
//        carOrientation = newOrientation;
//        System.out.println("wo=" + carOrientation);
//
//        carModel.setPosition(newX, newY);
//        carModel.rotate(Math.toDegrees(carOrientation));
//
//        System.out.println("w = " + w);
//        System.out.println("r0 = " + r0);
//        System.out.println("xc = " + xc);
//        System.out.println("yc = " + yc);
//    }
    public void move(double t) {
        carModel.turn(Math.toDegrees(wheelsOrientation));
        updateCarPosition(t);
    }

    public void moveForward(double t) {
        this.speed = this.maxSpeed;
        move(t);
    }

    public void moveBackward(double t) {
        this.speed = -this.maxSpeed;
        move(t);
    }

    public void turnLeft() {
        wheelsOrientation = Math.toRadians(-maxSteeringAngle);
        carModel.turn(Math.toDegrees(wheelsOrientation));
    }

    public void turnRight() {
        wheelsOrientation = Math.toRadians(maxSteeringAngle);
        carModel.turn(Math.toDegrees(wheelsOrientation));
    }

    public void straightWheels() {
        wheelsOrientation = 0;
        carModel.turn(Math.toDegrees(wheelsOrientation));
    }
}
