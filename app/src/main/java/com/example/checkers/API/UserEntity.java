package com.example.checkers.API;


public class UserEntity {

    private int id;
    private    String userName;

    public UserEntity(int id, String userName) {
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