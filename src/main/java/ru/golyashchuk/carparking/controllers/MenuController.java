package ru.golyashchuk.carparking.controllers;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ru.golyashchuk.carparking.config.SettingsConfiguration;

import java.io.IOException;

public class MenuController implements Controller {
    private Stage primaryStage;

    public MenuController(Stage primaryStage) {
        initializeScene(primaryStage);
    }

    @Override
    public void initializeScene(Stage stage) {
        this.primaryStage = stage;
        VBox menuLayout = new VBox();
        menuLayout.setSpacing(10);
        menuLayout.setPadding(new javafx.geometry.Insets(20, 20, 20, 20));
        menuLayout.setAlignment(javafx.geometry.Pos.CENTER);

        Button playButton = new Button("Играть");
        playButton.setFont(new javafx.scene.text.Font("System Bold", 18));
        playButton.setOnAction(e -> {
            try {
                startGame();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        Button createLevelButton = new Button("Cоздать уровень");
        createLevelButton.setFont(new javafx.scene.text.Font("System Bold", 18));
        createLevelButton.setOnAction(e -> startLevelEditor());

        Button informationButton = new Button("Информация");
        informationButton.setFont(new javafx.scene.text.Font("System Bold", 18));
        informationButton.setOnAction(e -> showInformation());

        Button exitButton = new Button("Выход");
        exitButton.setFont(new javafx.scene.text.Font("System Bold", 18));
        exitButton.setOnAction(e -> System.exit(0));

        menuLayout.getChildren().addAll(playButton, createLevelButton, informationButton, exitButton);

        Scene menuScene;
        if (primaryStage.getScene() == null) {
            menuScene = new Scene(menuLayout, SettingsConfiguration.getWindowWidth(), SettingsConfiguration.getWindowHeight());
        } else {
            menuScene = new Scene(menuLayout, primaryStage.getScene().getWidth(), primaryStage.getScene().getHeight());
        }

        primaryStage.setScene(menuScene);
        primaryStage.setTitle("Car Parking");
        primaryStage.show();
    }

    private void showInformation() {
        new InformationController(primaryStage);
    }

    private void startLevelEditor() {
        new LevelEditorController(primaryStage);
    }

    private void startGame() throws IOException {
        new LevelChooserController(primaryStage);
    }


}
