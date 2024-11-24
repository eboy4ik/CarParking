package ru.golyashchuk.carparking.tester;



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;


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

        Rectangle rect = new Rectangle(200, 100, 400, 200);
        Rectangle rect2 = new Rectangle(500, 500, 100, 100);
        rect2.setRotate(45);
        rect2.setFill(Color.rgb(255, 0, 255));
        rect.setFill(Color.rgb(255, 0, 0));

        rect.setX(200);
        Shape shape = Shape.intersect(rect, rect2);
//        System.out.println(rect.intersects(rect2));
        System.out.println(shape.getBoundsInLocal().getWidth());
        System.out.println(shape.isVisible());
//        System.out.println((rect.getBoundsInParent().intersects(rect2.getBoundsInParent())));
        System.out.println(Shape.intersect(rect, rect2));
        Pane root = new Pane();
        root.getChildren().add(rect);
        root.getChildren().add(rect2);
        Scene scene = new Scene(root, 800, 800);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}