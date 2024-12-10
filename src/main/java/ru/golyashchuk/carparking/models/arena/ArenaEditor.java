package ru.golyashchuk.carparking.models.arena;

import ru.golyashchuk.carparking.models.car.Car;
import ru.golyashchuk.carparking.view.arena.ArenaEditorView;
import ru.golyashchuk.carparking.view.arena.ArenaView;
import ru.golyashchuk.carparking.view.arena.EditorArenaView;

public class ArenaEditor {
    private final Arena arena;
    private final EditorArenaView arenaView;

    public ArenaEditor() {
        this.arena = new Arena();
        arena.setWidth(500);
        arena.setHeight(500);
        arenaView = new EditorArenaView(arena);
    }

    public Arena getArena() {
        return arena;
    }

    public EditorArenaView getArenaView() {
        return arenaView;
    }

    public void addCar(Car car) {
        arena.addCar(car);
        arenaView.addCar(car);
    }

}
