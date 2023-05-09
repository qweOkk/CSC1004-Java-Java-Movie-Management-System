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
    /**
     * show the loginView page.
     */
    public void showLoginView() {
        try {
            // Load the login page.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("scene/Login.fxml"));
            Parent root = loader.load();
            LoginController loginController = loader.getController();
            primaryStage.setResizable(false);
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
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
