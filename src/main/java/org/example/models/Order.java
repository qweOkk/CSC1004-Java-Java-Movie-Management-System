package org.example.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import java.io.Serializable;

public class Order implements Serializable {
    private IntegerProperty id;
    private IntegerProperty uid;
    private StringProperty userName;
    private IntegerProperty mid;
    private StringProperty movieName;
    private  IntegerProperty age;
    private StringProperty comment;

    public Order() {
    }

    public String getUserName() {
        return userName.get();
    }

    public StringProperty userNameProperty() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName.set(userName);
    }

    public String getMovieName() {
        return movieName.get();
    }

    public StringProperty movieNameProperty() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName.set(movieName);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public int getUid() {
        return uid.get();
    }

    public IntegerProperty uidProperty() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid.set(uid);
    }

    public int getMid() {
        return mid.get();
    }

    public IntegerProperty midProperty() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid.set(mid);
    }
    public Integer getAge() {
        return age.get();
    }

    public IntegerProperty ageProperty() {
        return age;
    }


    public void setAge(Integer age) {
        this.age.set(age);
    }

    public String getComment() {
        return comment.get();
    }

    public StringProperty commentProperty() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment.set(comment);
    }


    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", uid=" + uid +
                ", userName=" + userName +
                ", mid=" + mid +
                ", age=" + age +
                ", movieName=" + movieName +
                ", comment=" + comment +
                '}';
    }
}
