package org.example.scene;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import org.example.Main;
import org.example.Session;
import org.example.models.Admin;
import org.example.models.CommonUser;
import org.example.utils.DialogUtils;
import org.example.utils.InputChecker;
import org.example.utils.JdbcUtils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginController {
    @FXML
    private TextField TeleField;
    @FXML
    private TextField ID_numberField;
    @FXML
    private TextField AgeField;
    @FXML
    private PasswordField SignUpPasswordField;
    @FXML
    private TextField SignUpUserField;
    @FXML
    private Pane SignUp_pane;
    @FXML
    private Pane Login_pane;
    @FXML
    private Button LoginBut;

    @FXML
    private Button SignUp;

    @FXML
    private Label ProjectName;

    @FXML
    private TextField UserField;

    @FXML
    private Label UsernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label Password;
    @FXML
    private CheckBox isAdmin;

    @FXML
    private Main mainApp;
    @FXML
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    public Main getMainApp() {
        return mainApp;
    }

    /**
     * Login with Admin or commonuser.
     */
    @FXML
    void LogIn() {
            System.out.println("The button has been clicked!");
            JdbcUtils  jdbcUtils = new JdbcUtils();
            jdbcUtils.getConnection();
            String sql=null;

            if(isAdmin.isSelected()){
                sql = "select * from `admin` where username = ? and password = ?";
            }
            else{
                sql = "select * from `commonUser` where username = ? and password = ?";
            }
            List<Object> params = new ArrayList<>();
            params.add(UserField.getText());
            params.add(passwordField.getText());
            System.out.println(UserField.getText());
            System.out.println(passwordField.getText());

            try{
                if(isAdmin.isSelected()){
                    Admin admin = jdbcUtils.findSimpleProResult(sql, params, Admin.class);
                    if(admin==null){
                        throw new NullPointerException("There is wrong with your username or password");

                    }
                    System.out.println(admin);
                    mainApp.setUserNow(admin);
                    Session.setUsername(admin.getUsername());
                    Session.setType("admin");
                    Session.setId(admin.getId());
                }

                else {
                    CommonUser commonUser = jdbcUtils.findSimpleProResult(sql, params, CommonUser.class);
                    if(commonUser == null){
                        throw new NullPointerException("There is wrong with your username or password");
                    }
                    System.out.println(commonUser);
                    mainApp.setUserNow(commonUser);
                    Session.setUsername(commonUser.getUsername());
                    Session.setType("commonUser");
                }
                FXMLLoader loader = new FXMLLoader();
                if( isAdmin.isSelected()){
                    loader.setLocation(Main.class.getResource("scene/AdminView.fxml"));
                }
                else{
                    loader.setLocation(Main.class.getResource("scene/CommonUserView.fxml"));
                }
                Parent newView = loader.load();
               if (isAdmin.isSelected()) {
                    AdminViewController adminVIewController = loader.getController();
                    System.out.println(adminVIewController);
                    System.out.println(mainApp);
                    adminVIewController.setMainApp(mainApp);
                } else {
                    CommonUserViewController commonUserViewController = loader.getController();
                    System.out.println("mainApp: " + mainApp);
                    commonUserViewController.setMainApp(mainApp);
                }
                mainApp.getPrimaryStage().setScene(new Scene(newView));
            } catch (Exception e) {
                DialogUtils.tips(mainApp.getPrimaryStage(), e.getMessage());
                System.out.println("There is something wrong!");

            }

    }

    /**
     * Turn to Sign Up page.
     */
    @FXML
    void signup() throws Exception{
        System.out.println("The SignUp button is clicked");
        SignUp_pane.setVisible(true);
        Login_pane.setVisible(false);

    }
    /**
     * Back to Log In page.
     */
    @FXML
    void Back_up()throws Exception {
        SignUp_pane.setVisible(false);
        Login_pane.setVisible(true);
    }
    /**
     * Sign Up for CommonUse.
     */
    @FXML
    void Sign_up()throws Exception {
       String errormessage = InputChecker.commonUserSignUpCheck(SignUpUserField.getText(),SignUpPasswordField.getText(), Integer.valueOf(AgeField.getText()), TeleField.getText(),ID_numberField.getText());


        if(errormessage==null){
            JdbcUtils jdbcUtils = new JdbcUtils();
            jdbcUtils.getConnection();
            String sql = "insert into `commonUser`(username, password, age, tel, sid) " +
                    "values(?, ?, ?, ?, ?)";
            List<Object> params = new ArrayList<>();
            params.add(SignUpUserField.getText());
            params.add(SignUpPasswordField.getText());
            params.add(AgeField.getText());
            params.add(TeleField.getText());
            params.add(ID_numberField.getText());
            try{
                jdbcUtils.updateByPreparedStatement(sql, params);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            CommonUser newUser=new CommonUser((SignUpUserField.getText()),SignUpPasswordField.getText());
            mainApp.setUserNow(newUser);
            sql="select * from `commonUser` where username = ? and password = ?";
            params.clear();
            params.add(SignUpUserField.getText());
            params.add(SignUpPasswordField.getText());
            try{
                CommonUser now = jdbcUtils.findSimpleProResult(sql, params, CommonUser.class);
                Session.setUsername(now.getUsername());
                Session.setType("commonUser");
            }catch (Exception e) {
                e.printStackTrace();
            }
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Main.class.getResource("scene/CommonUserView.fxml"));
                Parent commonUserView = loader.load();
                CommonUserViewController commonUserViewController = loader.getController();
                mainApp.getPrimaryStage().setScene(new Scene(commonUserView, 1152, 736));
                commonUserViewController.setMainApp(mainApp);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            DialogUtils.tips(mainApp.getPrimaryStage(), errormessage);
        }
    }
}
