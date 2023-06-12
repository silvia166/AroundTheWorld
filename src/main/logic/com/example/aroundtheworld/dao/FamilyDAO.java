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
    private static final String EMAIL = "email";
    private static final String CSV_FILE_NAME = "src/main/res/Users.csv";

    private FamilyDAO() {}

    public static Family retrieveFamilyById(int idFamily) throws NotFoundException {
        Connection connection;
        Family family = null;

        try {
            connection = ConnectionDB.getConnection();

            ResultSet resultSet = SimpleQueries.retrieveFamilyById(connection, idFamily);

            if (!resultSet.first()) {
                throw new NotFoundException("No family found with id: " + idFamily);
            }

            resultSet.first();
            do {

               family = setFamilyData(resultSet);

            } while (resultSet.next());

            resultSet.close();

        } catch (SQLException e) {
            Printer.printError(e.getMessage());
        }

        return family;
    }

    private static Family setFamilyData(ResultSet resultSet) throws NotFoundException, SQLException {
        int familyId = resultSet.getInt(ID);
        String phoneNumber = resultSet.getString(PHONE);
        String name = resultSet.getString(NAME);
        String city = resultSet.getString(CITY);
        String address = resultSet.getString(ADDRESS);
        String photo = resultSet.getString(PHOTO);
        String email = resultSet.getString(EMAIL);

        List<FamilyMember> familyMembers = FamilyMemberDAO.retrieveMembers(familyId);
        List<Animal> animals = AnimalDAO.retrieveAnimals(familyId);
        FamilyPreferences preferences = FamilyPreferencesDAO.retrievePreferences(familyId);

        Family family = new Family(familyId, phoneNumber, name, city, address, email);
        family.setAnimals(animals);
        family.setMembers(familyMembers);
        family.setPreferences(preferences);
        family.setImgSrc(photo);

        return family;
    }

    public static Family retrieveFamilyByUsername(String username) throws NotFoundException {
        Connection connection;
        Family family = null;
        ResultSet resultSet;

        try {
            connection = ConnectionDB.getConnection();

            resultSet = SimpleQueries.retrieveFamilyByUsername(connection, username);

            if (!resultSet.first()) {
                throw new NotFoundException("No family found with username: " + username);
            }

            resultSet.first();
            do {
                family = setFamilyData(resultSet);
            } while (resultSet.next());

            resultSet.close();

        } catch (SQLException e) {
            Printer.printError(e.getMessage());
        }

        return family;
    }

    public static void addFamily(Family family) {
        Connection connection;

        try {
            connection = ConnectionDB.getConnection();

            CRUDQueries.insertUser(connection, family.getEmail(), "123", "family");
            CRUDQueries.insertFamily(connection, family.getName(), family.getPhoneNumber(), family.getCity(), family.getAddress(), family.getEmail(), family.getImgSrc());

            ResultSet resultSet = SimpleQueries.retrieveFamilyID(connection, family.getEmail());

            if(!resultSet.first()){
                throw new NotFoundException("No family found");
            }
            resultSet.first();

            int id = resultSet.getInt(ID);

            for(Animal animal : family.getAnimals()){
                AnimalDAO.addAnimal(animal, id);
            }

            for(FamilyMember familyMember : family.getMembers()){
                FamilyMemberDAO.addMember(familyMember, id);
            }

            FamilyPreferencesDAO.addPreferences(family.getPreferences(), id);

        } catch (SQLException | NotFoundException e) {
            Printer.printError(e.getMessage());
        }


        String user = family.getEmail();
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

    public static Family retrieveFamilyID(String email) {
        Connection connection;
        Family family = null;

        try {
            connection = ConnectionDB.getConnection();

            ResultSet resultSet = SimpleQueries.retrieveFamilyID(connection, email);

            if (!resultSet.first()) {
                throw new NotFoundException("No family found with email" + email);
            }

            resultSet.first();

            int id = resultSet.getInt(ID);
            family = new Family();
            family.setId(id);

            resultSet.close();


        } catch (SQLException | NotFoundException e) {
            Printer.printError(e.getMessage());
        }

        return family;
    }

    public static List<Family> retrieveFamilies(String city) {
        Connection connection;
        Family family;
        List<Family> familyList = new ArrayList<>();

        try {
            connection = ConnectionDB.getConnection();

            ResultSet resultSet = SimpleQueries.retrieveFamiliesByCity(connection, city);

            if (!resultSet.first()) {
                throw new NotFoundException("No family found in the city" + city);
            }

            resultSet.first();

            do {

                family = setFamilyData(resultSet);
                familyList.add(family);

            } while (resultSet.next());

            resultSet.close();

        } catch (SQLException | NotFoundException e) {
            Printer.printError(e.getMessage());
        }
        return familyList;
    }


    public static Family retrieveFamilyInfo(int idFamily) {
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
            family = new Family();
            family.setImgSrc(photo);
            family.setName(name);

            resultSet.close();


        } catch (SQLException | NotFoundException e) {
            Printer.printError(e.getMessage());
        }

        return family;
    }
}

