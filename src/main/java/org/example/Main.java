package org.example;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class Main extends Application{

    public static void main(String[] args){
        launch(args);
    }
    public void start(Stage args0) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("Scene/Login.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        args0.setScene(scene);
        args0.setTitle("Login");
        args0.show();

    }
}
