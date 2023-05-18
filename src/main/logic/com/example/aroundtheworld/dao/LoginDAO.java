package com.example.aroundtheworld.dao;

import com.example.aroundtheworld.connection.ConnectionDB;
import com.example.aroundtheworld.exception.ConnectionDbException;
import com.example.aroundtheworld.exception.NotFoundException;
import com.example.aroundtheworld.dao.queries.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginDAO {
    private LoginDAO() {}
    public static int checkUser(String username, String password) {
        Connection connection;
        int role = 0;

        try {

            connection = ConnectionDB.getConnectionP();
            ResultSet resultSet = SimpleQueries.checkUser(connection, username,password);

            if (!resultSet.isBeforeFirst()){
                throw new NotFoundException("No user found with "+ username);
            }

            resultSet.first();

            String nameRole = resultSet.getString(1);
            switch (nameRole){
                case "student"-> role = 1;
                case "family" -> role = 2;
                case "agency" -> role = 3;
                default -> throw new NotFoundException("No role found");
            }

            resultSet.close();

        } catch (NotFoundException | SQLException | ConnectionDbException e){
            e.printStackTrace();
        }

        return role;
    }
}
