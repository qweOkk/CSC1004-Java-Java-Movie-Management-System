package org.example.utils;
import javafx.beans.property.StringProperty;
import org.example.models.*;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Check Input
 */
public class InputChecker {
    /**
     * Check input of commonUser's log in.
     */
    public static String commonUserInfoCheck(String uname, String pass, Integer age, String tel, String sid) {
        String errorMessage = null;

        if (tel.length() > 60) {
            errorMessage = "Your telephone number is too long!";
        }

        if (sid.length() > 60) {
            errorMessage = "Your ID number is too long!";
        }
        if (age<0){
            errorMessage ="Age is impossible!";
        }
        if (pass.length() == 0) {
            errorMessage = "Password cannot be empty!";
        } else if (pass.length() > 60) {
            errorMessage = "Password is too long!";
        }

        if (uname.length() == 0) {
            errorMessage = "Username cannot be empty!";
        } else if (uname.length() > 60) {
            errorMessage = "Username is too long!";
        }

        return errorMessage;
    }
    /**
     * Check input of commonUser's sign ip.
     */
    public static String commonUserSignUpCheck(String uname, String pass, Integer age, String tel, String sid) {
        String errorMessage = commonUserInfoCheck(uname, pass, age, tel, sid);

        //Examine whether the username has been signed up.
        JdbcUtils jdbcUtils = new JdbcUtils();
        jdbcUtils.getConnection();
        String sql = "select * from `commonUser` where username = ?";
        List<Object> params = new ArrayList<>();
        params.add(uname);

        try {
            CommonUser sameUser = jdbcUtils.findSimpleProResult(sql, params, CommonUser.class);
            if (sameUser != null) {
                errorMessage = "The username has been signed up!";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return errorMessage;
    }
    /**
     * Check input of commonUser's sign up in admin page.
     */
    public static String commonUserSignUpCheck(String id, String uname, String pass, Integer age, String tel, String sid) {
        String errorMessage = commonUserSignUpCheck(uname, pass, age, tel, sid);

        //Examine whether the user has been signed up upon the original info.
        JdbcUtils jdbcUtils= new JdbcUtils();
        jdbcUtils.getConnection();
        String sql = "select * from `commonUser` where id = ?";
        List<Object> params = new ArrayList<>();

        if (id == null || id.length() == 0) {
            return errorMessage = "ID number is empty.";
        } else if(id.length() > 11) {
            return  errorMessage = "Your ID number is too long!";
        } else {
            try {
                Integer.parseInt(id);
            } catch (Exception e) {
                return errorMessage = "id should only be numbers.";
            }
        }

        params.add(Integer.parseInt(id));

        try {
            CommonUser sameUser = jdbcUtils.findSimpleProResult(sql, params, CommonUser.class);
            if (sameUser != null) {
                errorMessage = "The ID number has been signed up.";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return errorMessage;
    }
    /**
     * Check update of commonUser's info in admin page.
     */
    public static String commonUserUpdateCheck(CommonUser oldInfo, CommonUser newInfo) {
        String errorMessage = commonUserInfoCheck(newInfo.getUsername(), newInfo.getPassword(), Integer.valueOf(newInfo.getAge()),newInfo.getTel(), newInfo.getSid());

        //If the id has not been changed.
        if (oldInfo.getId() != newInfo.getId()) {
            errorMessage = "You cannot change the user's id.";
        }

        if (!oldInfo.getUsername().equals(newInfo.getUsername())) {
            JdbcUtils jdbcUtils = new JdbcUtils();
            jdbcUtils.getConnection();
            String sql = "select * from `commonUser` where username = ?";
            List<Object> params = new ArrayList<>();
            params.add(newInfo.getUsername());
            try {
                CommonUser same = jdbcUtils.findSimpleProResult(sql, params, CommonUser.class);
                if (same != null) {
                    errorMessage = "The username has been used.";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return errorMessage;
    }

    /**
     * Check new of Admin's info in admin page.
     */
    public static String adminSignUpCheck(String id, String uname, String pass) {
        String errorMessage = null;

        JdbcUtils jdbcUtils = new JdbcUtils();
        jdbcUtils.getConnection();
        String sql = "select * from `admin` where id = ?";
        List<Object> params = new ArrayList<>();

        if (id == null || id.length() == 0) {
            return errorMessage = "ID is empty!";
        } else if(id.length() > 11) {
            return  errorMessage = "Your ID is too long!";
        } else {
            try {
                Integer.parseInt(id);
            } catch (Exception e) {
                return errorMessage = "ID should only be numbers!";
            }
        }

        if (uname == null || uname.length() == 0) {
            return errorMessage = "Username should not be empty!";
        }


        params.add(Integer.parseInt(id));

        try {
            Admin sameUser = jdbcUtils.findSimpleProResult(sql, params, Admin.class);
            if (sameUser != null) {
                errorMessage = "Your ID has been used.";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        sql = "select * from `admin` where username = ?";
        params.clear();
        params.add(uname);

        try {
            Admin sameUser = jdbcUtils.findSimpleProResult(sql, params, Admin.class);
            if (sameUser != null) {
                errorMessage = "Your username has been used!";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (pass.length() > 60) {
            errorMessage = "Password is too long!";
        } else if (pass.length() == 0) {
            errorMessage = "Please enter your password!";
        }

        return errorMessage;
    }
    /**
     * Check update of Admin's info in admin page.
     */
    public static String adminInfoUpdateCheck(Admin oldInfo, Admin newInfo) {
        String errorMessage = null;

        //If ID and username are not been changed.
        if (oldInfo.getId() != newInfo.getId()) {
            errorMessage = "You cannot change the ID!";
        }

        if (newInfo.getUsername() == null || newInfo.getUsername().length() == 0) {
            return errorMessage = "Username cannot be empty!";
        }

        if (newInfo.getPassword() == null || newInfo.getPassword().length() == 0) {
            return errorMessage = "Your password should not be empty!";
        }


        if (!oldInfo.getUsername().equals(newInfo.getUsername())) {
            JdbcUtils jdbcUtils = new JdbcUtils();
            jdbcUtils.getConnection();
            String sql = "select * from `admin` where username = ?";
            List<Object> params = new ArrayList<>();
            params.add(newInfo.getUsername());
            try {
                Admin same = jdbcUtils.findSimpleProResult(sql, params, Admin.class);
                if (same != null) {
                    errorMessage = "The username has been used!";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return errorMessage;
    }
    /**
     * Check new movie.
     */
    public static String movieSignUpCheck(String id, File image, String mname, String director, String actor, String mclas, String intro) {
        String errorMessage = movieRegularCheck(id, mname, director, actor, mclas, intro);

        //Examine the ID, name.
        JdbcUtils jdbcUtils = new JdbcUtils();
        jdbcUtils.getConnection();
        String sql = "select id, coverPath, name from `movie`";
        List<Object> params = new ArrayList<>();
        try {
            List<Map<String, Object>> infos = jdbcUtils.findModeResult(sql, params);
            for (Map<String, Object> info : infos) {
                if (image == null || image.getName().equals(info.get("coverPath"))) {
                    errorMessage = "The image has been used. Please change one.";
                }
                if (mname.equals(info.get("name"))) {
                    errorMessage = "The movie's name has existed!";
                }
                if (id == null || id.length() == 0) {
                    return errorMessage = "ID should not empty!";
                } else {
                    try {
                        Integer.parseInt(id);
                    } catch (Exception e) {
                        return errorMessage = "ID should be numbers.";
                    }
                }
                if (Integer.parseInt(id) == (Integer) info.get("id")) {
                    errorMessage = "The ID has been changed.";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return errorMessage;
    }
    /**
     * Check update of movies.
     */
    public static String movieUpdateCheck(Movie oldInfo, Movie newInfo) {
        String errorMessage = movieRegularCheck(Integer.toString(newInfo.getId()), newInfo.getName(), newInfo.getDirector(), newInfo.getActor(), newInfo.getClassName(), newInfo.getIntro());
        //id cannot be changed.
        if (oldInfo.getId() != newInfo.getId()) {
            errorMessage = "You cannot change the ID!";
        }

        JdbcUtils jdbcUtils = new JdbcUtils();
        jdbcUtils.getConnection();
        List<Object> params = new ArrayList<>();

        // Examine the new name.
        if (!oldInfo.getName().equals(newInfo.getName())) {
            String sql = "select * from `movie` where name = ?";
            params.add(newInfo.getName());
            try {
                Map<String, Object> same = jdbcUtils.findSimpleResult(sql, params);
                if (!same.isEmpty()) {
                    errorMessage = "The name has been used!";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //Examine whether the image has been used.
        if (!oldInfo.getCoverPath().equals(newInfo.getCoverPath())) {
            String sql = "select coverPath from `movie`";
            params.clear();
            try {
                List<Map<String, Object>> infos = jdbcUtils.findModeResult(sql, params);
                for (Map<String, Object> info : infos) {
                    if (newInfo.getCoverPath().equals(info.get("coverPath"))) {
                        errorMessage = "The image has been used. Please change one.";
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return errorMessage;
    }
    /**
     * Regular check of movies.
     */
    public static String movieRegularCheck(String id, String mname, String director, String actor, String mclas, String intro) {
        String errorMessage = null;
        System.out.println("Enter the regular test.");
        if (director.length() == 0) {
            errorMessage = "The director should not be empty!";
        }
        if (actor.length() == 0) {
            errorMessage = "The actor should not be empty!";
        }

        if (mclas.length() == 0) {
            errorMessage = "No class!";
        }
        if (intro.length() > 140) {
            errorMessage = "The introduction is too long!";
        }
        if (id.length() > 11) {
            errorMessage = "ID is too long!";
        }
        if (mname.length() > 60) {
            errorMessage = "Movie's name is too long!";
        }
        return errorMessage;
    }

}

