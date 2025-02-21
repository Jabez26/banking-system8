package com.banking.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DbConnection {

    public final static String URL = "jdbc:mysql://127.0.0.1:3306/banking-system9";
    public final static String USERNAME = "root";
    public final static String PASSWORD = "";
    public final static String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static Connection connection;
    //public static ResultSet rs;


    //CONSTRUCTOR: (is called upon DbConnection instantiation)
    public DbConnection(){
        try {
            Class.forName(DRIVER);

            connection = DriverManager.getConnection(
                    URL,
                    USERNAME,
                    PASSWORD

            );
            //System.out.println("Database connection successful!");

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
    }




}
