package ru.golyashchuk.carparking.view.car;


import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import ru.golyashchuk.carparking.models.Model;
import ru.golyashchuk.carparking.models.car.Car;
import ru.golyashchuk.carparking.utils.ShapeHandler;
import ru.golyashchuk.carparking.view.Renderer;
import ru.golyashchuk.carparking.view.View;
import ru.golyashchuk.carparking.view.arena.Focusable;


public class CarView implements Renderer, View, Focusable {
    private Rectangle bounds;
    private Group view;
    private Image focusedCar;
    private Image defaultCar;
    private Image mainCar;
    private ImageView body;
    private Transmission transmission;


    public CarView(Car car) {
        bounds = ShapeHandler.copyRectangle(car.getBounds());
        bounds.setX(0);
        bounds.setY(0);
        bounds.setRotate(0);
        bounds.setFill(Color.BLUE);
    }


    public void rebuild() {
        this.view = new Group();
        view.getChildren().add(bounds);
        view.getChildren().add(transmission.getView());
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
        defaultCar = mainCar;
    }

    @Override
    public void render(Model model) {
        if (!(model instanceof Car)) {
            throw new IllegalArgumentException("model isn't Car");
        }
        Car car = (Car) model;
        turn(Math.toDegrees(car.getWheelsOrientation()));
        rotate(Math.toDegrees(car.getCarOrientation()));
        setPosition(car.getX(), car.getY());
    }

    public void renderCar(Car car) {
        turn(Math.toDegrees(car.getWheelsOrientation()));
        rotate(Math.toDegrees(car.getCarOrientation()));
        setPosition(car.getX(), car.getY());
    }


}
