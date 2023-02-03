package com.khtn.facexapp.model;

public class Student {
    private String name;
    private int id;

    public Student() {
    }

    public Student(String name, int id) {
        this.name = name;
        this.id = id;
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

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

//
//    public Student(int studentID, int imgID, String studentName, String studentDesc) {
//        this.studentID = studentID;
//        this.imgID = imgID;
//        this.studentName = studentName;
//        this.studentDesc = studentDesc;
//    }
//
//    public int getStudentID() {
//        return studentID;
//    }
//
//    public void setStudentID(int studentID) {
//        this.studentID = studentID;
//    }
//
//    public int getImgID() {
//        return imgID;
//    }
//
//    public void setImgID(int imgID) {
//        this.imgID = imgID;
//    }
//
//    public String getStudentName() {
//        return studentName;
//    }
//
//    public void setStudentName(String studentName) {
//        this.studentName = studentName;
//    }
//
//    public String getStudentDesc() {
//        return studentDesc;
//    }
//
//    public void setStudentDesc(String studentDesc) {
//        this.studentDesc = studentDesc;
//    }
}
