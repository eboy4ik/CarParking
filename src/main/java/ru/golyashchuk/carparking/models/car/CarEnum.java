package ru.golyashchuk.carparking.models.car;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ru.golyashchuk.carparking.config.ConfigurationManager;

public enum CarEnum implements ICar {
    YELLOWCAR {
        @Override
        public CarModel getCarModel() {
            CarModel carModel = new CarModel();
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
