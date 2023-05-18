package com.example.aroundtheworld.connection;


import com.example.aroundtheworld.exception.ConnectionDbException;

import java.io.FileInputStream;
import java.io.IOException;

import java.sql.*;

import java.util.Properties;

public class ConnectionDB {

    private ConnectionDB() {}

    private static Connection connection;

    public static Statement getConnection() throws ConnectionDbException {
        Statement stmt;
        try {
            conn();
            stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException e) {
            throw new ConnectionDbException();
        }
        return stmt;
    }

    public static Connection getConnectionP() throws ConnectionDbException {
        String user;
        String password;
        String url;
        String driverClassName;

        if (connection == null) {
            try{
                Properties db = loadProperties();
                user = db.getProperty("USER");
                url = db.getProperty("CONNECTION_URL");
                password = db.getProperty("PASS");
                driverClassName = db.getProperty("DRIVER_CLASS_NAME");

                Class.forName(driverClassName);

                connection = DriverManager.getConnection(url,user, password);

            } catch (SQLException | IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void closeConnection() throws SQLException {
        connection.close();
    }

    private static void conn() {
        String user;
        String password;
        String url;
        String driverClassName;

        if (connection == null) {
            try{
                Properties db = loadProperties();
                user = db.getProperty("USER");
                url = db.getProperty("CONNECTION_URL");
                password = db.getProperty("PASS");
                driverClassName = db.getProperty("DRIVER_CLASS_NAME");

                Class.forName(driverClassName);

                connection = DriverManager.getConnection(url,user, password);

            } catch (SQLException | IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private static Properties loadProperties() throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("src/main/logic/com/example/aroundtheworld/connection/db.properties");
        properties.load(fileInputStream);
        return properties;
    }
}
