package org.example.scene;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.example.Main;

public class CommonUserViewController extends Application {
        private Main mainApp;

        @Override
        public void start(Stage primaryStage){

        }

        public void setMainApp(Main mainApp) {this.mainApp = mainApp;
        }

    public void handleChange(ActionEvent actionEvent) {
    }

    public void handleNameOrderPie(ActionEvent actionEvent) {
    }

    public void handleToMovie(MouseEvent mouseEvent) {
    }

    public void handleToCommonUser(MouseEvent mouseEvent) {
    }

    public void handleLogout(MouseEvent mouseEvent) {
    }
}
