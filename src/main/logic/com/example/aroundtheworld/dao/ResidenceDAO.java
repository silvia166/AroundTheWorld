package com.example.aroundtheworld.dao;

import com.example.aroundtheworld.connection.ConnectionDB;
import com.example.aroundtheworld.dao.queries.SimpleQueries;
import com.example.aroundtheworld.exception.ConnectionDbException;
import com.example.aroundtheworld.exception.NotFoundException;
import com.example.aroundtheworld.model.Residence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ResidenceDAO {

    private static final String ID = "idResidence";
    private static final String NAME = "name";
    private static final String ADDRESS = "address";
    private static final String DISTANCE = "distanceSchool";

    private ResidenceDAO(){}
    public static Residence retrieveResidence(String city) throws NotFoundException {
        Statement stmt;
        Residence residence = null;


        try{
            stmt = ConnectionDB.getConnection();

            ResultSet resultSet = SimpleQueries.retrieveResidence(stmt, city);

            if(!resultSet.first()) {
                throw new NotFoundException("No residence found in city with name: " + city);
            }

            resultSet.first();
            do{
                int id = resultSet.getInt(ID);
                String name = resultSet.getString(NAME);
                String address = resultSet.getString(ADDRESS);
                String distance = resultSet.getString(DISTANCE);

                residence = new Residence(id,name,address,distance);

            } while(resultSet.next());

            resultSet.close();

        } catch(SQLException | ConnectionDbException e){
            e.printStackTrace();
        }
        return residence;
    }
}

