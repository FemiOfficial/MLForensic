package com.example.alayesanmifemi.mlforensic;

/**
 * Created by Alayesanmi Femi on 11/05/2018.
 */

public class Cases {

    private String title;
    private String incident_date;
    private  String location;
    private String detective;
    private String detective_id;
    private String crimeType;
    private String description;
    private  String  arrivalTimeDate;
    private  String departureTimeDate;
    private String weather;
    int img_thumbnail;

    public Cases(String detective_id, String title, String detective, String incident_date, String location, String crimeType,
                  String description, String arrivalTimeDate,
                  String departureTimeDate, String weather, int img_thumbnail) {

        this.incident_date = incident_date;
        this.title = title;
        this.location = location;
        this.detective_id = detective_id;
        this.detective = detective;
        this.crimeType = crimeType;
        this.description = description;
        this.incident_date = incident_date;
        this.arrivalTimeDate = arrivalTimeDate;
        this.departureTimeDate = departureTimeDate;
        this.weather = weather;
        this.img_thumbnail = img_thumbnail;
    }
    public Cases(){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIncident_date() {
        return incident_date;
    }

    public void setIncident_date(String incident_date) {
        this.incident_date = incident_date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDetective() {
        return detective;
    }

    public void setDetective(String detective) {
        this.detective = detective;
    }

    public String getDetective_id() {
        return detective_id;
    }

    public void setDetective_id(String detective_id) {
        this.detective_id = detective_id;
    }

    public String getCrimeType() {
        return crimeType;
    }

    public void setCrimeType(String crimeType) {
        this.crimeType = crimeType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getArrivalTimeDate() {
        return arrivalTimeDate;
    }

    public void setArrivalTimeDate(String arrivalTimeDate) {
        this.arrivalTimeDate = arrivalTimeDate;
    }

    public String getDepartureTimeDate() {
        return departureTimeDate;
    }

    public void setDepartureTimeDate(String departureTimeDate) {
        this.departureTimeDate = departureTimeDate;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public int getImg_thumbnail() {
        return img_thumbnail;
    }

    public void setImg_thumbnail(int img_thumbnail) {
        this.img_thumbnail = img_thumbnail;
    }
}
