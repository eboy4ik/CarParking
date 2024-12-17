package ru.golyashchuk.carparking.view;

import ru.golyashchuk.carparking.models.Model;
import ru.golyashchuk.carparking.models.ModelType;
import ru.golyashchuk.carparking.view.arena.Focusable;

public interface SelectedModel extends Focusable, Model {
    void rotate(double angle);

    void moveTo(double x, double y);

    ModelType getModelType();
}
