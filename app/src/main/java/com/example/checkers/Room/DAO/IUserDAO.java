package com.example.checkers.Room.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Query;

import com.example.checkers.Room.Entities.User;

@Dao
public interface IUserDAO {

    @Query("SELECT * FROM User LIMIT 1")
    User getUser();

    @Delete
    void deleteUser();
}
