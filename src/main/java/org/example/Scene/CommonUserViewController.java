package org.example.scene;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.example.Main;
import org.example.Session;
import org.example.models.CommonUser;
import org.example.models.Movie;
import org.example.utils.JdbcUtils;
import org.example.utils.UIAdjistUtils;
import org.controlsfx.control.Rating;

import javax.xml.bind.annotation.XmlMimeType;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CommonUserViewController extends Application {
        private Main mainApp;
    @FXML
    private TextField usernameField;

    @FXML
    private BorderPane subscriptedPane;

    @FXML
    private TableColumn<Movie, Integer> movieIdColumn;

    @FXML
    private TextArea movieComArea;

    @FXML
    private Rectangle imageBorder;

    @FXML
    private TextField sidField;

    @FXML
    private Label movieNameLabel;

    @FXML
    private Label movieDirectorLabel;

    @FXML
    private Label toMovieButton;

    @FXML
    private Button changeButton;

    @FXML
    private ImageView movieCover;

    @FXML
    private TableView<Movie> movieTableView;

    @FXML
    private Label userNameLabel;

    @FXML
    private Label toCommonUserButton;

    @FXML
    private TextField passwordField;

    @FXML
    private Label movieContentLabel;

    @FXML
    private TextField AgeField;

    @FXML
    private TextField telField;

    @FXML
    private TableColumn<Movie, String> movieNameColumn;

    @FXML
    private Pane personInfoPane;

    @FXML
    private Label movieTypeLabel;

    @FXML
    private Label logOutButton;

    @FXML
    private BorderPane moviePane;
    @FXML
    private Label movieActorLabel;
    @FXML
    private Label movieIdLabel;
    @FXML
    private Rating movieRating;
    private ObservableList<Movie> moviesData = FXCollections.observableArrayList();
    @FXML
    private void initialize() {
        movieIdColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        movieNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        movieTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> updateDetail(newValue)
        );
        initTable();
        movieTableView.getSelectionModel().selectFirst();
        initUserNameLabel();
    }

    public void initUserNameLabel() {
        if (Session.getType().equals("commonUser")) {
            userNameLabel.setText(Session.getUsername());
        }
        else if (Session.getType().equals("admin")) {
            userNameLabel.setText("尊贵的管理员" + Session.getUsername() + "先生");
        }
    }
    public void initTable() {
        JdbcUtils jdbcUtils = new JdbcUtils();
        jdbcUtils.getConnection();
        String sql = "select a.id, a.coverPath, a.`name`, a.director, a.actor, a.intro, b.className from `movie` a, `mClass` b where a.classNumber = b.id;";
        List<Object> params = new ArrayList<>();
        try {
            List<Movie> rawData = jdbcUtils.findMoreProResult(sql, params, Movie.class);
            moviesData.addAll(rawData);
            movieTableView.setItems(moviesData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void updateDetail(Movie solo) {
        solo = movieTableView.getSelectionModel().getSelectedItem();
        movieIdLabel.setText("" + solo.getId());
        movieNameLabel.setText(solo.getName());
        movieDirectorLabel.setText(solo.getDirector());
        movieActorLabel.setText(solo.getActor());
        movieContentLabel.setText(solo.getIntro());
        movieTypeLabel.setText(solo.getClassName());
        movieComArea.clear();
        movieRating.setRating(5);
        File newFile = new File("src/main/resources/org/example/scene/images/" + solo.getCoverPath());
        //System.out.println(newFile.getAbsoluteFile().toURI());
        movieCover.setImage(new Image(newFile.getAbsoluteFile().toURI().toString()));
        UIAdjistUtils.adjustImageBorder(movieCover, imageBorder);
    }
    public void initPersonInfoPane() {
        JdbcUtils jdbcUtils = new JdbcUtils();
        jdbcUtils.getConnection();
        String sql = "select * from `commonUser` where username = ?";
        List<Object> params = new ArrayList<>();
        params.add(Session.getUsername());

        try {
            CommonUser userNow = jdbcUtils.findSimpleProResult(sql, params, CommonUser.class);

            // 设置显示对应的原始数据
            usernameField.setText(userNow.getUsername());
            passwordField.setText(userNow.getPassword());
            AgeField.setText(userNow.getAge());
            sidField.setText(userNow.getSid());
            telField.setText(userNow.getTel());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        @Override
        public void start(Stage primaryStage){

        }

        public void setMainApp(Main mainApp) {this.mainApp = mainApp;
        }

    public void handleChange(ActionEvent actionEvent) {
    }

    public void handleNameOrderPie(ActionEvent actionEvent) {
    }

    public void handleToMovie() {
        //subscriptedPane.setVisible(false);
        personInfoPane.setVisible(false);
        moviePane.setVisible(true);
    }

    public void handleToCommonUser() {
        moviePane.setVisible(false);
        //subscriptedPane.setVisible(false);
        personInfoPane.setVisible(true);
        initPersonInfoPane();
    }

    public void handleLogout(MouseEvent mouseEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("scene/Login.fxml"));
            Parent loginView = loader.load();
            LoginController loginController = loader.getController();
            mainApp.getPrimaryStage().setScene(new Scene(loginView, 1152, 640));
            mainApp.getPrimaryStage().show();
            loginController.setMainApp(mainApp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleRate() {

        JdbcUtils jdbcUtils=new JdbcUtils();
        jdbcUtils.getConnection();
        String Usql = "select * from `commonUser` where username = ?";
        List<Object> Uparams = new ArrayList<>();
        Uparams.add(Session.getUsername());
        try {
            CommonUser userNow = jdbcUtils.findSimpleProResult(Usql, Uparams, CommonUser.class);
            String sql ="insert into `rate`(uid,mid,rate)"+"values(?,?,?)";
            List<Object> params=new ArrayList<>();
            params.add(userNow.getId());
            params.add(movieTableView.getSelectionModel().getSelectedItem().getId());
            params.add((int)(movieRating.getRating()));
            try{
                jdbcUtils.updateByPreparedStatement(sql,params);
            }catch (SQLException e){
                e.printStackTrace();
            }
            //System.out.println(params);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
    @FXML
    public void handleComment(ActionEvent actionEvent) {
        JdbcUtils jdbcUtils=new JdbcUtils();
        jdbcUtils.getConnection();
        String Usql = "select * from `commonUser` where username = ?";
        List<Object> Uparams = new ArrayList<>();
        Uparams.add(Session.getUsername());
        try {
            CommonUser userNow = jdbcUtils.findSimpleProResult(Usql, Uparams, CommonUser.class);
            String sql ="insert into `order`(uid,mid,comment)"+"values(?,?,?)";
            List<Object> params=new ArrayList<>();
            params.add(userNow.getId());
            params.add(movieTableView.getSelectionModel().getSelectedItem().getId());
            params.add((movieComArea.getText()));
            movieComArea.clear();
            try{
                jdbcUtils.updateByPreparedStatement(sql,params);
            }catch (SQLException e){
                e.printStackTrace();
            }
            //System.out.println(params);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
