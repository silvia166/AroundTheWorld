package com.example.aroundtheworld.connection;


import com.example.aroundtheworld.engineering.Printer;

import java.io.FileInputStream;
import java.io.IOException;

import java.sql.*;

import java.util.Properties;

public class ConnectionDB {

    private ConnectionDB() {}

    private static Connection connection;

    public static Connection getConnection() {
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
                Printer.error(e.getMessage());
            }
        }
        return connection;
    }

    public static void closeConnection() throws SQLException {
        connection.close();
    }

    private static Properties loadProperties() throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("src/main/logic/com/example/aroundtheworld/connection/db.properties");
        properties.load(fileInputStream);
        return properties;
    }
}
