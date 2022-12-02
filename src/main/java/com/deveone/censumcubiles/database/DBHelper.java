package com.deveone.censumcubiles.database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public abstract class DBHelper {
    protected static Connection getConnection() throws SQLException {
        Properties props = new Properties();
        try (InputStream in = DBHelper.class.getClassLoader().getResourceAsStream("database.properties")) {
            props.load(in);
        } catch (IOException e) {
            System.err.println("database.properties not found :O");
        }

        String url = props.getProperty("url");
        return DriverManager.getConnection(url, props);
    }

    protected static void printError(Exception ex) {
        System.err.println("Connection to DB failed :(\n");
        ex.printStackTrace();
    }
}


