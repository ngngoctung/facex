package com.khtn.facexapp.model;

public class Student {
    private String name;
    private int id;
    private String image;

    public Student() {
    }

    public Student(String name, int id, String image) {
        this.name = name;
        this.id = id;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() { return image; }

}
