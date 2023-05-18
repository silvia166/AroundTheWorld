package com.example.aroundtheworld.dao;

import com.example.aroundtheworld.connection.ConnectionDB;
import com.example.aroundtheworld.dao.queries.SimpleQueries;
import com.example.aroundtheworld.exception.ConnectionDbException;
import com.example.aroundtheworld.exception.NotFoundException;
import com.example.aroundtheworld.model.School;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SchoolDAO {

    private static final String ID = "idSchool";
    private static final String NAME = "name";
    private static final String ADDRESS = "address";
    private static final String HOURS = "hours";
    private static final String COURSES = "courses";

    private SchoolDAO(){}
    public static School retrieveSchool(String city) throws NotFoundException {
        Connection connection;
        School school = null;


        try{
            connection = ConnectionDB.getConnectionP();

            ResultSet resultSet = SimpleQueries.retrieveSchool(connection, city);

            if(!resultSet.first()) {
                throw new NotFoundException("No school found in city with name: " + city);
            }

            resultSet.first();
            do{
                int id = resultSet.getInt(ID);
                String name = resultSet.getString(NAME);
                String address = resultSet.getString(ADDRESS);
                String hours = resultSet.getString(HOURS);
                String courses = resultSet.getString(COURSES);

                school = new School(id,name,address,hours,courses);

            } while(resultSet.next());

            resultSet.close();

        } catch(SQLException | ConnectionDbException e){
            e.printStackTrace();
        }
        return school;
    }

}

