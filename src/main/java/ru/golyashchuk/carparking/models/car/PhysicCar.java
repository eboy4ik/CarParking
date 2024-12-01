package ru.golyashchuk.carparking.models.car;


import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import ru.golyashchuk.carparking.models.car.listeners.CollisionListener;
import ru.golyashchuk.carparking.models.car.listeners.FutureCollisionListener;
import ru.golyashchuk.carparking.utils.Beam;

import java.util.LinkedList;
import java.util.List;

public class PhysicCar extends MathCar implements Collisional {
    public static PhysicCar DEFAULT_PHYSICCAR = new PhysicCar(100, 45, MathCar.DEFAULT_MATHCAR_BUILDER);
    private Rectangle bounds;
    private Rectangle futureBounds;

    private List<CollisionListener> collisionListeners = new LinkedList<>();
    private List<FutureCollisionListener> futureCollisionListeners = new LinkedList<>();
    private boolean inCollision = false;

    public PhysicCar() {
        super(MathCar.DEFAULT_MATHCAR_BUILDER);
        bounds = new Rectangle(PhysicCar.DEFAULT_PHYSICCAR.bounds.getWidth(), PhysicCar.DEFAULT_PHYSICCAR.bounds.getHeight());
        updateBounds(getCurrentCoordinates());
        futureBounds = new Rectangle(bounds.getWidth(), bounds.getHeight());
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

    public void addFutureCollisionListener(FutureCollisionListener listener) {
        futureCollisionListeners.add(listener);
    }

    public void addCollisionListener(CollisionListener collisionListener) {
        collisionListeners.add(collisionListener);
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
        boolean result = shape.getBoundsInParent().getWidth() > 0;
        if (result != inCollision) {
            inCollision = result;
            notifyCollisionListeners();
        }
        return result;
    }

    private void notifyCollisionListeners() {
        if (inCollision) {
            for (CollisionListener collisionListener : collisionListeners) {
                collisionListener.enterCollision(this);
            }
            return;
        }

        for (CollisionListener collisionListener : collisionListeners) {
            collisionListener.outCollision(this);
        }
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
}
