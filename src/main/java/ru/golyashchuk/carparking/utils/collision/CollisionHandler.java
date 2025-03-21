package ru.golyashchuk.carparking.utils.collision;


import javafx.scene.shape.Shape;

public class CollisionHandler {
    private CollisionHandler() {
    }

    public static boolean containsShape(Shape main, Shape inner) {
        return Shape.subtract(Shape.union(main, inner), main).getBoundsInParent().isEmpty();
    }

    public static boolean intersectShapes(Shape first, Shape second) {
        Shape shape = Shape.intersect(first, second);
        return shape.getBoundsInParent().getWidth() > 0;
    }
}
