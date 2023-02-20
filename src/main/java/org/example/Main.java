package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.models.User;
import org.example.scene.LoginController;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application{
    private User userNow;
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Movie Management");
        showLoginView();
    }
    public void showLoginView() {
        try {
            // 载入登录页面
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("scene/Login.fxml"));
            Parent root = loader.load();
            LoginController loginController = loader.getController();
            primaryStage.setScene(new Scene(root));
            primaryStage.show();

            // 传递主函数
            System.out.println(loginController);
            loginController.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setUserNow(User userNow) {
        this.userNow = userNow;
    }

    public User getUserNow() {
        return userNow;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    public static void main(String[] args){
        launch(args);
    }

}
