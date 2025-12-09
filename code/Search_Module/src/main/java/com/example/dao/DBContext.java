package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBContext {
    public Connection getConnection() throws Exception {

    	String url = "jdbc:sqlserver://localhost:1433;databaseName=ShopDB;encrypt=true;trustServerCertificate=true;";        String user = "sa"; 
        String pass = "123";
        
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url, user, pass);
    }
}