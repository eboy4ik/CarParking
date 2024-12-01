package ru.golyashchuk.carparking.view.arena;

import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import ru.golyashchuk.carparking.view.Renderer;
import ru.golyashchuk.carparking.view.View;

public class CollisionView implements Renderer, View {
    Rectangle collision;
    Rectangle view;

    public CollisionView(Rectangle collision) {
        this.collision = collision;
    }

    @Override
    public Rectangle getView() {
        return view;
    }

    @Override
    public void render() {

    }


}
