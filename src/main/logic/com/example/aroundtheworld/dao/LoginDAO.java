package com.example.aroundtheworld.dao;

import com.example.aroundtheworld.connection.ConnectionDB;
import com.example.aroundtheworld.exception.ConnectionDbException;
import com.example.aroundtheworld.exception.NotFoundException;
import com.example.aroundtheworld.dao.queries.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginDAO {

    private LoginDAO() {}

    public static int checkUser(String username, String password) {
        Statement stmt;
        int role = 0;

        try {
            stmt = ConnectionDB.getConnection();

            ResultSet resultSet = SimpleQueries.checkUser(stmt,username,password);

            if (!resultSet.first()){
                throw new NotFoundException("No user found with "+ username);
            }

            resultSet.first();

            String nameRole = resultSet.getString(1);
            switch (nameRole){
                case "student" -> {role = 1; break;}
                case "family" -> {role = 2; break;}
                case "agency" -> {role = 3; break;}
                default -> throw new NotFoundException("No role found");
            }

            resultSet.close();

        } catch (NotFoundException | SQLException | ConnectionDbException e){
            e.printStackTrace();
        }

        return role;
    }
}
