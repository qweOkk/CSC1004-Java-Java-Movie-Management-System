package org.example;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
public class Main extends Application{

    public static void main(String[] args){
        launch(args);
    }
    public void start(Stage FirstStage) throws Exception{

        Pane pane =new Pane();
        FirstStage.setScene(new Scene(pane,640,480));
        FirstStage.show();
    }
}