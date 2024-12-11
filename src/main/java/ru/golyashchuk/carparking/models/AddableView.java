package ru.golyashchuk.carparking.models;

import javafx.scene.shape.Shape;
import ru.golyashchuk.carparking.view.View;

public interface AddableView extends View {
    Shape getModel();
    ModelType getModelType();

}
