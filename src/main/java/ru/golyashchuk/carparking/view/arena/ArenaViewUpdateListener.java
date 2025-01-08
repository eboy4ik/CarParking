package ru.golyashchuk.carparking.view.arena;

public interface ArenaViewUpdateListener {
    void onMove(Object object, double newX, double newY);

    void onResize(Object object, double newWidth, double newHeight);

    void onRotate(Object object, double newAngle);
}
