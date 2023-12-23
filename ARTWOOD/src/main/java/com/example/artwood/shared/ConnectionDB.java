package com.example.artwood.shared;


import com.example.artwood.controller.ClientServlet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private static Connection instance = null;
    private static   final Logger logger = LogManager.getLogger(ConnectionDB.class);
    private static String DB_USER_NAME = "root";
    private static String DB_USER_PASSWORD = "root";
    private static String DB_URL = "jdbc:mysql://localhost:3306/wood";
   public static Connection getInstance(){
       if (instance == null){
           try {
               Class.forName("com.mysql.cj.jdbc.Driver");
               instance =  DriverManager.getConnection(DB_URL,DB_USER_NAME,DB_USER_PASSWORD);
               logger.info("Conenction On Db");
           } catch (ClassNotFoundException | SQLException e) {
               logger.error("Error in connection JDBC ",e);


           }
       }
       return instance;
   }
}
