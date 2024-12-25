package ru.golyashchuk.carparking.view.car;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ru.golyashchuk.carparking.config.ConfigurationManager;
import ru.golyashchuk.carparking.models.car.Car;
import ru.golyashchuk.carparking.view.car.AxisWheels;
import ru.golyashchuk.carparking.view.car.CarView;
import ru.golyashchuk.carparking.view.car.ICar;
import ru.golyashchuk.carparking.view.car.Transmission;

import java.awt.font.ImageGraphicAttribute;

public enum CarEnum implements ICar {
    YELLOWCAR {
        @Override
        public CarView getCarModel(Car car) {
            CarView carModel = new CarView(car);
            Image carImage = new Image(ConfigurationManager.getProperty("path.car.yellowcar"));

            carModel.setDefaultCar(carImage);
            ImageView body = new ImageView(carImage);
            double length = car.getBounds().getWidth();
            double width = car.getBounds().getHeight();
            body.setFitWidth(length);
            body.setFitHeight(width);

            double distanceWheels = car.getDistanceWheels();
            carModel.setBody(body);

            Transmission transmission = new Transmission();
            transmission.setFrontAxisWheels(new AxisWheels(length - 20, width / 2, distanceWheels));
            transmission.setRearAxisWheels(new AxisWheels(20, width / 2, distanceWheels));
            transmission.rebuild();

            carModel.setTransmission(transmission);
            carModel.rebuild();
            return carModel;
        }
    },

    GRAYCAR {
        @Override
        public CarView getCarModel(Car car) {
            CarView carModel = new CarView(car);
//            Image carImage = new Image(ConfigurationManager.getProperty("path.car.yellowcar"));
            Image carImage = new Image(ConfigurationManager.getProperty("path.car.graycar.car"));
            Image focusedCarImage = new Image("cars/png/graycar/focusedcar.png");
            Image mainCar = new Image(ConfigurationManager.getProperty("path.car.graycar.maincar"));

            carModel.setFocusedCar(focusedCarImage);
            carModel.setDefaultCar(carImage);
            carModel.setMainCar(mainCar);
            ImageView body = new ImageView(carImage);
            double length = car.getBounds().getWidth();
            double width = car.getBounds().getHeight();
            double widthView = width / 0.84;
            body.setY(-widthView * 0.08);
            body.setFitWidth(length);
            body.setFitHeight(widthView);

            double distanceWheels = car.getDistanceWheels();

            carModel.setBody(body);

            Transmission transmission = new Transmission();
            transmission.setFrontAxisWheels(new AxisWheels(length - 20, width / 2, distanceWheels));
            transmission.setRearAxisWheels(new AxisWheels(20, width / 2, distanceWheels));
            transmission.rebuild();

            carModel.setTransmission(transmission);
            carModel.rebuild();
            return carModel;
        }
    }

}
