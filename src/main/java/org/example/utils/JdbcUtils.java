package org.example.utils;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import org.example.models.Admin;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class JdbcUtils {
    private static final String USER_NAME = "root";
    private static final String PASSWORD =" ";

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/moviemanagement?useSSL=false&characterEncoding=utf-8&serverTimezone=UTC";
    private Connection connection;
    private PreparedStatement pstmt;
    private ResultSet resultSet;

    public JdbcUtils() {
        try {
            Class.forName(DRIVER);
            System.out.println("Connect with the database successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        try {
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return connection;
    }
    /**
     * Adding, deleting, and modifying database data
     */
    public boolean updateByPreparedStatement(String sql, List<Object> params) throws SQLException {
        int resultLineNumber = -1;
        pstmt = connection.prepareStatement(sql);
        if (params != null && !params.isEmpty()) {
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(i+1, params.get(i));
            }
        }
        resultLineNumber = pstmt.executeUpdate();
        return resultLineNumber > 0;
    }

    /**
     * Query a single database data and package it into a dictionary to return it
     */
    public Map<String, Object> findSimpleResult(String sql, List<Object> params) throws SQLException {
        Map<String, Object> map = new HashMap<>();
        pstmt = connection.prepareStatement(sql);
        if (params != null && !params.isEmpty()) {
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(i+1, params.get(i));
            }
        }
        resultSet = pstmt.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int col = metaData.getColumnCount();
        while (resultSet.next()) {
            for (int i = 0; i < col; i++) {
                String colName = metaData.getColumnLabel(i+1);
                Object colValue = resultSet.getObject(colName);
                if (colValue == null) {
                    colValue = "";
                }
                map.put(colName, colValue);
            }
        }
        return map;
    }

    /**
     * Query multiple database data, package them into a dictionary, and place them in a list to return
     */
    public List<Map<String, Object>> findModeResult(String sql, List<Object> params) throws SQLException {
        List<Map<String, Object>> maps = new ArrayList<>();
        pstmt = connection.prepareStatement(sql);
        if (params != null && !params.isEmpty()) {
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(i+1, params.get(i));
            }
        }
        resultSet = pstmt.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int col = metaData.getColumnCount();
        while (resultSet.next()) {
            Map<String, Object> map = new HashMap<>();
            for (int i = 0; i < col; i++) {
                String colName = metaData.getColumnLabel(i+1);
                Object colValue = resultSet.getObject(colName);
                if (colValue == null) {
                    colValue = "";
                }
                map.put(colName, colValue);
            }
            maps.add(map);
        }
        return maps;
    }

    /**
     * Query a single database data through a reflection mechanism, package it after processing the binding, and directly return the packaged object
     */
    public <T> T findSimpleProResult(String sql, List<Object> params, Class<T> cls) throws Exception {
        T resultObject = null;
        pstmt = connection.prepareStatement(sql);
        if (params != null && !params.isEmpty()) {
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(i+1, params.get(i));
            }
        }
        resultSet = pstmt.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int col = metaData.getColumnCount();
        while (resultSet.next()) {
            resultObject = cls.newInstance();
            for (int i = 0; i < col; i++) {
                String colName = metaData.getColumnName(i+1);
                Object colValue = resultSet.getObject(colName);
                Object newValue = null;
                if (colValue == null) {
                    newValue = "";
                } else if (colValue.getClass() == Integer.class) {
                    newValue = new SimpleIntegerProperty(Integer.parseInt(colValue.toString()));
                } else if (colValue.getClass() == String.class) {
                    newValue = new SimpleStringProperty(colValue.toString());
                }
                Field field = cls.getDeclaredField(colName);
                field.setAccessible(true);
                field.set(resultObject, newValue);
            }
        }
        return resultObject;
    }

    /**
     * Query multiple database data through reflection mechanism, handle bidirectional binding, encapsulate objects, and fill them in the list to return
     */
    public <T> List<T> findMoreProResult(String sql, List<Object> params, Class<T> cls) throws Exception {
        List<T> list = new ArrayList<>();
        pstmt = connection.prepareStatement(sql);
        if (params != null && !params.isEmpty()) {
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(i+1, params.get(i));
            }
        }

        resultSet = pstmt.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        //System.out.println(metaData);
        int col = metaData.getColumnCount();
        while (resultSet.next()) {
            T resultObject = cls.newInstance();
            for (int i = 0; i < col; i++) {
                String colName = metaData.getColumnLabel(i+1);
                Object colValue = resultSet.getObject(colName);
                //System.out.println(colName);
                Object newValue = null;
                if (colValue == null) {
                    colValue = "";
                } else if (colValue.getClass() == Integer.class) {
                    newValue = new SimpleIntegerProperty(Integer.parseInt(colValue.toString()));
                } else if (colValue.getClass() == String.class) {
                    newValue = new SimpleStringProperty(colValue.toString());
                }
                Field field = cls.getDeclaredField(colName);
                field.setAccessible(true);
                field.set(resultObject, newValue);
            }
            list.add(resultObject);
        }
        return list;
    }

    /**
     * Query single database data through reflection mechanism and directly return packaged objects
     */
    public <T> T findSimpleRefResult(String sql, List<Object> params, Class<T> cls) throws Exception {
        T resultObject = null;
        pstmt = connection.prepareStatement(sql);
        if (params != null && !params.isEmpty()) {
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(i+1, params.get(i));
            }
        }
        resultSet = pstmt.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int col = metaData.getColumnCount();
        while (resultSet.next()) {
            resultObject = cls.newInstance();
            for (int i = 0; i < col; i++) {
                String colName = metaData.getColumnName(i+1);
                Object colValue = resultSet.getObject(colName);
                if (colValue == null) {
                    colValue = "";
                }
                Field field = cls.getDeclaredField(colName);
                field.setAccessible(true);
                field.set(resultObject, colValue);
            }
        }
        return resultObject;
    }


    /**
     * Release database connection
     */
    public void releaseConnection() {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Test Function
     */
    public static void main(String[] args) throws SQLException {
        JdbcUtils jdbcUtils = new JdbcUtils();
        jdbcUtils.getConnection();
        String sql = "select * from `admin` where username = ?";
        List<Object> params = new ArrayList<>();
        params.add("qweokk");
        try {
            Admin admin = jdbcUtils.findSimpleRefResult(sql, params, Admin.class);
            System.out.println(jdbcUtils.findSimpleRefResult(sql, params, Admin.class));
            System.out.println(admin.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
