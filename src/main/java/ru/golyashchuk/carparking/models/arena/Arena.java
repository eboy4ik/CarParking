package ru.golyashchuk.carparking.models.arena;

import com.sun.javafx.geom.Area;
import javafx.scene.Node;
import ru.golyashchuk.carparking.models.car.Car;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class Arena {
    int width;
    int length;
    Area area;
    List<Car> cars = new LinkedList<Car>();
    Node node;

    public Arena(int width, int length) {
        Rectangle arenaRect = new Rectangle(width, length);
        arenaRect.getBounds();

    }
}
