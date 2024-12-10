package ru.golyashchuk.carparking.tester;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ResizablePaneExample extends Application {

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();

        Rectangle rectangle = new Rectangle(50, 50, 100, 100);
        rectangle.setFill(Color.BLUE);

        // Привязываем ширину и высоту Pane к размерам Rectangle
        pane.prefWidthProperty().bind(rectangle.widthProperty());
        pane.prefHeightProperty().bind(rectangle.heightProperty());

        pane.getChildren().add(rectangle);

        Scene scene = new Scene(pane, 300, 250);

        primaryStage.setTitle("Resizable Pane Example");
        primaryStage.setScene(scene);
        primaryStage.show();
        rectangle.setWidth(1000);
    }

    public static void main(String[] args) {
        launch(args);
    }
}