package ru.golyashchuk.carparking.view.arena;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import ru.golyashchuk.carparking.shape.ResizableRectangle;
import ru.golyashchuk.carparking.view.Renderer;
import ru.golyashchuk.carparking.view.View;

public class EditorArenaBoundsView implements Renderer, View {
    public static final int WALL_WIDTH = 10;
    private ResizableRectangle view;
    private Rectangle model;

    public EditorArenaBoundsView(Rectangle model) {
        this.model = model;
        initializeArea();
    }

    @Override
    public void render() {

    }

    @Override
    public ResizableRectangle getView() {
        return view;
    }

    private void initializeArea() {
        view = new ResizableRectangle(model.getWidth() + WALL_WIDTH * 2, model.getHeight() + WALL_WIDTH * 2);
        view.setX(-WALL_WIDTH);
        view.setY(-WALL_WIDTH);
        view.setSidesStrokeWidth(WALL_WIDTH);
        view.getRectangle().setFill(Color.WHITE);
        view.setSidesColor(Color.BLACK);
    }

}
