package org.example.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class MovieClass {
    private IntegerProperty id;
    private StringProperty className;

    public MovieClass() {
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

    public String getclassName() {
        return className.get();
    }

    public StringProperty classNameProperty() {
        return className;
    }

    public void setclassName(String className) {
        this.className.set(className);
    }

    @Override
    public String toString() {
        return "MovieClass{" +
                "id=" + id +
                ", className=" + className +
                '}';
    }
}
