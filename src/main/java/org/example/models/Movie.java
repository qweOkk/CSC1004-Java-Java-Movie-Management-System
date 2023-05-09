package org.example.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;

public class Movie implements Serializable {
    private IntegerProperty id;
    private StringProperty coverPath;
    private StringProperty name;
    private StringProperty director;
    private StringProperty actor;
    private StringProperty intro;
    private StringProperty className;
//    private IntegerProperty classNumber;

    public Movie() {
    }

    public Movie(Integer id, String coverPath, String name, String director, String actor,String intro, String className) {
        this.id = new SimpleIntegerProperty(id);
        this.coverPath = new SimpleStringProperty(coverPath);
        this.name = new SimpleStringProperty(name);
        this.director = new SimpleStringProperty(director);
        this.actor = new SimpleStringProperty(actor);
        this.intro = new SimpleStringProperty(intro);
        this.className = new SimpleStringProperty(className);
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

    public String getCoverPath() {
        return coverPath.get();
    }

    public StringProperty coverPathProperty() {
        return coverPath;
    }

    public void setCoverPath(String coverPath) {
        this.coverPath.set(coverPath);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getDirector() {
        return director.get();
    }

    public StringProperty directorProperty() {
        return director;
    }

    public void setODirector(String director) {
        this.director.set(director);
    }

    public String getActor() {
        return actor.get();
    }

    public StringProperty actorProperty() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor.set(actor);
    }
    public String getIntro() {
        return intro.get();
    }

    public StringProperty introProperty() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro.set(intro);
    }

    public String getClassName() {
        return className.get();
    }

    public StringProperty classNameProperty() {
        return className;
    }

    public void setClassName(String className) {
        this.className.set(className);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", coverPath=" + coverPath +
                ", name=" + name +
                ", director=" + director +
                ", actor=" + actor +
                ", intro=" + intro +
                ", className=" + className +
                '}';
    }
}
