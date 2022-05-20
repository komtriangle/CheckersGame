package com.example.checkers.Sugar.Entities;

import com.orm.SugarRecord;

public class User  extends SugarRecord {

    private  int idInDb;
    private  String userName;

    public  User(){

    }

    public  User(int id, String userName){
        this.idInDb = id;
        this.userName = userName;
    }

    public int Id() {
        return idInDb;
    }
}
