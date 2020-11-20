package com.example.empmgtsystem.TaskList;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Task implements Serializable {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    int id;
    String taskName;
    String location;
    String status;


    public Task(String taskName, String location, String status) {
        this.taskName = taskName;
        this.location = location;
        this.status = status;
    }

    public Task(int id, String taskName, String location, String status) {
        this.id = id;
        this.taskName = taskName;
        this.location = location;
        this.status = status;
    }


    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @NonNull
    @Override
    public String toString() {
        return taskName.toString();
    }
}
