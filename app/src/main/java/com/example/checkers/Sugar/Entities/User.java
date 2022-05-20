package com.example.checkers.Sugar.Entities;

import com.orm.SugarRecord;

public class User  extends SugarRecord {

    private  int id;
    private  String userName;

    public  User(){

    }

    public  User(int id, String userName){
        this.id = id;
        this.userName = userName;
    }

    public int Id() {
        return id;
    }
}
