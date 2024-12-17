package ru.golyashchuk.carparking.models.car;


import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import ru.golyashchuk.carparking.utils.Beam;
import ru.golyashchuk.carparking.utils.Serializator;
import ru.golyashchuk.carparking.utils.ShapeHandler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


public class PhysicCar extends MathCar implements Serializable, Collisional {
    public static PhysicCar DEFAULT_PHYSICCAR = new PhysicCar(100, 40, MathCar.DEFAULT_MATHCAR_BUILDER);
    //    private double offsetBody = -100; // regarding center of the car
    private transient Rectangle bounds;
    private transient Rectangle futureBounds;

    public PhysicCar() {
        super(MathCar.DEFAULT_MATHCAR_BUILDER);
        bounds = new Rectangle(PhysicCar.DEFAULT_PHYSICCAR.bounds.getWidth(), PhysicCar.DEFAULT_PHYSICCAR.bounds.getHeight());
        bounds.setArcHeight(30);
        bounds.setArcWidth(30);
        updateBounds(getCurrentCoordinates());
        futureBounds = ShapeHandler.copyRectangle(bounds);
        bounds.setFill(Color.rgb(0, 255, 255));
    }

    public PhysicCar(double length, double width, MathCarBuilder mathCarBuilder) {
        super(mathCarBuilder);
        bounds = new Rectangle(length, width);
        updateBounds(getCurrentCoordinates());
    }

    @Override
    public void setX(double x) {
        super.setX(x);
        updateBounds(getCurrentCoordinates());
    }

    @Override
    public void setY(double y) {
        super.setY(y);
        updateBounds(getCurrentCoordinates());
    }

    @Override
    public void setCarOrientation(double carOrientation) {
        super.setCarOrientation(carOrientation);
        updateBounds(getCurrentCoordinates());
    }

    public Rectangle getBounds() {
        return bounds;
    }

    @Override
    public Rectangle getCollision() {
        return bounds;
    }

    @Override
    public void updateCarPosition(double time) {
        Beam beam = getNewCoordinates(time);
        updateBounds(beam);
        super.updateCarPosition(time);
    }

    public Rectangle getFutureBounds(double time) {
        updateFutureBounds(getNewCoordinates(time));
        return futureBounds;
    }

    public boolean willCollision(Collisional object, double time) {
        updateBounds(getNewCoordinates(time));
        Shape shape = Shape.intersect(this.bounds, object.getCollision());
        boolean result = shape.getBoundsInLocal().getWidth() > 0;
        updateBounds(getCurrentCoordinates());
        return result;
    }

    public boolean checkCollision(PhysicCar otherCar) {
        Shape shape = Shape.intersect(this.getBounds(), otherCar.getBounds());
        return shape.getBoundsInParent().getWidth() > 0;
    }

    private void updateBounds(Beam beam) {
        updateRectangle(bounds, beam);
    }

    private void updateFutureBounds(Beam beam) {
        updateRectangle(futureBounds, beam);
    }

    private void updateRectangle(Rectangle rect, Beam beam) {
        rect.setRotate(Math.toDegrees(beam.getOrientation()));
        rect.setX(beam.getX());
        rect.setY(beam.getY());
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        Serializator.serializeRectangle(bounds, oos);
        Serializator.serializeRectangle(futureBounds, oos);
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        bounds = Serializator.deserializeRectangle(ois);
        futureBounds = Serializator.deserializeRectangle(ois);
    }
}
