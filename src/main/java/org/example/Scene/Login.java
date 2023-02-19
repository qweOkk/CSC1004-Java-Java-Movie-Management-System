package org.example.Scene;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Login {

    @FXML
    private Button LoginBut;

    @FXML
    private Label UsernameFIield;

    @FXML
    private Label ProjectName;

    @FXML
    private TextField UserField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private ChoiceBox<?> Choicebox;

    @FXML
    private Label Password;

    @FXML
    void login(ActionEvent event) {
            String username = UserField.getText();
            String password = passwordField.getText();
    }

}
