package com.example.checkers.DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Data implements   IData {
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    private  Boolean status;
    private  DbProperties properties;
    private Statement statement;



    public  Data(DbProperties prop){
        properties = prop;
        connect();
    }

    private void connect()
    {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run()
            {
                try
                {
                    Class.forName("org.postgresql.Driver");
                    String connectionString = properties.getConnectionString();
                    connection = DriverManager.getConnection(connectionString,
                            properties.getUserName(), properties.getPassword());
                    status = true;
                    System.out.println("connected:" + status);
                }
                catch (Exception e)
                {
                    status = false;
                    System.out.print(e.getMessage());
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        try
        {
            thread.join();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            this.status = false;
        }
    }

    public PreparedStatement createPreparedStatement(String query){
        PreparedStatement statement = null;
        if(connection != null){
            try{
                statement = connection.prepareStatement(query);
            }
            catch (SQLException ex){
                System.out.println("Error while creating prepared statement");
            }
        }
        return statement;
    }

    public Statement createStatement(){
        Statement statement = null;
        if(connection != null){
            try{
                statement = connection.createStatement();
            }
            catch (SQLException ex){
                System.out.println("Error while creating prepared statement");
            }
        }
        return statement;
    }


}
