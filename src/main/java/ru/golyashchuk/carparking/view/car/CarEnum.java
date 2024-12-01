package ru.golyashchuk.carparking.view.car;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ru.golyashchuk.carparking.config.ConfigurationManager;
import ru.golyashchuk.carparking.models.car.Car;
import ru.golyashchuk.carparking.view.car.AxisWheels;
import ru.golyashchuk.carparking.view.car.CarView;
import ru.golyashchuk.carparking.view.car.ICar;
import ru.golyashchuk.carparking.view.car.Transmission;

public enum CarEnum implements ICar {
    YELLOWCAR {
        @Override
        public CarView getCarModel(Car car) {
            CarView carModel = new CarView(car);
            Image carImage = new Image(ConfigurationManager.getProperty("path.car.yellowcar"));

            ImageView body = new ImageView(carImage);
            double length = 102;
            double width = 47;
            body.setFitWidth(length);
            body.setFitHeight(width);
            double distanceWheels = width - 4;
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
