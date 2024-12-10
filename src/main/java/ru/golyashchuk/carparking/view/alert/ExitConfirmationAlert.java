package ru.golyashchuk.carparking.view.alert;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import ru.golyashchuk.carparking.controllers.Controller;
import ru.golyashchuk.carparking.controllers.MenuController;

import java.util.Optional;

public class ExitConfirmationAlert extends Alert {
    public ExitConfirmationAlert() {
        super(AlertType.CONFIRMATION);
        super.setTitle("Подтверждение действия");
        super.setHeaderText("Вы точно хотите выйти в меню?");
    }
}
