package com.example.acm_app_stanleydo.models;

public class Announcement {

    private String author;
    private String date;
    private String message;
    private String imgurl;

    public Announcement(){}

    public Announcement(String author, String date, String message, String imgurl) {
        this.author = author;
        this.date = date;
        this.message = message;
        this.imgurl = imgurl;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

}
