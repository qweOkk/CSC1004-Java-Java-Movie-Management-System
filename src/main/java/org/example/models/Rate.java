package org.example.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;

public class Rate implements Serializable {
    private IntegerProperty uid;
    private StringProperty username;
    private IntegerProperty mid;
    private StringProperty moviename;
    private IntegerProperty rate;

    public Rate() {
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
    public Integer getRate() {
        return rate.get();
    }

    public IntegerProperty rateProperty() {
        return rate;
    }


    public void setRate(Integer rate) {
        this.rate.set(rate);
    }


    @Override
    public String toString() {
        return "Rate{" +
                ", uid=" + uid +
                ", username=" + username +
                ", mid=" + mid +
                ", moviename=" + moviename +
                ", rate=" + rate +
                '}';
    }
}
