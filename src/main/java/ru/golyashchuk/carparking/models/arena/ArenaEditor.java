package ru.golyashchuk.carparking.models.arena;

import ru.golyashchuk.carparking.models.car.Car;
import ru.golyashchuk.carparking.view.arena.ArenaView;

public class ArenaEditor {
    private Arena arena;
    private ArenaView arenaView;

    public ArenaEditor() {
        this.arena = new Arena();
        arena.setWidth(500);
        arena.setHeight(500);
        arenaView = new ArenaView(arena);
    }

    public Arena getArena() {
        return arena;
    }

    public ArenaView getArenaView() {
        return arenaView;
    }

    public void addCar(Car car) {
        arena.addCar(car);
        arenaView.updateAllViews();
    }


}
