package com.khtn.facexapp.model;

public class Student {
    private int studentID;
    private int imgID;
    private String studentName;
    private String studentDesc;

    public Student() {
    }

    public Student(int studentID, int imgID, String studentName, String studentDesc) {
        this.studentID = studentID;
        this.imgID = imgID;
        this.studentName = studentName;
        this.studentDesc = studentDesc;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getImgID() {
        return imgID;
    }

    public void setImgID(int imgID) {
        this.imgID = imgID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentDesc() {
        return studentDesc;
    }

    public void setStudentDesc(String studentDesc) {
        this.studentDesc = studentDesc;
    }
}
