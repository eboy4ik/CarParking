package ru.golyashchuk.carparking.view.arena;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import ru.golyashchuk.carparking.view.Renderer;
import ru.golyashchuk.carparking.view.View;

public class FinishView implements Renderer, View {
    private Rectangle view;

    public FinishView(Rectangle model) {
        view = new Rectangle(model.getX(), model.getY(), model.getWidth(), model.getHeight());
        view.setFill(Color.rgb(0, 150, 0, 0.5));
    }

    @Override
    public Rectangle getView() {
        return view;
    }

    @Override
    public void render() {
//        initializeView();
    }

}
