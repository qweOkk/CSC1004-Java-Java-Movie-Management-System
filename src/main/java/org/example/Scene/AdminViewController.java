package org.example.scene;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import java.util.List;
import java.util.Map;
public class AdminViewController extends Application{

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
    private TableView<Order> fooOrderTableView;
    @FXML
    private TableColumn<Order, Integer> fooOrderIdColumn;
    @FXML
    private TableColumn<Order, String> fooUserNameColumn;
    @FXML
    private TableColumn<Order, String>  fooMagazineNameColumn;
    @FXML
    private TableColumn<Order, Integer> fooTotalPriceColumn;
    @FXML
    private TableView<MovieClass> fooMagazineClassTableView;
    @FXML
    private TableColumn<MovieClass, Integer> fooMagazineClassIdColumn;
    @FXML
    private TableColumn<MovieClass, String> fooMagazineClassNameColumn;
    @FXML
    private TextField fooMagazineClassIdField;
    @FXML
    private TextField fooMagazineClassNameField;
    @FXML
    private TextField fooOrderIdField;
    @FXML
    private ComboBox<String> fooOrderUsernameBox;
    @FXML
    private ComboBox<String> fooOrderMagazineNameBox;
    @FXML
    private ChoiceBox<String> fooMonthBox;
    @FXML
    private ChoiceBox<String> fooCopiesNumBox;
    @FXML
    private TextField fooTotalPriceField;

    private ObservableList<Admin> adminData = FXCollections.observableArrayList();
    private ObservableList<Movie> magazineData = FXCollections.observableArrayList();
    private ObservableList<CommonUser> commonUsersData = FXCollections.observableArrayList();
    private ObservableList<Order> seOrderData = FXCollections.observableArrayList();
    private ObservableList<Order> fooOrderData = FXCollections.observableArrayList();
    private ObservableList<MovieClass> fooMagazineClassData = FXCollections.observableArrayList();

    // 各种选项盒子的数据
    private ObservableList<String> choiceItems = FXCollections.observableArrayList();
    private ObservableList<String> seMagazineNames = FXCollections.observableArrayList();
    private ObservableList<String> seMagazineClasses = FXCollections.observableArrayList();
    private ObservableList<String> seUserNames = FXCollections.observableArrayList();
    private ObservableList<String> anUserNames = FXCollections.observableArrayList();
    private ObservableList<String> anMagazineNames = FXCollections.observableArrayList();
    private ObservableList<String> fooOrderUsernames = FXCollections.observableArrayList();
    private ObservableList<String> fooOrderMagazineNames = FXCollections.observableArrayList();

    //杂志封面临时文件
    private File imageFile;

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

        /*fooMagazineClassIdColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        fooMagazineClassNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        fooMagazineClassTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> updateMovieClassDetail(newValue)
        );*/

        initAdminTable();
        initCommonUserTable();
        initMovieTable();
        //initSearchTable();
        //initStatisticView();
        initUserNameLabel();
        //initMagazineClassTableAndOrderTable();
    }

    public void initUserNameLabel() {
        if (Session.getType().equals("commonUser")) {
            userNameLabel.setText(Session.getUsername());
        } else if (Session.getType().equals("admin")) {
            userNameLabel.setText(Session.getUsername());
        }
    }

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
    private void initMovieTable() {
        JdbcUtils jdbcUtils = new JdbcUtils();
        jdbcUtils.getConnection();
        String sql = "select a.id, a.coverPath, a.`name`, a.director, a.actor, a.intro, b.className from `movie` a, `mClass` b where a.classNumber = b.id;";
        List<Object> params = new ArrayList<>();
        try {
            List<Movie> magazines = jdbcUtils.findMoreProResult(sql, params, Movie.class);
            magazineData.clear();
            magazineData.addAll(magazines);
            movieTableView.setItems(magazineData);
            movieTableView.getSelectionModel().selectFirst();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initBox(String sql, ComboBox<String> box, ObservableList<String> items) {
        JdbcUtils jdbcUtils = new JdbcUtils();
        jdbcUtils.getConnection();
        List<Object> params = new ArrayList<>();
        try {
            items.clear();
            List<Map<String, Object>> names = jdbcUtils.findModeResult(sql, params);
            for (Map<String, Object> name : names) {
                items.add(name.get("name").toString());
            }
            box.setItems(items);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateAdminDetail(Admin admin) {
        if (admin != null) {
            adminIdField.setText(Integer.toString(admin.getId()));
            adminNameField.setText(admin.getUsername());
            adminPasswordField.setText(admin.getPassword());
        }
    }

    private void updateCommonDetail(CommonUser solo) {
        if (solo != null) {
            commonUserIdField.setText(Integer.toString(solo.getId()));
            commonUserNameField.setText(solo.getUsername());
            commonUserpassField.setText(solo.getPassword());
            commonUserSidField.setText(solo.getSid());
            commonUserTelField.setText(solo.getTel());
            commonUserAgeField.setText(solo.getAge());
        }
    }
    private void updateMovieClassDetail(MovieClass solo) {
        if (solo != null) {
            fooMagazineClassIdField.setText(Integer.toString(solo.getId()));
            fooMagazineClassNameField.setText(solo.getName());
        }
    }

    private void updateMovieDetail(Movie solo) {
        if (solo != null) {
            movieIdField.setText(Integer.toString(solo.getId()));
            movieNameField.setText(solo.getName());
            movieDirectorField.setText(solo.getDirector());
            movieActorField.setText(solo.getActor());
            //处理ChoiceBox的相关问题
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

            movieCover.setImage(new Image(newFile.getAbsoluteFile().toURI().toString()));
            UIAdjistUtils.adjustImageBorder(movieCover, imageBorder);
            imageFile = new File("src/main/resources/org/example/scene/images/" + solo.getCoverPath());
            System.out.println(imageFile.getAbsolutePath());
        }
    }

    @Override
    public void start(Stage primaryStage){

        }

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }
    
    @FXML
    void handleToMovie() {
        commonUserPane.setVisible(false);
        adminPane.setVisible(false);
        initMovieTable();
        moviePane.setVisible(true);
    }

    @FXML
    void handleToStatistic(ActionEvent event) {

    }

    @FXML
    void handleToCommonUser() {
        moviePane.setVisible(false);
        adminPane.setVisible(false);
        initCommonUserTable();
        commonUserPane.setVisible(true);
    }

    @FXML
    void handleToAdmin() {
        moviePane.setVisible(false);
        commonUserPane.setVisible(false);
        initAdminTable();
        adminPane.setVisible(true);
    }

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
    @FXML
    private void handleSubmitAdmin() {
        Admin oldInfo = adminTableView.getSelectionModel().getSelectedItem();
        String id = adminIdField.getText();
        if (id == null || id.length() == 0) {
            DialogUtils.tips(mainApp.getPrimaryStage(), "id为空");
            return;
        } else {
            try {
                Integer.parseInt(id);
            } catch (Exception e) {
                DialogUtils.tips(mainApp.getPrimaryStage(), "id应只含有数字");
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
                DialogUtils.good(mainApp.getPrimaryStage(), "更新成功");
                // 更新一下现在在线的用户
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
                DialogUtils.good(mainApp.getPrimaryStage(), "添加成功");
                initAdminTable();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            DialogUtils.tips(mainApp.getPrimaryStage(), errorMessage);
        }
    }

    /**
     * 删除所选项，将该管理员删除
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
                if (DialogUtils.confirm(mainApp.getPrimaryStage(), "您真的要删掉该管理员吗？")) {
                    jdbcUtils.updateByPreparedStatement(sql, params);
                    adminTableView.getItems().remove(index);
                    DialogUtils.good(mainApp.getPrimaryStage(), "管理员删除成功");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void handleNewCommonUser() {
        String errorMessage = InputChecker.commonUserSignUpCheck(commonUserIdField.getText(),
                commonUserNameField.getText(),
                commonUserpassField.getText(),
                commonUserAgeField.getText(),
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
                DialogUtils.good(mainApp.getPrimaryStage(), "添加成功");
                initCommonUserTable();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            DialogUtils.tips(mainApp.getPrimaryStage(), errorMessage);
        }
    }
    @FXML
    private void handleUpdateCommonUser() {
        CommonUser oldInfo =  commonUserTableView.getSelectionModel().getSelectedItem();
        String errorMessage;
        String id = commonUserIdField.getText();
        if (id == null || id.length() == 0) {
            DialogUtils.tips(mainApp.getPrimaryStage(), "id为空");
            return;
        } else {
            try {
                Integer.parseInt(id);
            } catch (Exception e) {
                DialogUtils.tips(mainApp.getPrimaryStage(), "id应只含有数字");
                return;
            }
        }
        CommonUser newInfo = new CommonUser(Integer.parseInt(commonUserIdField.getText()),
                commonUserNameField.getText(),
                commonUserpassField.getText(),
                commonUserAgeField.getText(),
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
                DialogUtils.good(mainApp.getPrimaryStage(), "更新成功");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            initCommonUserTable();
        } else {
            DialogUtils.tips(mainApp.getPrimaryStage(), errorMessage);
        }
    }
    @FXML
    private void handleDeleteCommonUser() {
        int index = commonUserTableView.getSelectionModel().getFocusedIndex();
        CommonUser commonUser = commonUserTableView.getSelectionModel().getSelectedItem();
        if (commonUser != null) {
            JdbcUtils jdbcUtils = new JdbcUtils();
            jdbcUtils.getConnection();
            String sql = "delete from `commonUser` where id = ?";
            List<Object> params = new ArrayList<>();
            params.add(commonUser.getId());

            try {
                if (DialogUtils.confirm(mainApp.getPrimaryStage(), "请确认您要删掉这个用户，该用户的订单也会被一并删掉。")){
                    jdbcUtils.updateByPreparedStatement(sql, params);
                    commonUserTableView.getItems().remove(index);
                    DialogUtils.good(mainApp.getPrimaryStage(), "删除成功");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
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
                DialogUtils.good(mainApp.getPrimaryStage(), "添加成功");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            //复制文件到static文件夹
            ImageUtils.save(imageFile);
            initMovieTable();
        } else {
            DialogUtils.tips(mainApp.getPrimaryStage(), errorMessage);
        }
    }

    /**
     * 删除杂志数据的同时删除static中的图片文件。
     */
    @FXML
    private void handleDeleteMovie() {
        // 获取到所选项
        int index = movieTableView.getSelectionModel().getFocusedIndex();
        Movie movie = movieTableView.getSelectionModel().getSelectedItem();

        // 进行删除操作
        if (movie != null) {
            JdbcUtils jdbcUtils = new JdbcUtils();
            jdbcUtils.getConnection();
            String sql = "delete from `movie` where id = ?";
            List<Object> params = new ArrayList<>();
            params.add(movie.getId());

            try {
                if (DialogUtils.confirm(mainApp.getPrimaryStage(), "您确认要删掉这个杂志吗？")) {
                    jdbcUtils.updateByPreparedStatement(sql, params);
                    /*
                     *==================================================
                     *                 !!important!!
                     *     注意这里移动后会进行一个update所以不能用
                     *     imageFile.getPath()
                     *==================================================
                     */
                    movieTableView.getItems().remove(index);
                    ImageUtils.delete(movie.getCoverPath());
                    DialogUtils.good(mainApp.getPrimaryStage(), "删除成功");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 杂志更新的同时要记得保存新图片和删除老图片
     */
    @FXML
    private void handleUpdateMovie() {
        Movie oldInfo = movieTableView.getSelectionModel().getSelectedItem();
        String errorMessage;
        String foo = movieIdField.getText();
        if (foo == null || foo.length() == 0) {
            DialogUtils.tips(mainApp.getPrimaryStage(), "id为空");
            return;
        } else {
            try {
                Integer.parseInt(foo);
            } catch (Exception e) {
                DialogUtils.tips(mainApp.getPrimaryStage(), "id应只含有数字");
                return;
            }
        }

        if (imageFile == null) {
            DialogUtils.tips(mainApp.getPrimaryStage(), "该图片已被使用");
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

        //更新数据库
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
                DialogUtils.good(mainApp.getPrimaryStage(), "更新成功");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            /*
             *==================================================
             *                 !!important!!
             *     注意这里为了防止在新图片在static的情况，一定
             *     要注意顺序，好吧实际上这种情况并不会出现，如果
             *     不是用户手动向static文件夹中添加图片
             *==================================================
             */
            //添加新图片,删除旧图片.
            // TODO: 2018/6/30 但是这个问题先放着吧
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
     * 向服务器上传图片，涉及到文件操作，比较重要的一个函数
     */
    @FXML
    private void handleSwitchImage() {
        imageFile = ImageUtils.choose(mainApp);
        // 这里会输出该图片的路径
        System.out.println();
        // 替换图片
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
