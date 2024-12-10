package ru.golyashchuk.carparking.tester;


import javafx.application.Application;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import ru.golyashchuk.carparking.shape.ResizableRectangle;


import java.io.IOException;

//public class test {
//    public static void main(String[] args) {
//        int w = 200;
//        int l = 300;
//        Rectangle rect = new Rectangle(w, l);
//        Rectangle rect2 = new Rectangle(w, l);
//        rect.setLocation(200, 299);
//        System.out.println(rect.getBounds().intersects(rect2));
//    }
//}

public class test extends Application {

    @Override
    public void start(Stage stage) throws IOException {

//        Rectangle rect = new Rectangle(200, 100, 400, 200);
        ResizableRectangle rect2 = new ResizableRectangle(400, 100, 100, 100);
//        Line line = new Line(100, 0, 100, 100);
//        line.setStrokeWidth(5);
//        line.setOnMouseEntered(event -> {
//            line.getScene().setCursor(Cursor.H_RESIZE);
//        });
//        line.setOnMouseExited(event -> {
//            line.getScene().setCursor(Cursor.DEFAULT);
//        });

//        rect2.setRotate(45);
        rect2.getRectangle().setFill(Color.rgb(255, 0, 255));
//        rect.setFill(Color.rgb(255, 0, 0));

//        rect.setX(200);
//        Shape shape = Shape.intersect(rect, rect2);

        Pane root = new Pane();
//        root.getChildren().add(rect);
        root.getChildren().add(rect2);
//        root.getChildren().add(line);

        Scene scene = new Scene(root, 800, 800);
        stage.setScene(scene);
//        rect2.setOnMouseDragged(this::onMouseDragged);
//        rect2.setOnMouseExited(this::onMouseDragExited);

//        rect2.set
//        rect2.setOnDragExited(this::onMouseDragExited);
        stage.show();
    }

    private void onMouseExited(MouseEvent mouseEvent) {
        System.out.println("EXIT");
        ((Rectangle) mouseEvent.getSource()).getScene().setCursor(Cursor.DEFAULT);
    }

    private void onMouseDragExited(MouseEvent dragEvent) {
        System.out.println("RELEASED");
        ((Rectangle) dragEvent.getSource()).getScene().setCursor(Cursor.DEFAULT);
    }

    private void onMouseDragged(MouseEvent mouseEvent) {
        System.out.println("Drag");
        Rectangle rect = (Rectangle) mouseEvent.getSource();
        rect.setX(mouseEvent.getX() - 10);
        rect.setY(mouseEvent.getY() - 10);
        ((Rectangle) mouseEvent.getSource()).getScene().setCursor(Cursor.MOVE);
    }


    public static void main(String[] args) {
        launch();
    }
}