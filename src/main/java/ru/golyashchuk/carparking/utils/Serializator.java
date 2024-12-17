package ru.golyashchuk.carparking.utils;

import javafx.scene.shape.Rectangle;
import ru.golyashchuk.carparking.models.arena.Arena;
import ru.golyashchuk.carparking.models.car.Car;

import java.io.*;

public class Serializator {
    private Serializator() {
    }

    public static void serializeRectangle(Rectangle rectangle, ObjectOutputStream oos) throws IOException {
        oos.writeDouble(rectangle.getX());
        oos.writeDouble(rectangle.getY());
        oos.writeDouble(rectangle.getWidth());
        oos.writeDouble(rectangle.getHeight());
        oos.writeDouble(rectangle.getArcWidth());
        oos.writeDouble(rectangle.getArcHeight());
    }

    public static Rectangle deserializeRectangle(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        double x = ois.readDouble();
        double y = ois.readDouble();
        double width = ois.readDouble();
        double height = ois.readDouble();
        double arcWidth = ois.readDouble();
        double arcHeight = ois.readDouble();

        Rectangle rect = new Rectangle(x, y, width, height);
        rect.setArcWidth(arcWidth);
        rect.setArcHeight(arcHeight);

        return rect;
    }

    public static Arena deserializeArena(File selectedFile) throws IOException, ClassNotFoundException {
        Arena arena = null;
        try (FileInputStream fileIn = new FileInputStream(selectedFile);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            arena = (Arena) in.readObject();
        }
        return arena;
    }

    public static void serializeArena(Arena arena, File fileToSave) throws IOException {
        try (FileOutputStream fileOut = new FileOutputStream(fileToSave);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(arena);
        }
    }

}
