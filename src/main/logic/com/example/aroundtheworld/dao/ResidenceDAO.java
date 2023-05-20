package com.example.aroundtheworld.dao;

import com.example.aroundtheworld.connection.ConnectionDB;
import com.example.aroundtheworld.dao.queries.SimpleQueries;
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
            System.out.println(e.getMessage());
        }
        return residence;
    }

    public static int getIdResidence(String city) throws NotFoundException{
        Connection connection;
        int idResidence = 0;


        try{
            connection = ConnectionDB.getConnection();

            ResultSet resultSet = SimpleQueries.retrieveIdResidence(connection, city);

            if(!resultSet.first()) {
                throw new NotFoundException("No residence found in city with name: " + city);
            }

            resultSet.first();
            do{
                idResidence = resultSet.getInt(ID);

            } while(resultSet.next());

            resultSet.close();

        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return idResidence;
    }

    public static String retrieveResidencebyId(int idResidence) throws NotFoundException {

        Connection connection;
        String city = null;


        try{
            connection = ConnectionDB.getConnection();

            ResultSet resultSet = SimpleQueries.retrieveResidencebyId(connection, idResidence);

            if(!resultSet.first()) {
                throw new NotFoundException("No residence found with id: " + idResidence);
            }

            resultSet.first();
            do{
                city = resultSet.getString(CITY);

            } while(resultSet.next());

            resultSet.close();

        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return city;
    }
}

