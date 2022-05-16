package com.example.checkers.DAL.Users;

import com.example.checkers.DAL.Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserRepository implements  IUserRepository{
    private Data db;
    private Connection connection;

    public  UserRepository(Data db){
        this.db = db;
        connection = db.getConnection();
        crateUserTableIfNotExist();
    }

    @Override
    public boolean createUser(String userName, String password) {
        String insertUserName = "INSERT INTO Users (UserName, HashPassword) values (?, ?)";
        PreparedStatement insertStatement = db.createPreparedStatement(insertUserName);
        if(insertStatement != null){
            try {
                insertStatement.setString(1, userName);
                insertStatement.setString(2, hashPassword((password)));
            }
            catch (SQLException ex){
                System.out.println("Eror while creating insert statement:" + ex.getMessage());
                return  false;
            }
        }
        try{
            insertStatement.executeUpdate();
            return  true;
        }
        catch (SQLException ex){
            System.out.println("Error while creating user in db:" + ex.getMessage());
        }
        return false;
    }

    @Override
    public int LogIn(String userName, String password) {
        return 0;
    }

    private  String hashPassword(String password){
        return  password;
    }

    private  void crateUserTableIfNotExist(){
        String checkQuery = "SELECT EXISTS (" +
                "   SELECT FROM information_schema.tables" +
                "   WHERE  table_schema = 'Users'" +
                "   AND    table_name   = 'Users'" +
                "   );";
        String usersTableName = "Users";
        PreparedStatement checkTableStatement = db.createPreparedStatement(checkQuery);
        if(checkTableStatement != null){
            try{
                //checkTableStatement.setString(1, usersTableName);
                //checkTableStatement.setString(2, usersTableName);
                ResultSet result = checkTableStatement.executeQuery();
                if(result.next()){
                    boolean isExist = result.getBoolean(0);
                    if(isExist){
                        createTable(usersTableName);
                    }
                }

            }
            catch (SQLException ex){
                System.out.println("Error while checking Users table exist:" + ex.getMessage());
            }
            catch (Exception ex){
                System.out.println(ex.getMessage());
            }


        }
    }

    private  void createTable(String tableName){
        String  createQuery = "CREATE TABLE ?(\n" +
                "\t  Id INT GENERATED ALWAYS AS IDENTITY,\n" +
                "\t  UserName VARCHAR NOT NULL,\n" +
                "\t  Password VARCHAR NOT NULL\n" +
                "  )";
        PreparedStatement checkTableStatement = db.createPreparedStatement(createQuery);
        if(checkTableStatement != null) {
            try{
                checkTableStatement.setString(1, tableName);
                checkTableStatement.executeUpdate();
            }
            catch (SQLException ex){
                System.out.println("Error while creating table " + tableName + ": " + ex.getMessage());
            }
        }
    }
}
