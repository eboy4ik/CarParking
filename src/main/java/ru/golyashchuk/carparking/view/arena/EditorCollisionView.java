package ru.golyashchuk.carparking.view.arena;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import ru.golyashchuk.carparking.models.Collision;
import ru.golyashchuk.carparking.models.Model;
import ru.golyashchuk.carparking.shape.ResizableRectangle;
import ru.golyashchuk.carparking.view.Renderer;
import ru.golyashchuk.carparking.view.View;

public class EditorCollisionView implements Renderer, View, Focusable {
    private ResizableRectangle view;

    public EditorCollisionView(Collision model) {
        view = new ResizableRectangle(model.getCollision().getWidth(), model.getCollision().getHeight());
        render(model);
    }

    public void setView(ResizableRectangle view) {
        this.view = view;
    }

    @Override
    public ResizableRectangle getView() {
        return view;
    }

    @Override
    public void render(Model model) {
        Collision collision = (Collision) model;
        view.setX(collision.getCollision().getX());
        view.setY(collision.getCollision().getY());
    }

    @Override
    public void unfocus() {
        view.getRectangle().setStroke(Color.BLACK);
    }

    @Override
    public void focus() {
        view.getRectangle().setStroke(Color.RED);
    }
}
