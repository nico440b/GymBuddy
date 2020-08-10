package com.example.gymBroApp.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table")
public class User {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name,email,pw,location,workPref,weatherPref;
    private int age,phoneNr;

    public User(String name, String email, String pw, String location, String workPref, String weatherPref, int age, int phoneNr) {
        this.name = name;
        this.email = email;
        this.pw = pw;
        this.location = location;
        this.workPref = workPref;
        this.weatherPref = weatherPref;
        this.age = age;
        this.phoneNr = phoneNr;
    }

    public User(){

    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWorkPref() {
        return workPref;
    }

    public void setWorkPref(String workPref) {
        this.workPref = workPref;
    }

    public String getWeatherPref() {
        return weatherPref;
    }

    public void setWeatherPref(String weatherPref) {
        this.weatherPref = weatherPref;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPhoneNr() {
        return phoneNr;
    }

    public void setPhoneNr(int phoneNr) {
        this.phoneNr = phoneNr;
    }
}