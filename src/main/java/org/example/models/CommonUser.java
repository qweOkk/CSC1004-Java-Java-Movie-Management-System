package org.example.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CommonUser implements User{
    private IntegerProperty id;
    private StringProperty username;
    private StringProperty password;
    private StringProperty sid;
    private IntegerProperty age;
    private StringProperty tel;

    @Override
    public String showUsername() {
        return getPassword();
    }

    @Override
    public String showPassword() {
        return getPassword();
    }

    @Override
    public String showType() {
        return "CommonUser";
    }

    public CommonUser() {
    }

    public CommonUser( String username, String password) {
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
    }

    public CommonUser(Integer id,String username, String password, Integer age, String tel,String sid) {
        this.id = new SimpleIntegerProperty(id);
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
        this.sid = new SimpleStringProperty(sid);
        this.tel = new SimpleStringProperty(tel);
        this.age = new SimpleIntegerProperty(age);
    }
    public int getId(){return id.get();}
    public IntegerProperty idProperty() {
        return id;
    }
    public void setId(int id) {
        this.id.set(id);
    }
    public String getUsername() {
        return username.get();
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }
    public Integer getAge() {return age.get();}
    public String getSid() {
        return sid.get();
    }
    public IntegerProperty ageProperty() {return age;}
    public StringProperty sidProperty() {
        return sid;
    }
    public void setAge(Integer age) {this.age.set(age);}
    public void setSid(String sid) {
        this.sid.set(sid);
    }

    public String getTel() {
        return tel.get();
    }

    public StringProperty telProperty() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel.set(tel);
    }


    @Override
    public String toString() {
        return "CommonUser{" +
                "id=" + id +
                ", username=" + username +
                ", password=" + password +
                ", age="+ age +
                ", tel=" + tel +
                ", sid=" + sid +
                '}';
    }
}
