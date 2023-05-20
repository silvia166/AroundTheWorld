package com.example.aroundtheworld.dao;

import com.example.aroundtheworld.connection.ConnectionDB;
import com.example.aroundtheworld.dao.queries.CRUDQueries;
import com.example.aroundtheworld.dao.queries.SimpleQueries;
import com.example.aroundtheworld.engineering.Printer;
import com.example.aroundtheworld.exception.ConnectionDbException;
import com.example.aroundtheworld.exception.PersistenceLayerException;
import com.example.aroundtheworld.model.Animal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnimalDAO {

    private static final String TYPE = "type";
    private static final String QT = "quantity";

    private AnimalDAO(){}
    public static List<Animal> retrieveAnimal(int familyId) {
        Connection connection;
        List<Animal> animals = new ArrayList<>();
        Animal animal;

        try {
            connection = ConnectionDB.getConnection();

            ResultSet resultSet = SimpleQueries.retrieveAnimal(connection, familyId);

            if (!resultSet.first()) {
                return animals;
            }

            resultSet.first();
            do {
                String type = resultSet.getString(TYPE);
                int qt = resultSet.getInt(QT);

                animal = new Animal(type, qt);
                animals.add(animal);

            } while (resultSet.next());

            resultSet.close();

        } catch (SQLException  e) {
            Printer.error(e.getMessage());
        }
        return animals;
    }

    public static void addAnimal(String type, int quantity, int id) {
        Connection connection;

        try {
            connection = ConnectionDB.getConnection();
            CRUDQueries.insertAnimal(connection, id, type, quantity);

        } catch (SQLException e) {
            Printer.error(e.getMessage());
        }

    }
}

