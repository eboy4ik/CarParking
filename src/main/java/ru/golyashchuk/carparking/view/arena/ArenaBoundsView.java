package ru.golyashchuk.carparking.view.arena;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import ru.golyashchuk.carparking.view.Renderer;
import ru.golyashchuk.carparking.view.View;

public class ArenaBoundsView implements Renderer, View {
    public static final int WALL_WIDTH = 10;
    private Group view;
    private Rectangle freeArea;
    private Rectangle wall;
    private Rectangle model;

    public ArenaBoundsView(Rectangle model) {
        this.model = model;
        initializeWall();
        initializeFreeArea();
        rebuild();
    }

    @Override
    public void render() {
        initializeWall();
        initializeFreeArea();
        rebuild();
    }

    @Override
    public Group getView() {
        return view;
    }

    private void initializeWall() {
        wall = new Rectangle(model.getWidth() + WALL_WIDTH * 2, model.getHeight() + WALL_WIDTH * 2);
        wall.setX(-WALL_WIDTH);
        wall.setY(-WALL_WIDTH);
        wall.setFill(Color.BLACK);
    }

    private void initializeFreeArea() {
        freeArea = new Rectangle(model.getWidth(), model.getHeight());
        freeArea.setFill(Color.rgb(255, 255, 255, 1));
    }


    private void rebuild() {
        this.view = new Group();
        this.view.getChildren().add(this.wall);
        this.view.getChildren().add(this.freeArea);
    }

}
