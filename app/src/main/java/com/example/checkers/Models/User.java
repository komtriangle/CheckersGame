package com.example.checkers.Models;

import com.squareup.moshi.Json;
import com.squareup.moshi.JsonClass;

public class User {

    private int id;
    private    String userName;

    public User(int id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }
}
