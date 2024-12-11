package ru.golyashchuk.carparking.view.arena;

import javafx.scene.paint.Color;
import ru.golyashchuk.carparking.models.Collision;
import ru.golyashchuk.carparking.shape.ResizableRectangle;
import ru.golyashchuk.carparking.view.Renderer;
import ru.golyashchuk.carparking.view.View;

public class EditorCollisionView implements Renderer, View, Focusable {
    private Collision model;
    private ResizableRectangle view;

    public EditorCollisionView(Collision model) {
        this.model = model;
        view = new ResizableRectangle(model.getCollision().getWidth(), model.getCollision().getHeight());
        render();
    }

    public Collision getModel() {
        return model;
    }

    public void setModel(Collision model) {
        this.model = model;
    }

    public void setView(ResizableRectangle view) {
        this.view = view;
    }

    @Override
    public ResizableRectangle getView() {
        return view;
    }

    @Override
    public void render() {
        view.setX(model.getCollision().getX());
        view.setY(model.getCollision().getY());
    }

    @Override
    public void unfocus() {
        view.getRectangle().setStroke(Color.BLACK);
    }

    @Override
    public void focus() {
        view.getRectangle().setStroke(Color.RED);
        System.out.println("AGA 2");
    }
}
