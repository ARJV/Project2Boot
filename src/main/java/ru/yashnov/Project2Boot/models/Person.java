package ru.yashnov.Project2Boot.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Person {
    private long id;

    @NotEmpty()
    @Size(min = 2, max = 30)
    private String name;

    @Min(value = 1900)
    private int birth_date;

    public Person(long id, String name, int birth_date) {
        this.id = id;
        this.name = name;
        this.birth_date = birth_date;
    }

    public Person() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(int birth_date) {
        this.birth_date = birth_date;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birth_date=" + birth_date +
                '}';
    }
}
