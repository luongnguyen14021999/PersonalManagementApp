package com.example.empmgtsystem.FriendList;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Friend implements Serializable {


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    int id;
    String fname;
    String lname;
    String gentle;
    String age;
    String address;
    String image;

    public Friend(String fname, String lname, String gentle, String age, String address,String image) {
        this.fname = fname;
        this.lname = lname;
        this.gentle = gentle;
        this.age = age;
        this.address = address;
        this.image = image;
    }

    public Friend(int id, String fname, String lname, String gentle, String age, String address) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.gentle = gentle;
        this.age = age;
        this.address = address;
    }

    public Friend(int id, String fname, String lname, String gentle, String age, String address, String image) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.gentle = gentle;
        this.age = age;
        this.address = address;
        this.image = image;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getGentle() {
        return gentle;
    }

    public void setGentle(String gentle) {
        this.gentle = gentle;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @NonNull
    @Override
    public String toString() {
        return fname.toString();
    }

}