package ru.golyashchuk.carparking.view.arena;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import ru.golyashchuk.carparking.models.Model;
import ru.golyashchuk.carparking.view.Renderer;
import ru.golyashchuk.carparking.view.View;

public class FinishView implements Renderer, Focusable, View {
    private Rectangle view;

    public FinishView(Rectangle model) {
        view = new Rectangle(model.getX(), model.getY(), model.getWidth(), model.getHeight());
        view.setRotate(model.getRotate());
        view.setFill(Color.rgb(0, 150, 0, 0.5));
    }

    @Override
    public Rectangle getView() {
        return view;
    }

    @Override
    public void render(Model model) {
        render((Rectangle) model);
    }

    public void render(Rectangle rect) {
        view.setX(rect.getX());
        view.setY(rect.getY());
        view.setWidth(rect.getWidth());
        view.setHeight(rect.getHeight());
        view.setRotate(rect.getRotate());
        view.setFill(Color.rgb(0, 150, 0, 0.5));
    }

    @Override
    public void unfocus() {
        view.setStroke(Color.TRANSPARENT);
    }

    @Override
    public void focus() {
        view.setStroke(Color.RED);
    }
}
