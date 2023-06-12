package com.example.aroundtheworld.dao;

import com.example.aroundtheworld.connection.ConnectionDB;
import com.example.aroundtheworld.dao.queries.SimpleQueries;
import com.example.aroundtheworld.engineering.Printer;
import com.example.aroundtheworld.exception.NotFoundException;
import com.example.aroundtheworld.model.Residence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResidenceDAO {

    private static final String ID = "idResidence";
    private static final String NAME = "name";
    private static final String ADDRESS = "address";
    private static final String DISTANCE = "distanceSchool";
    private static final String CITY = "city";

    private ResidenceDAO(){}
    public static Residence retrieveResidence(String city) throws NotFoundException {
        Connection connection;
        Residence residence = null;

        try{
            connection = ConnectionDB.getConnection();

            ResultSet resultSet = SimpleQueries.retrieveResidence(connection, city);

            if(!resultSet.first()) {
                throw new NotFoundException("No residence found in city with name: " + city);
            }

            resultSet.first();
            do{
                int id = resultSet.getInt(ID);
                String name = resultSet.getString(NAME);
                String address = resultSet.getString(ADDRESS);
                String distance = resultSet.getString(DISTANCE);
                city = resultSet.getString(CITY);

                residence = new Residence(id,name,address,distance,city);

            } while(resultSet.next());

            resultSet.close();

        } catch(SQLException e){
            Printer.printError(e.getMessage());
        }
        return residence;
    }

    public static Residence retrieveResidenceById(int idResidence) throws NotFoundException {

        Connection connection;
        Residence residence = null;

        try{
            connection = ConnectionDB.getConnection();

            ResultSet resultSet = SimpleQueries.retrieveResidenceById(connection, idResidence);

            if(!resultSet.first()) {
                throw new NotFoundException("No residence found with id: " + idResidence);
            }

            resultSet.first();

            String name = resultSet.getString(NAME);
            String city = resultSet.getString(CITY);
            residence = new Residence(idResidence, name, city);

            resultSet.close();

        } catch(SQLException e){
            Printer.printError(e.getMessage());
        }
        return residence;
    }
}

