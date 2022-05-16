package com.example.checkers.DAL;

public class DbProperties {
    private  String hostName;
    private  int port;
    private  String userName;
    private  String password;
    private  String database;
    private String url = "jdbc:postgresql://%s:%d/%s";


    public DbProperties(String hostName, int port, String userName, String password,  String database) {
        this.hostName = hostName;
        this.port = port;
        this.userName = userName;
        this.password = password;
        this.database = database;
    }

    public String getHostName() {
        return hostName;
    }

    public int getPort() {
        return port;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public  String getConnectionString(){
        return String.format(url, hostName, port, database);
    }
}
