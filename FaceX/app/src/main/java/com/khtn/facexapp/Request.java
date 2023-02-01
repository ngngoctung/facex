package com.khtn.facexapp;

public class Request {
    static String chooseClass;

    public Request() {
    }

    public static String getChooseClass() {
        return chooseClass;
    }

    public static void setChooseClass(String chooseClass) {
        Request.chooseClass = chooseClass;
    }
}
