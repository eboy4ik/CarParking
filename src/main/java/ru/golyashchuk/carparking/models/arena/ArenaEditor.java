package ru.golyashchuk.carparking.models.arena;

import javafx.scene.shape.Rectangle;
import ru.golyashchuk.carparking.models.Collision;
import ru.golyashchuk.carparking.models.ModelType;
import ru.golyashchuk.carparking.models.car.Car;
import ru.golyashchuk.carparking.utils.ShapeHandler;
import ru.golyashchuk.carparking.view.arena.EditorArenaView;
import ru.golyashchuk.carparking.view.arena.EditorCollisionView;
import ru.golyashchuk.carparking.view.arena.Focusable;
import ru.golyashchuk.carparking.view.car.CarView;

public class ArenaEditor {
    private final Arena arena;
    private final EditorArenaView arenaView;
    private ModelType selectedModel;
    private Focusable focusable;

    public void setSelectedModel(ModelType selectedModel) {
        this.selectedModel = selectedModel;
    }

    public ModelType getSelectedModel() {
        return selectedModel;
    }

    public ArenaEditor() {
        this.arena = new Arena();
        arena.setWidth(500);
        arena.setHeight(500);
        arenaView = new EditorArenaView(arena);
    }

    public Focusable getFocusable() {
        return focusable;
    }

    public void setFocusable(Focusable focusable) {
        this.focusable = focusable;
    }

    public Arena getArena() {
        return arena;
    }

    public EditorArenaView getArenaView() {
        return arenaView;
    }

    public void addCar(Car car) {
        arena.addCar(car);
        arenaView.addCar(car);
    }

    public void setFinish(Rectangle finish) {
        arena.setFinish(finish);
        arenaView.setFinish(ShapeHandler.copyRectangle(finish));
    }

    public void addCollision(Collision collision) {
        arena.addCollisional(collision);
        arenaView.addCollision(collision);
    }

    public void addObject(double x, double y) {
        if (selectedModel == null)
            return;

        if (selectedModel == ModelType.CAR) {
            Car car = new Car(x, y, 0);
            addCar(car);
        }
        if (selectedModel == ModelType.FINISH) {
            Rectangle finish = new Rectangle(x, y, 70, 120);
            setFinish(finish);
        }

        if (selectedModel == ModelType.COLLISION) {
            Collision collision = new Collision(new Rectangle(x, y, 100, 100));
            addCollision(collision);
        }
    }

    public void focus(double x, double y) {
        unfocus();

        for (CarView carView : arenaView.getCars()) {
//            if (carView.getCar().getBounds().contains(x, y)) {
//                carView.focus();
//                focusable = carView;
//            }
        }

        for (EditorCollisionView collision : arenaView.getCollisions()) {
            if (collision.getModel().getCollision().contains(x, y)) {
                collision.focus();
                focusable = collision;
                return;
            }
        }
    }

    private void unfocus() {
        if (this.focusable != null) {
            focusable.unfocus();
            focusable = null;
        }
    }
}
