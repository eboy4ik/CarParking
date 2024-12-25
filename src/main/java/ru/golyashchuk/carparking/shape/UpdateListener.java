package ru.golyashchuk.carparking.shape;

public interface UpdateListener {
    void onResize(double newWidth, double newHeight);

    void onMove(double newX, double newY);
}
