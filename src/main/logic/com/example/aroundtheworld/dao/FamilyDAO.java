package com.example.aroundtheworld.dao;

import com.example.aroundtheworld.connection.ConnectionDB;
import com.example.aroundtheworld.dao.queries.CRUDQueries;
import com.example.aroundtheworld.dao.queries.SimpleQueries;
import com.example.aroundtheworld.engineering.Printer;
import com.example.aroundtheworld.exception.NotFoundException;
import com.example.aroundtheworld.model.Animal;
import com.example.aroundtheworld.model.Family;
import com.example.aroundtheworld.model.FamilyMember;
import com.example.aroundtheworld.model.FamilyPreferences;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FamilyDAO {

    private static final String ID = "idFamily";
    private static final String NAME = "name";
    private static final String CITY = "city";
    private static final String ADDRESS = "address";
    private static final String PHONE = "phoneNumber";
    private static final String PHOTO = "photo";
    private static final String CSV_FILE_NAME = "src/main/res/Users.csv";

    private FamilyDAO() {}

    public static Family retrieveFamily(String username, int idFamily) throws NotFoundException {
        Connection connection;
        Family family = null;
        List<FamilyMember> familyMembers;
        List<Animal> animals;
        FamilyPreferences preferences;
        ResultSet resultSet = null;

        try {
            connection = ConnectionDB.getConnection();

            if(username == null){
                resultSet = SimpleQueries.retrieveFamilyById(connection, idFamily);
            }else{
                resultSet = SimpleQueries.retrieveFamilyByUsername(connection, username);
            }

            if (!resultSet.first()) {
                throw new NotFoundException("No family found with username: " + username);
            }

            resultSet.first();
            do {
                int familyId = resultSet.getInt(ID);
                String phoneNumber = resultSet.getString(PHONE);
                String name = resultSet.getString(NAME);
                String city = resultSet.getString(CITY);
                String address = resultSet.getString(ADDRESS);
                String photo = resultSet.getString(PHOTO);

                familyMembers = FamilyMemberDAO.retrieveMembers(familyId);
                animals = AnimalDAO.retrieveAnimal(familyId);
                preferences = FamilyPreferencesDAO.retrievePreferences(familyId);

                family = new Family(familyId, phoneNumber, name, city, address, username);
                family.setAnimals(animals);
                family.setMembers(familyMembers);
                family.setPreferences(preferences);
                family.setImgSrc(photo);

            } while (resultSet.next());

            resultSet.close();

        } catch (SQLException e) {
            Printer.printError(e.getMessage());
        }

        return family;
    }

    public static void addFamily(String name, String phone, String city, String address, String imgSrc, String email) {
        Connection connection;

        try {
            connection = ConnectionDB.getConnection();

            CRUDQueries.insertUser(connection, email, "123", "family");
            CRUDQueries.insertFamily(connection, name, phone, city, address, email, imgSrc);

        } catch (SQLException e) {
            Printer.printError(e.getMessage());
        }


        String user = email;
        user = user.concat(",123,family");

        File file = new File(CSV_FILE_NAME);

        try {
            PrintWriter output = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
            output.println(user);
            output.close();
        } catch (IOException e) {
            Printer.printError(e.getMessage());
        }

    }

    public static int retrieveFamilyID(String name) {
        Connection connection;
        int id = 0;

        try {
            connection = ConnectionDB.getConnection();

            ResultSet resultSet = SimpleQueries.retrieveFamilyID(connection, name);

            if (!resultSet.first()) {
                throw new NotFoundException("No family found with name" + name);
            }

            resultSet.first();

            id = resultSet.getInt(1);

            resultSet.close();


        } catch (SQLException | NotFoundException e) {
            Printer.printError(e.getMessage());
        }

        return id;
    }

    public static List<Family> retrieveFamilies(String city) {
        Connection connection;
        Family family;
        List<FamilyMember> familyMembers;
        List<Animal> animals;
        FamilyPreferences preferences;
        List<Family> familyList = new ArrayList<>();

        try {
            connection = ConnectionDB.getConnection();

            ResultSet resultSet = SimpleQueries.retrieveFamiliesByCity(connection, city);

            if (!resultSet.first()) {
                throw new NotFoundException("No family found in the city" + city);
            }

            resultSet.first();

            do {
                int familyId = resultSet.getInt(ID);
                String phoneNumber = resultSet.getString(PHONE);
                String name = resultSet.getString(NAME);
                String address = resultSet.getString(ADDRESS);
                String photo = resultSet.getString(PHOTO);

                familyMembers = FamilyMemberDAO.retrieveMembers(familyId);
                animals = AnimalDAO.retrieveAnimal(familyId);
                preferences = FamilyPreferencesDAO.retrievePreferences(familyId);

                family = new Family(familyId, phoneNumber, name, city, address);
                family.setAnimals(animals);
                family.setMembers(familyMembers);
                family.setPreferences(preferences);
                family.setImgSrc(photo);

                familyList.add(family);

            } while (resultSet.next());

            resultSet.close();

        } catch (SQLException | NotFoundException e) {
            Printer.printError(e.getMessage());
        }
        return familyList;
    }


    public static Family retrieveFamilyName(int idFamily) {
        Connection connection;
        String name;
        String photo;
        Family family = null;

        try {
            connection = ConnectionDB.getConnection();

            ResultSet resultSet = SimpleQueries.retrieveFamilyName(connection, idFamily);

            if (!resultSet.first()) {
                throw new NotFoundException("No family found with id" + idFamily);
            }

            resultSet.first();

            name = resultSet.getString(NAME);
            photo = resultSet.getString(PHOTO);
            family = new Family(name, photo);

            resultSet.close();


        } catch (SQLException | NotFoundException e) {
            Printer.printError(e.getMessage());
        }

        return family;
    }
}

