package ru.golyashchuk.carparking.models.car;


import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import ru.golyashchuk.carparking.utils.Beam;

public class PhysicCar extends MathCar {
    public static PhysicCar DEFAULT_PHYSICCAR = new PhysicCar(100, 45, MathCar.DEFAULT_MATHCAR_BUILDER);
    private Rectangle bounds;

    public Rectangle getBounds() {
        return bounds;
    }

    public PhysicCar() {
        super(MathCar.DEFAULT_MATHCAR_BUILDER);
        bounds = new Rectangle(PhysicCar.DEFAULT_PHYSICCAR.bounds.getWidth(), PhysicCar.DEFAULT_PHYSICCAR.bounds.getHeight());
        bounds.setFill(Color.rgb(0, 255, 255));
    }

    public PhysicCar(double length, double width, MathCarBuilder mathCarBuilder) {
        super(mathCarBuilder);
        bounds = new Rectangle(length, width);
    }

    @Override
    public void updateCarPosition(double time) {
        Beam beam = getNewCoordinates(time);
        updateBounds(beam);
        super.updateCarPosition(time);
    }

    private void updateBounds(Beam beam) {
        bounds.setRotate(Math.toDegrees(beam.getOrientation()));
        bounds.setX(x);
        bounds.setY(y);
    }

    private void setBoundsCenter(double x, double y) {

    }
}
