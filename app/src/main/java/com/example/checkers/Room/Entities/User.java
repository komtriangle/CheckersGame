package com.example.checkers.Room.Entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;

@Entity
public class User {
    @ColumnInfo(name =  "id")
    public  int Id;

    @ColumnInfo(name = "user_name")
    public  String UserName;

    public User(int id, String userName) {
        Id = id;
        UserName = userName;
    }
}
