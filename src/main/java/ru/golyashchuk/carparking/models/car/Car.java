package ru.golyashchuk.carparking.models.car;


public class Car extends PhysicCar {
    private double maxSteeringAngle = 30; // в градусах
    private double maxSpeed = 200; // в пикселях

    private CarModel carModel;

//    public static Car DEFAULT_CAR = new

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


        carModel.setPosition(x, y);
        carModel.rotate(Math.toDegrees(carOrientation));
    }

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
