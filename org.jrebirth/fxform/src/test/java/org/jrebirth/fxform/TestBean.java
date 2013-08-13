package org.jrebirth.fxform;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * User: Antoine Mischler <antoine@dooapp.com>
 * Date: 13/08/13
 * Time: 15:25
 */
public class TestBean {

    private final StringProperty name = new SimpleStringProperty("Joe");

    public int getAge() {
        return age.get();
    }

    public IntegerProperty ageProperty() {
        return age;
    }

    public void setAge(int age) {
        this.age.set(age);
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

    private final IntegerProperty age = new SimpleIntegerProperty(99);

}
