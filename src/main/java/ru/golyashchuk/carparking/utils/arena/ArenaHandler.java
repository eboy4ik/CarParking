package ru.golyashchuk.carparking.utils.arena;

import javafx.scene.shape.Shape;
import ru.golyashchuk.carparking.models.AddableView;
import ru.golyashchuk.carparking.models.Model;
import ru.golyashchuk.carparking.models.arena.Arena;

public class ArenaHandler {
    private ArenaHandler() {
    }

    public static boolean canBeAdded(Arena arena, Shape shape) {
        return true;
    }

}
