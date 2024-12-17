package ru.golyashchuk.carparking.view.arena;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import ru.golyashchuk.carparking.models.Model;
import ru.golyashchuk.carparking.utils.ShapeHandler;
import ru.golyashchuk.carparking.view.Renderer;
import ru.golyashchuk.carparking.view.View;

public class CollisionView implements Renderer, View {
    private Rectangle view;

    public CollisionView(Rectangle model) {
        view = ShapeHandler.copyRectangle(model);
        view.setFill(Color.BLACK);
    }

    @Override
    public Rectangle getView() {
        return view;
    }

    @Override
    public void render(Model model) {

    }


}
