package com.example.aroundtheworld.dao;

import com.example.aroundtheworld.connection.ConnectionDB;
import com.example.aroundtheworld.dao.queries.CRUDQueries;
import com.example.aroundtheworld.dao.queries.SimpleQueries;
import com.example.aroundtheworld.exception.ConnectionDbException;
import com.example.aroundtheworld.exception.NotFoundException;
import com.example.aroundtheworld.model.Animal;
import com.example.aroundtheworld.model.FamilyMember;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.lang.Integer.parseInt;

public class AnimalDAO {

    private static final String TYPE = "type";
    private static final String QT = "quantity";

    private AnimalDAO(){}
    public static List<Animal> retrieveAnimal(int familyId) throws NotFoundException{
            Statement stmt;
            List<Animal> animals = new ArrayList<>();
            Animal animal = null;

            try{
                stmt = ConnectionDB.getConnection();

                ResultSet resultSet = SimpleQueries.retrieveAnimal(stmt, familyId);

                if(!resultSet.first()) {
                    throw new NotFoundException("No animal found in family with id: " + familyId);
                }

                resultSet.first();
                do{
                    String type = resultSet.getString(TYPE);
                    int qt = resultSet.getInt(QT);

                    animal = new Animal(type,qt);
                    animals.add(animal);

                } while(resultSet.next());

                resultSet.close();

            } catch(SQLException | ConnectionDbException e){
                e.printStackTrace();
            }
            return animals;
        }

    public static void addAnimal(List<Animal> animals, int id) {

        Statement stmt;

        try{
            stmt = ConnectionDB.getConnection();

            Iterator<Animal> iterator = animals.iterator();

            while(iterator.hasNext()){

                Animal animal = iterator.next();
                CRUDQueries.insertAnimal(stmt, id, animal.getType(), animal.getQuantity());
            }

        } catch(SQLException | ConnectionDbException e) {
            e.printStackTrace();
        }

    }
}

