package com.example.projectone;

public class TourGuide {

    private int id;
    private String name;
    private int phone;
    private String email;
    private int city;
    private int hour_rate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public int getHour_rate() {
        return hour_rate;
    }

    public void setHour_rate(int hour_rate) {
        this.hour_rate = hour_rate;
    }
}