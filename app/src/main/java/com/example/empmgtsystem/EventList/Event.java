package com.example.empmgtsystem.EventList;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Event implements Serializable {

    int id;

    String eventName;

    String date;

    String time;

    String location;

    public Event(String eventName, String date, String time, String location) {
        this.eventName = eventName;
        this.date = date;
        this.time = time;
        this.location = location;
    }


    public Event(int id, String eventName, String date, String time, String location) {
        this.id = id;
        this.eventName = eventName;
        this.date = date;
        this.time = time;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @NonNull
    @Override
    public String toString() {
        return eventName.toString();
    }
}
