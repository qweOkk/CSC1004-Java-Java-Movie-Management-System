package org.example.scene;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.example.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import org.example.Session;
import org.example.models.*;
import org.example.utils.*;

import java.io.*;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
public class AdminViewController extends Application{
    @FXML
    public Label commentField;
    @FXML
    private TableColumn<Movie, Integer> movieIdColumn;

    @FXML
    private TableColumn<CommonUser, Integer> commonUserIdColumn;

    @FXML
    private TextField adminIdField;

    @FXML
    private Rectangle imageBorder;

    @FXML
    private TextField movieActorField;

    @FXML
    private TextArea movieIntroArea;

    @FXML
    private BorderPane commonUserPane;

    @FXML
    private Label toStatisticButton;

    @FXML
    private TextField commonUserIdField;

    @FXML
    private ChoiceBox<String> movieClassNameBox;

    @FXML
    private ImageView movieCover;

    @FXML
    private Label userNameLabel;

    @FXML
    private Label toCommonUserButton;

    @FXML
    private Button imageButton;

    @FXML
    private TextField movieDirectorField;

    @FXML
    private BorderPane adminPane;

    @FXML
    private TextField commonUserAgeField;

    @FXML
    private TextField commonUserTelField;

    @FXML
    private TextField commonUserSidField;

    @FXML
    private TextField movieNameField;

    @FXML
    private TextField commonUserNameField;

    @FXML
    private TableColumn<Admin, String> adminPasswordColumn;

    @FXML
    private Label toMovieButton;

    @FXML
    private TableView<Admin> adminTableView;

    @FXML
    private TextField movieIdField;

    @FXML
    private TableColumn<Admin, String> adminNameColumn;

    @FXML
    private TextField commonUserpassField;

    @FXML
    private TableColumn<Movie, String> movieNameColumn;

    @FXML
    private Label adminIdLabel;

    @FXML
    private TextField adminNameField;

    @FXML
    private TableView<CommonUser> commonUserTableView;

    @FXML
    private TableColumn<CommonUser, String> commonUserNameColumn;

    @FXML
    private Label logOutButton;

    @FXML
    private TextField movieYearPriceField;

    @FXML
    private Label adminPasswordLabel;

    @FXML
    private TableColumn<Admin, Integer> adminIdColumn;

    @FXML
    private Label adminNameLabel;

    @FXML
    private TextField adminPasswordField;

    @FXML
    private Label toAdminButton;

    @FXML
    private TableView<Movie> movieTableView;

    @FXML
    private BorderPane moviePane;

    private Main mainApp;

    @FXML
    private BorderPane commentPane;

    @FXML
    private TableView<Comment> commentTableView;

    @FXML
    private TableColumn<Comment, Integer> commentIdColumn;

    @FXML
    private TableColumn<Comment,String> commentNameColumn;

    @FXML
    private TableColumn<Comment, String> commentMovieColumn;
    @FXML
    private BorderPane statisticPane;

    @FXML
    private BorderPane ratingPane;

    @FXML
    private CategoryAxis xAxis= new CategoryAxis();
    @FXML
    private NumberAxis yAxis;
    @FXML
    private BarChart<String, Number> movieBarChart;
    @FXML
    private TableView<Movie> statisticTableView;

    @FXML
    private TableColumn<Movie,String> statisticMovieColumn;
    @FXML
    private CategoryAxis typexAxis= new CategoryAxis();
    @FXML
    private NumberAxis typeyAxis;
    @FXML
    private BarChart<String, Number> typeBarChart;
    @FXML
    private CategoryAxis agexAxis;

    @FXML
    private NumberAxis ageyAxis;
    @FXML
    private BarChart<String, Number> ageBarChart;

    @FXML
    private Rectangle commentBorder;
    @FXML
    private ImageView commontCover;
    @FXML
    private BorderPane movieTypePane;

    @FXML
    private BorderPane userAgePane;
    private ObservableList<Admin> adminData = FXCollections.observableArrayList();
    private ObservableList<Movie> movieData = FXCollections.observableArrayList();
    private ObservableList<CommonUser> commonUsersData = FXCollections.observableArrayList();
    private ObservableList<Comment> commentsData = FXCollections.observableArrayList();
    private ObservableList<Rate> ratesData = FXCollections.observableArrayList();
    private ObservableList<String> choiceItems = FXCollections.observableArrayList();

    private File imageFile;

    /**
     * initialize the tables.
     */
    @FXML
    private void initialize() {
        adminIdColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        adminNameColumn.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
        adminPasswordColumn.setCellValueFactory(cellDate -> cellDate.getValue().passwordProperty());
        adminTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> updateAdminDetail(newValue)
        );
        commonUserIdColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        commonUserNameColumn.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
        commonUserTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> updateCommonDetail(newValue)
        );

        movieIdColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        movieNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        movieTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> updateMovieDetail(newValue)
        );
        commentIdColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        commentNameColumn.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
        commentMovieColumn.setCellValueFactory(cellDate -> cellDate.getValue().movienameProperty());
        commentTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> updateCommentDetail(newValue)
        );
        statisticMovieColumn.setCellValueFactory(cellData ->cellData.getValue().nameProperty());
        statisticTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> updateStatistic(newValue)
        );
        initAdminTable();
        initCommonUserTable();
        initMovieTable();
        initCommentTable();
        initStatisticView();
        initUserNameLabel();
        initType();
        initAge();
    }
    /**
     * initialize the username table.
     */
    public void initUserNameLabel() {
        if (Session.getType().equals("commonUser")) {
            userNameLabel.setText(Session.getUsername());
        } else if (Session.getType().equals("admin")) {
            userNameLabel.setText(Session.getUsername());
        }
    }
    /**
     * initialize the Admin table.
     */
    public void initAdminTable() {
        JdbcUtils jdbcUtils = new JdbcUtils();
        jdbcUtils.getConnection();
        String sql = "select * from `admin`";
        List<Object> params = new ArrayList<>();
        try {
            List<Admin> admins = jdbcUtils.findMoreProResult(sql, params, Admin.class);
            adminData.clear();
            adminData.addAll(admins);
            adminTableView.setItems(adminData);
            adminTableView.getSelectionModel().selectFirst();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * initialize the CommonUser table.
     */
    private void initCommonUserTable() {
        JdbcUtils jdbcUtils = new JdbcUtils();
        jdbcUtils.getConnection();
        String sql = "select * from `commonUser`;";
        List<Object> params = new ArrayList<>();
        try {
            List<CommonUser> commonUsers = jdbcUtils.findMoreProResult(sql, params, CommonUser.class);
            commonUsersData.clear();
            commonUsersData.addAll(commonUsers);
            commonUserTableView.setItems(commonUsersData);
            commonUserTableView.getSelectionModel().selectFirst();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * initialize the Movie table.
     */
    private void initMovieTable() {
        JdbcUtils jdbcUtils = new JdbcUtils();
        jdbcUtils.getConnection();
        String sql = "select a.id, a.coverPath, a.`name`, a.director, a.actor, a.intro, b.className from `movie` a, `mClass` b where a.classNumber = b.id;";
        List<Object> params = new ArrayList<>();
        try {
            List<Movie> movies = jdbcUtils.findMoreProResult(sql, params, Movie.class);
            movieData.clear();
            movieData.addAll(movies);
            movieTableView.setItems(movieData);
            movieTableView.getSelectionModel().selectFirst();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * initialize the Comment table.
     */
    private void initCommentTable() {
        JdbcUtils jdbcUtils = new JdbcUtils();
        jdbcUtils.getConnection();
        String sql = "select * from `comment`;";
        List<Object> params = new ArrayList<>();
        try {
            List<Comment> comments = jdbcUtils.findMoreProResult(sql, params, Comment.class);
            System.out.println(comments);
            commentsData.clear();
            commentsData.addAll(comments);
            commentTableView.setItems(commentsData);
            commentTableView.getSelectionModel().selectFirst();
            commentField.setText(commentTableView.getSelectionModel().getSelectedItem().getComment());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * initialize the rate page of the Statistic page.
     */
    private void initStatisticView() {
        JdbcUtils jdbcUtils = new JdbcUtils();
        jdbcUtils.getConnection();
        String sql= " select a.id, a.coverPath, a.`name`, a.director, a.actor, a.intro, b.className from `movie` a, `mClass` b where a.classNumber = b.id;";
        List<Object> params =new ArrayList<>();
        try{
            List<Movie> movies =jdbcUtils.findMoreProResult(sql, params, Movie.class);
            //System.out.println(movies);
            movieData.clear();
            movieData.addAll(movies);
            statisticTableView.setItems(movieData);
            statisticTableView.getSelectionModel().selectFirst();
            String rsql= "select * from `rate`  ;";
            List<Object> rparams = new ArrayList<>();
            try {
                List<Rate> rates =jdbcUtils.findMoreProResult(rsql, rparams, Rate.class);
                XYChart.Series<String, Number> series = new XYChart.Series<>();
                series.setName("rate");

                for (Rate rate : rates) {


                    if (rate.getmoviename().equals(statisticTableView.getSelectionModel().getSelectedItem().getName())) {
                        //ratesName.add(rates.get(i).getusername());
                        series.getData().add(new XYChart.Data<>(rate.getusername(), rate.getRate()));
                    }
                }
                //System.out.println(ratesName);
                //xAxis.setCategories(ratesName);
                movieBarChart.getData().clear();
                movieBarChart.getData().add(series);
                //System.out.println(movieBarChart.getData());
                //System.out.println(rates);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * initialize the age page of the Statistic page.
     */
    private void initAge() {
        JdbcUtils jdbcUtils = new JdbcUtils();
        jdbcUtils.getConnection();
        String sql = "select * from `commonUser`;";
        List<Object> params = new ArrayList<>();
        try {
            List<CommonUser> commonUsers = jdbcUtils.findMoreProResult(sql, params, CommonUser.class);
            agexAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList(
                    "0-40", "41-80", "81-120", "121-160",">=161")));
            String[] as=new String[5];
            as[0]="0-40";
            as[1]="41-80";
            as[2]="81-120";
            as[3]="121-160";
            as[4]=">=161";
            int[] num =new int[10];
            for(int i=0;i<10;i++){
                num[i]=0;
            }
            for(CommonUser commonUser : commonUsers){
                if(commonUser.getAge()>=0&&commonUser.getAge()<=40){
                    num[0]++;
                }
                if(commonUser.getAge()>=41&&commonUser.getAge()<=80){
                    num[1]++;
                }
                if(commonUser.getAge()>=81&&commonUser.getAge()<=120){
                    num[2]++;
                }
                if(commonUser.getAge()>=121&&commonUser.getAge()<=160){
                    num[3]++;
                }
                if(commonUser.getAge()>=161){
                    num[4]++;
                }
            }
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            ObservableList<String> className = FXCollections.observableArrayList();
            series.setName("Age");
            for(int i=0;i<5;i++){
                series.getData().add(new XYChart.Data<>(as[i],num[i]));
            }

            for(int i=0;i<5;i++){
                System.out.println(num[i]);
            }
            ageBarChart.getData().clear();
            ageBarChart.getData().addAll(series);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * initialize the type page of the Statistic page.
     */
    private void initType() {
        JdbcUtils jdbcUtils = new JdbcUtils();
        jdbcUtils.getConnection();
        String sql = "select  a.`name`, b.className from `movie` a, `mClass` b where a.classNumber = b.id;";
        List<Object> params = new ArrayList<>();
        try {
            List<Movie> movies = jdbcUtils.findMoreProResult(sql, params, Movie.class);
            System.out.println(movies);
            sql = "select * from `mClass`";
            List<Object> mcparams = new ArrayList<>();
            try{
                List<MovieClass> id = jdbcUtils.findMoreProResult(sql, mcparams, MovieClass.class);
                int length= id.size(),len = movies.size();
                int[] num =new int[length];
                for(int i=0;i<length;i++){
                    num[i]=0;
                }
                for (Movie movie : movies) {
                    for (int j = 0; j < length; j++) {
                        if (movie.getClassName().equals(id.get(j).getclassName())) {
                            num[j]++;
                            //System.out.println(j);
                        }
                    }
                }
                XYChart.Series<String, Number> mseries = new XYChart.Series<>();
                ObservableList<String> className = FXCollections.observableArrayList();
                mseries.setName("Movie Types");
                for(int i=0;i<length;i++){
                    className.add(id.get(i).getclassName());
                    mseries.getData().add(new XYChart.Data<>(id.get(i).getclassName(),num[i]));
                }
                typexAxis.setCategories(className);
                typeBarChart.getData().clear();
                typeBarChart.getData().addAll(mseries);
            }catch (Exception e){
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Update the admin page.
     */
    private void updateAdminDetail(Admin admin) {
        if (admin != null) {
            adminIdField.setText(Integer.toString(admin.getId()));
            adminNameField.setText(admin.getUsername());
            adminPasswordField.setText(admin.getPassword());
        }
    }
    /**
     * Update the CommonUser page.
     */
    private void updateCommonDetail(CommonUser solo) {
        if (solo != null) {
            commonUserIdField.setText(Integer.toString(solo.getId()));
            commonUserNameField.setText(solo.getUsername());
            commonUserpassField.setText(solo.getPassword());
            commonUserSidField.setText(solo.getSid());
            commonUserTelField.setText(solo.getTel());
            commonUserAgeField.setText(String.valueOf(solo.getAge()));
        }
    }
    /**
     * Update the Comment page.
     */
    private void updateCommentDetail(Comment solo) {
        if(solo != null){
            commentField.setText(commentTableView.getSelectionModel().getSelectedItem().getComment());
            commontCover.setImage(null);
            if(!solo.getPicture().equals("non_exist")){
                File newFile = new File("src/main/resources/org/example/scene/images/" + solo.getPicture());
                System.out.println(newFile.getAbsoluteFile().toURI());

                commontCover.setImage(new Image(newFile.getAbsoluteFile().toURI().toString()));
                UIAdjistUtils.adjustImageBorder(commontCover, commentBorder);
                imageFile = new File("src/main/resources/org/example/scene/images/" + solo.getPicture());
                System.out.println(imageFile.getAbsolutePath());
            }

        }

    }
    /**
     * Update the Movie page.
     */
    private void updateMovieDetail(Movie solo) {
        if (solo != null) {
            movieIdField.setText(Integer.toString(solo.getId()));
            movieNameField.setText(solo.getName());
            movieDirectorField.setText(solo.getDirector());
            movieActorField.setText(solo.getActor());
            //Handle the choice box.
            JdbcUtils jdbcUtils = new JdbcUtils();
            jdbcUtils.getConnection();
            String sql = "select className from `mClass`;";
            List<Object> params = new ArrayList<>();

            try {
                List<Map<String, Object>> classNames = jdbcUtils.findModeResult(sql, params);
                System.out.println(classNames);
                choiceItems.clear();
                for (Map<String, Object> className : classNames) {
                    choiceItems.add(className.get("className").toString());
                }
                movieClassNameBox.setItems(choiceItems);
                movieClassNameBox.setValue(solo.getClassName());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            movieIntroArea.setText(solo.getIntro());
            //System.out.println(solo.getCoverPath());
            File newFile = new File("src/main/resources/org/example/scene/images/" + solo.getCoverPath());
            //System.out.println(newFile.getAbsoluteFile().toURI());

            movieCover.setImage(new Image(newFile.getAbsoluteFile().toURI().toString()));
            UIAdjistUtils.adjustImageBorder(movieCover, imageBorder);
            imageFile = new File("src/main/resources/org/example/scene/images/" + solo.getCoverPath());
            System.out.println(imageFile.getAbsolutePath());
        }
    }
    /**
     * Update the Rate page of the statistic page.
     */
    private void updateStatistic(Movie solo) {
        if(solo!=null){
            JdbcUtils jdbcUtils = new JdbcUtils();
            jdbcUtils.getConnection();
            String rsql= "select * from `rate`  ;";
            List<Object> rparams = new ArrayList<>();
            try {
                List<Rate> rates =jdbcUtils.findMoreProResult(rsql, rparams, Rate.class);
                XYChart.Series<String, Number> series = new XYChart.Series<>();
                series.setName("rate");

                ObservableList<String> ratesName = FXCollections.observableArrayList();
                ratesName.clear();
                for(int i=0;i<rates.size();i++){
                    //System.out.println(rates.get(i));


                    if(rates.get(i).getmoviename().equals(statisticTableView.getSelectionModel().getSelectedItem().getName())){
                        ratesName.add(rates.get(i).getusername());
                        series.getData().add(new XYChart.Data<>(rates.get(i).getusername(), rates.get(i).getRate()));
                    }
                }
                System.out.println(ratesName);
                xAxis.getCategories().clear();
                xAxis.setCategories(ratesName);
                movieBarChart.getData().clear();
                movieBarChart.getData().add(series);
                //System.out.println(rates);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void start(Stage primaryStage){

        }

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Turn to Movie page.
     */
    @FXML
    void handleToMovie() {
        commonUserPane.setVisible(false);
        adminPane.setVisible(false);
        commentPane.setVisible(false);
        statisticPane.setVisible(false);
        initMovieTable();
        moviePane.setVisible(true);

    }
    /**
     * Turn to Statistic page.
     */
    @FXML
    void handleToStatistic() {
        commonUserPane.setVisible(false);
        adminPane.setVisible(false);
        commentPane.setVisible(false);
        moviePane.setVisible(false);
        //initStatisticView();
        movieTypePane.setVisible(false);
        userAgePane.setVisible(false);
        initStatisticView();
        ratingPane.setVisible(true);
        statisticPane.setVisible(true);
    }
    /**
     * Turn to CommonUser page.
     */
    @FXML
    void handleToCommonUser() {
        moviePane.setVisible(false);
        adminPane.setVisible(false);
        commentPane.setVisible(false);
        statisticPane.setVisible(false);
        initCommonUserTable();
        commonUserPane.setVisible(true);
    }
    /**
     * Turn to Admin page.
     */
    @FXML
    void handleToAdmin() {
        moviePane.setVisible(false);
        commonUserPane.setVisible(false);
        commentPane.setVisible(false);
        statisticPane.setVisible(false);
        initAdminTable();
        adminPane.setVisible(true);
    }
    /**
     * Turn to Comment page.
     */
    @FXML
    void handleToComment(){
        moviePane.setVisible(false);
        commonUserPane.setVisible(false);
        adminPane.setVisible(false);
        statisticPane.setVisible(false);
        initCommentTable();
        commentPane.setVisible(true);
    }
    /**
     * Turn to Rate page.
     */
    @FXML
    void handleToRate() {
        movieTypePane.setVisible(false);
        userAgePane.setVisible(false);
        initStatisticView();
        ratingPane.setVisible(true);
    }
    /**
     * Turn to Age page.
     */
    @FXML
    void handleToAge() {
        movieTypePane.setVisible(false);
        ratingPane.setVisible(false);
        initAge();
        userAgePane.setVisible(true);
    }
    /**
     * Turn to Type page.
     */
    @FXML
    void handleToType() {
        ratingPane.setVisible(false);
        userAgePane.setVisible(false);
        initType();
        movieTypePane.setVisible(true);
    }
    /**
     * Log out and back to the login page.
     */
    @FXML
    void handleLogout() {
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
    /**
     * Update the Admin.
     */
    @FXML
    private void handleSubmitAdmin() {
        Admin oldInfo = adminTableView.getSelectionModel().getSelectedItem();
        String id = adminIdField.getText();
        if (id == null || id.length() == 0) {
            DialogUtils.tips(mainApp.getPrimaryStage(), "ID should not be empty,");
            return;
        } else {
            try {
                Integer.parseInt(id);
            } catch (Exception e) {
                DialogUtils.tips(mainApp.getPrimaryStage(), "ID should only be numbers!");
                return;
            }
        }
        Admin newInfo = new Admin(Integer.parseInt(adminIdField.getText()), adminNameField.getText(), adminPasswordField.getText());
        String errorMessage = InputChecker.adminInfoUpdateCheck(oldInfo, newInfo);
        if (errorMessage == null) {
            JdbcUtils jdbcUtils = new JdbcUtils();
            jdbcUtils.getConnection();
            String sql = "update `admin` set username = ?, password = ? where id = ?";
            List<Object> params = new ArrayList<>();
            params.add(newInfo.getUsername());
            params.add(newInfo.getPassword());
            params.add(newInfo.getId());
            try {
                jdbcUtils.updateByPreparedStatement(sql, params);
                DialogUtils.good(mainApp.getPrimaryStage(), "Update successfully!");
                // Update it.
                if (Session.getId() == newInfo.getId()) {
                    Session.setUsername(newInfo.getUsername());
                    initUserNameLabel();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            initAdminTable();
        } else {
            DialogUtils.tips(mainApp.getPrimaryStage(), errorMessage);
        }
    }
    /**
     * Add an Admin.
     */
    @FXML
    private void handleNewAdmin() {
        String errorMessage = InputChecker.adminSignUpCheck(adminIdField.getText(), adminNameField.getText(), adminPasswordField.getText());
        if (errorMessage == null) {
            JdbcUtils jdbcUtils = new JdbcUtils();
            jdbcUtils.getConnection();
            String sql = "insert into `admin`(id, username, password) values(?, ?, ?)";
            List<Object> params = new ArrayList<>();
            params.add(Integer.parseInt(adminIdField.getText()));
            params.add(adminNameField.getText());
            params.add(adminPasswordField.getText());

            try {
                jdbcUtils.updateByPreparedStatement(sql, params);
                DialogUtils.good(mainApp.getPrimaryStage(), "Add successfully!");
                initAdminTable();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            DialogUtils.tips(mainApp.getPrimaryStage(), errorMessage);
        }
    }

    /**
     * Delete the Admin.
     */
    @FXML
    private void handleDeleteAdmin() {
        int index = adminTableView.getSelectionModel().getFocusedIndex();
        Admin admin = adminTableView.getSelectionModel().getSelectedItem();
        if (admin != null) {
            JdbcUtils jdbcUtils = new JdbcUtils();
            jdbcUtils.getConnection();
            String sql = "delete from `admin` where id = ?";
            List<Object> params = new ArrayList<>();
            params.add(admin.getId());

            try {
                if (DialogUtils.confirm(mainApp.getPrimaryStage(), "Do you really want to delete this admin?")) {
                    jdbcUtils.updateByPreparedStatement(sql, params);
                    adminTableView.getItems().remove(index);
                    DialogUtils.good(mainApp.getPrimaryStage(), "Delete successfully!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * Add a CommonUser.
     */
    @FXML
    private void handleNewCommonUser() {
        String errorMessage = InputChecker.commonUserSignUpCheck(commonUserIdField.getText(),
                commonUserNameField.getText(),
                commonUserpassField.getText(),
                Integer.valueOf(commonUserAgeField.getText()),
                commonUserTelField.getText(),
                commonUserSidField.getText());
        if (errorMessage == null) {
            JdbcUtils jdbcUtils = new JdbcUtils();
            jdbcUtils.getConnection();
            String sql = "insert into `commonUser`(id, username, password, age, Tel, sid) values(?, ?, ?, ?, ?, ?)";
            List<Object> params = new ArrayList<>();
            params.add(Integer.parseInt(commonUserIdField.getText()));
            params.add(commonUserNameField.getText());
            params.add(commonUserpassField.getText());
            params.add(commonUserAgeField.getText());
            params.add(commonUserTelField.getText());
            params.add(commonUserSidField.getText());

            try {
                jdbcUtils.updateByPreparedStatement(sql, params);
                DialogUtils.good(mainApp.getPrimaryStage(), "Add successfully!");
                initCommonUserTable();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            DialogUtils.tips(mainApp.getPrimaryStage(), errorMessage);
        }
    }
    /**
     * Update a CommonUser.
     */
    @FXML
    private void handleUpdateCommonUser() {
        CommonUser oldInfo =  commonUserTableView.getSelectionModel().getSelectedItem();
        String errorMessage;
        String id = commonUserIdField.getText();
        if (id == null || id.length() == 0) {
            DialogUtils.tips(mainApp.getPrimaryStage(), "ID should not be empty!");
            return;
        } else {
            try {
                Integer.parseInt(id);
            } catch (Exception e) {
                DialogUtils.tips(mainApp.getPrimaryStage(), "ID should be numbers!");
                return;
            }
        }
        CommonUser newInfo = new CommonUser(Integer.parseInt(commonUserIdField.getText()),
                commonUserNameField.getText(),
                commonUserpassField.getText(),
                Integer.valueOf(commonUserAgeField.getText()),
                commonUserTelField.getText(),
                commonUserSidField.getText());
        errorMessage = InputChecker.commonUserUpdateCheck(oldInfo, newInfo);
        if (errorMessage == null) {
            JdbcUtils jdbcUtils = new JdbcUtils();
            jdbcUtils.getConnection();
            String sql = "update `commonUser` set username = ?, password = ?, age = ?, tel = ?, sid = ? where id = ?;";
            List<Object> params = new ArrayList<>();
            params.add(newInfo.getUsername());
            params.add(newInfo.getPassword());
            params.add(newInfo.getAge());
            params.add(newInfo.getTel());
            params.add(newInfo.getSid());
            params.add(newInfo.getId());
            try {
                jdbcUtils.updateByPreparedStatement(sql, params);
                DialogUtils.good(mainApp.getPrimaryStage(), "Update successfully!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            initCommonUserTable();
        } else {
            DialogUtils.tips(mainApp.getPrimaryStage(), errorMessage);
        }
    }
    /**
     * Delete the CommonUser.
     */
    @FXML
    private void handleDeleteCommonUser() {
        int index = commonUserTableView.getSelectionModel().getFocusedIndex();
        CommonUser commonUser = commonUserTableView.getSelectionModel().getSelectedItem();
        if (commonUser != null) {
            JdbcUtils jdbcUtils = new JdbcUtils();
            jdbcUtils.getConnection();
            String sql = "delete from `commonUser` where id = ?";
            String Osql="delete from `rate` where uid = ?";
            String Csql="delete from `comment` where uid = ?";
            List<Object> params = new ArrayList<>();
            params.add(commonUser.getId());

            try {
                if (DialogUtils.confirm(mainApp.getPrimaryStage(), "Attention!")){
                    jdbcUtils.updateByPreparedStatement(sql, params);
                    jdbcUtils.updateByPreparedStatement(Osql,params);
                    jdbcUtils.updateByPreparedStatement(Csql,params);
                    commonUserTableView.getItems().remove(index);
                    DialogUtils.good(mainApp.getPrimaryStage(), "Successfully deleted");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * Add a Movie.
     */
    @FXML
    private void handleNewMovie() {
        String errorMessage = InputChecker.movieSignUpCheck(movieIdField.getText(),
                imageFile,
                movieNameField.getText(),
                movieDirectorField.getText(),
                movieActorField.getText(),
                movieClassNameBox.getValue(),
                movieIntroArea.getText());
        if (errorMessage == null) {
            JdbcUtils jdbcUtils = new JdbcUtils();
            jdbcUtils.getConnection();
            String sql = "select id from `mClass` where className = ?";
            List<Object> params = new ArrayList<>();
            params.add(movieClassNameBox.getValue());
            try {
                Map<String, Object> id = jdbcUtils.findSimpleResult(sql, params);
                sql = "insert into `movie`(id, coverPath, name, director, actor, intro, classNumber) values(?, ?, ?, ?, ?, ?, ?)";
                params.clear();
                params.add(movieIdField.getText());
                params.add(imageFile.getName());
                params.add(movieNameField.getText());
                params.add(movieDirectorField.getText());
                params.add(movieActorField.getText());
                params.add(movieIntroArea.getText());
                params.add(id.get("id"));
                jdbcUtils.updateByPreparedStatement(sql, params);
                DialogUtils.good(mainApp.getPrimaryStage(), "Add successfully!");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            //Copy it to the folder.
            ImageUtils.save(imageFile);
            initMovieTable();
        } else {
            DialogUtils.tips(mainApp.getPrimaryStage(), errorMessage);
        }
    }

    /**
     * Delete the Movie.
     */
    @FXML
    private void handleDeleteMovie() {
        int index = movieTableView.getSelectionModel().getFocusedIndex();
        Movie movie = movieTableView.getSelectionModel().getSelectedItem();

        if (movie != null) {
            JdbcUtils jdbcUtils = new JdbcUtils();
            jdbcUtils.getConnection();
            String sql = "delete from `movie` where id = ?";
            String Osql="delete from `rate` where mid = ?";
            String Csql="delete from `comment` where mid = ?";
            List<Object> params = new ArrayList<>();
            params.add(movie.getId());

            try {
                if (DialogUtils.confirm(mainApp.getPrimaryStage(), "Do you really delete the movie?")) {
                    jdbcUtils.updateByPreparedStatement(sql, params);
                    jdbcUtils.updateByPreparedStatement(Osql, params);
                    jdbcUtils.updateByPreparedStatement(Csql, params);
                    movieTableView.getItems().remove(index);
                    ImageUtils.delete(movie.getCoverPath());
                    DialogUtils.good(mainApp.getPrimaryStage(), "Delete successfully!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Update the Movie!
     */
    @FXML
    private void handleUpdateMovie() {
        Movie oldInfo = movieTableView.getSelectionModel().getSelectedItem();
        String errorMessage;
        String foo = movieIdField.getText();
        if (foo == null || foo.length() == 0) {
            DialogUtils.tips(mainApp.getPrimaryStage(), "ID should not be empty!");
            return;
        } else {
            try {
                Integer.parseInt(foo);
            } catch (Exception e) {
                DialogUtils.tips(mainApp.getPrimaryStage(), "ID should be numbers!");
                return;
            }
        }

        if (imageFile == null) {
            DialogUtils.tips(mainApp.getPrimaryStage(), "The image has been used!");
            return;
        }

        Movie newInfo = new Movie(Integer.parseInt(movieIdField.getText()),
                imageFile.getName(),
                movieNameField.getText(),
                movieDirectorField.getText(),
                movieActorField.getText(),
                movieIntroArea.getText(),
                movieClassNameBox.getValue());
        errorMessage = InputChecker.movieUpdateCheck(oldInfo, newInfo);

        //Update the SQL
        if (errorMessage == null) {
            JdbcUtils jdbcUtils = new JdbcUtils();
            jdbcUtils.getConnection();
            String sql = "select id from `mClass` where className = ?";
            List<Object> params = new ArrayList<>();
            params.add(newInfo.getClassName());
            System.out.println(newInfo.getClassName());

            try {
                Map<String, Object> id = jdbcUtils.findSimpleResult(sql, params);
                sql = "update `movie` set coverPath = ?, name = ?, director = ?, actor = ?,  intro = ?, classNumber = ? where id = ?;";
                params.clear();
                params.add(newInfo.getCoverPath());
                params.add(newInfo.getName());
                params.add(newInfo.getDirector());
                params.add(newInfo.getActor());
                params.add(newInfo.getIntro());
                params.add(id.get("id"));
                params.add(newInfo.getId());
                jdbcUtils.updateByPreparedStatement(sql, params);
                DialogUtils.good(mainApp.getPrimaryStage(), "Update successfully!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (!oldInfo.getCoverPath().equals(newInfo.getCoverPath())){
                ImageUtils.save(imageFile);
                ImageUtils.delete(oldInfo.getCoverPath());
            }

            initMovieTable();
        } else {
            DialogUtils.tips(mainApp.getPrimaryStage(), errorMessage);
        }
    }

    /**
     * Select the image.
     */
    @FXML
    private void handleSwitchImage() {
        imageFile = ImageUtils.choose(mainApp);
        // Print the path.
        System.out.println();
        // Change the image.
        try {
            if (imageFile != null) {
                String localUrl = imageFile.toURI().toURL().toString();
                movieCover.setImage(new Image(localUrl, true));
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

}
