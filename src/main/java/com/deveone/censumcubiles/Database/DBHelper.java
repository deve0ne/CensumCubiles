package com.deveone.censumcubiles.Database;

import java.sql.Connection;

import java.io.IOException;
import java.io.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DBHelper {
    public static void init() {
        try {
            Connection conn = getConnection();
            System.out.println("Connection to DB succesfull :)");
        } catch (Exception ex) {
            System.err.println("Connection to DB failed :(\n" + ex);
        }
    }

    private static Connection getConnection() throws SQLException {
        Properties props = new Properties();
        try (InputStream in = DBHelper.class.getClassLoader().getResourceAsStream("database.properties")) {
            props.load(in);
        } catch (IOException e) {
            System.err.println("database.properties not found :O");
        }

        String url = props.getProperty("url");
        String username = props.getProperty("username");
        String password = props.getProperty("password");

        return DriverManager.getConnection(url, username, password);
    }
}


