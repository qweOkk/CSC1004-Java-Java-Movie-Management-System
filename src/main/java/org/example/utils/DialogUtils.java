package org.example.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.example.Main;

import java.util.Optional;

public class DialogUtils {
    public static void tips(Stage stage, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Suggestions!");
        alert.setHeaderText("There is something wrong with your information!");
        alert.setContentText(message);
        alert.initOwner(stage);
        alert.show();
    }

    public static void good(Stage stage, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Congratulations!");
        alert.setHeaderText("Your operation worked as expected");
        alert.setContentText(message);
        alert.initOwner(stage);
        alert.show();
    }

    public static boolean confirm(Stage stage, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, message ,new ButtonType("No", ButtonBar.ButtonData.NO),
                new ButtonType("Yes", ButtonBar.ButtonData.YES));
        alert.setTitle("Yes");
        alert.setHeaderText("The last Chance!");
        alert.initOwner(stage);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if(buttonType.get().getButtonData().equals(ButtonBar.ButtonData.YES)){
            return true;
        } else {
            return false;
        }
    }
}
