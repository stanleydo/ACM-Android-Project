package com.example.acm_app_stanleydo.models;

public class Event {
    private String name;

    public Event(String name, String url, String startDate, String endDate, String location, boolean isHighSchool, String imageUrl) {
        this.name = name;
        this.url = url;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.isHighSchool = isHighSchool;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getLocation() {
        return location;
    }

    public boolean isHighSchool() {
        return isHighSchool;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setHighSchool(boolean highSchool) {
        isHighSchool = highSchool;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    private String url;
    private String startDate;
    private String endDate;
    private String location;
    private boolean isHighSchool;
    private String imageUrl;
}
