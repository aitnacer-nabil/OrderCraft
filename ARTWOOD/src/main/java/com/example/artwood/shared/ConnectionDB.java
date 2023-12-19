package com.example.artwood.shared;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private static Connection instance = null;
    private static String DB_USER_NAME = "root";
    private static String DB_USER_PASSWORD = "root";
    private static String DB_URL = "jdbc:mysql://localhost:3306/wood";
   public static Connection getInstance(){
       if (instance == null){
           try {
               Class.forName("com.mysql.cj.jdbc.Driver");
               instance =  DriverManager.getConnection(DB_URL,DB_USER_NAME,DB_USER_PASSWORD);
           } catch (ClassNotFoundException | SQLException e) {
               System.out.println("Error In DB");
               throw new RuntimeException(e);

           }
       }
       return instance;
   }
}
