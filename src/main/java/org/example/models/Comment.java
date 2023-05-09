package org.example.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.io.Serializable;

public class Comment implements Serializable {
    private IntegerProperty id;
    private IntegerProperty uid;
    private StringProperty username;
    private IntegerProperty mid;
    private StringProperty moviename;
    private StringProperty comment;
    private StringProperty picture;
    public Comment() {
    }
    public Comment(Integer id, Integer uid, String username, Integer mid, String moviename,String comment,String picture) {
        this.id = new SimpleIntegerProperty(id);
        this.uid = new SimpleIntegerProperty(uid);
        this.username = new SimpleStringProperty(username);
        this.mid = new SimpleIntegerProperty(mid);
        this.moviename= new SimpleStringProperty(moviename);
        this.comment= new SimpleStringProperty(comment);
        this.picture=new SimpleStringProperty(picture);
    }

    public String getusername() {
        return username.get();
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public void setusername(String username) {
        this.username.set(username);
    }

    public String getmoviename() {
        return moviename.get();
    }

    public StringProperty movienameProperty() {
        return moviename;
    }

    public void setmoviename(String moviename) {
        this.moviename.set(moviename);
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

    public String getComment() {
        return comment.get();
    }

    public StringProperty commentProperty() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment.set(comment);
    }
    public String getPicture() {
        return picture.get();
    }

    public StringProperty pictureProperty() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture.set(picture);
    }

    @Override
    public String toString() {
        return "comment{" +
                "id=" + id +
                ", uid=" + uid +
                ", username=" + username +
                ", mid=" + mid +
                ", moviename=" + moviename +
                ", comment=" + comment +
                ", picture=" + picture +
                '}';
    }
}
