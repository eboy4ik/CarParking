package ru.golyashchuk.carparking.tester;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MouseEventExample extends Application {
    @Override
    public void start(Stage primaryStage) {
        Label label = new Label("Click Me!");
        label.setOnMouseClicked(event -> {
            System.out.println("Mouse Clicked at: (" + event.getX() + ", " + event.getY() + ")");
        });

        label.setOnMouseEntered(event -> {
            System.out.println("Mouse Entered");
        });

        label.setOnMouseExited(event -> {
            System.out.println("Mouse Exited");
        });

        label.setOnMousePressed(event -> {
            System.out.println("Mouse Pressed");
        });

        label.setOnMouseReleased(event -> {
            System.out.println("Mouse Released");
        });

        label.setOnMouseDragged(event -> {
            System.out.println("Mouse Dragged to: (" + event.getX() + ", " + event.getY() + ")");
        });

        StackPane root = new StackPane();
        root.getChildren().add(label);

        Scene scene = new Scene(root, 300, 200);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Mouse Event Example");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}