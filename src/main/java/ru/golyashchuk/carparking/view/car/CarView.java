package ru.golyashchuk.carparking.view.car;


import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import ru.golyashchuk.carparking.models.car.Car;
import ru.golyashchuk.carparking.utils.ShapeHandler;
import ru.golyashchuk.carparking.view.Renderer;
import ru.golyashchuk.carparking.view.View;
import ru.golyashchuk.carparking.view.arena.Focusable;


public class CarView implements Renderer, View, Focusable {
    public final static int OUTLINE_WIDTH = 2;
    public final static int OUTLINE_OFFSET = 10;
    //    private final Car car;
    private Rectangle outline;
    private Rectangle bounds;
    private Group view;
    private Image focusedCar;
    private Image defaultCar;
    private Image mainCar;
    private ImageView body;
    private Transmission transmission;


    public CarView(Car car) {
        initializeOutline(car);
        bounds = ShapeHandler.copyRectangle(car.getBounds());
        bounds.setX(0);
        bounds.setY(0);
        bounds.setFill(Color.BLUE);
    }


    public void rebuild() {
        this.view = new Group();
        view.getChildren().add(bounds);
        view.getChildren().add(transmission.getView());
        view.getChildren().add(outline);
        view.getChildren().add(body);
    }

    @Override
    public Group getView() {
        return view;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }


    public void setBody(ImageView body) {
        this.body = body;
    }

    public void setFocusedCar(Image focusedCar) {
        this.focusedCar = focusedCar;
    }

    public void setDefaultCar(Image defaultCar) {
        this.defaultCar = defaultCar;
    }

    public void setMainCar(Image mainCar) {
        this.mainCar = mainCar;
    }

    public void setPosition(double x, double y) {
        view.setLayoutX(x);
        view.setLayoutY(y);
    }

    public void rotate(double angle) {
        view.setRotate(angle);
    }

    public void turn(double steeringAngle) {
        transmission.rotate(steeringAngle);
    }

    @Override
    public void focus() {
        body.setImage(focusedCar);
    }

    @Override
    public void unfocus() {
        body.setImage(defaultCar);
    }

    public void mainCar() {
        outline.setStroke(Color.BLUE);
    }

    @Override
    public void render() {
//        turn(Math.toDegrees(car.getWheelsOrientation()));
//        rotate(Math.toDegrees(car.getCarOrientation()));
//        setPosition(car.getX(), car.getY());
    }

    public void renderCar(Car car) {
        turn(Math.toDegrees(car.getWheelsOrientation()));
        rotate(Math.toDegrees(car.getCarOrientation()));
        setPosition(car.getX(), car.getY());
    }

    private void initializeOutline(Car car) {
        outline = new Rectangle(car.getBounds().getWidth() + OUTLINE_OFFSET * 2, car.getBounds().getHeight() + OUTLINE_OFFSET * 2);
        outline.setArcWidth(50);
        outline.setArcHeight(50);
        outline.setX(-OUTLINE_OFFSET);
        outline.setY(-OUTLINE_OFFSET);
        outline.setStrokeWidth(OUTLINE_WIDTH);
        outline.setFill(Color.TRANSPARENT);
    }
}
