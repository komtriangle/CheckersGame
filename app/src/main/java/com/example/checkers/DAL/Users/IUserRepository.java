package com.example.checkers.DAL.Users;

public interface IUserRepository {
    boolean createUser(String userName, String password);
    int LogIn(String userName, String password);
}
