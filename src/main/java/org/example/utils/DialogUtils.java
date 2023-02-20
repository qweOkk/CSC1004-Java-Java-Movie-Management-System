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
        ImageView menhera = new ImageView(Main.class.getResource("scene/images/systemUse/tips.png").toString());
        menhera.setFitHeight(100);
        menhera.setPreserveRatio(true);
        alert.setGraphic(menhera);
        alert.setHeaderText("There is something wrong with your information!");
        alert.setContentText(message);
        alert.initOwner(stage);
        alert.show();
    }

    public static void good(Stage stage, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Congratulations!");
        ImageView menhera = new ImageView(Main.class.getResource("scene/images/systemUse/good.png").toString());
        menhera.setFitHeight(100);
        menhera.setPreserveRatio(true);
        alert.setGraphic(menhera);
        alert.setHeaderText("Your operation worked as expected");
        alert.setContentText(message);
        alert.initOwner(stage);
        alert.show();
    }

    public static boolean confirm(Stage stage, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, message ,new ButtonType("No", ButtonBar.ButtonData.NO),
                new ButtonType("Yes", ButtonBar.ButtonData.YES));
//        设置窗口的标题
        alert.setTitle("Yes");
        ImageView menhera = new ImageView(Main.class.getResource("scene/images/systemUse/confirm.png").toString());
        menhera.setFitHeight(100);
        menhera.setPreserveRatio(true);
        alert.setGraphic(menhera);
        alert.setHeaderText("The last Chance!");
//        设置对话框的 icon 图标，参数是主窗口的 stage
        alert.initOwner(stage);
//        showAndWait() 将在对话框消失以前不会执行之后的代码
        Optional<ButtonType> buttonType = alert.showAndWait();
//        根据点击结果返回
        if(buttonType.get().getButtonData().equals(ButtonBar.ButtonData.YES)){
            return true;
        } else {
            return false;
        }
    }
}
