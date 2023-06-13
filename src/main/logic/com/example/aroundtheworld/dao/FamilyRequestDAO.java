package com.example.aroundtheworld.dao;

import com.example.aroundtheworld.connection.ConnectionDB;
import com.example.aroundtheworld.dao.queries.CRUDQueries;
import com.example.aroundtheworld.dao.queries.SimpleQueries;
import com.example.aroundtheworld.engineering.Printer;
import com.example.aroundtheworld.exception.DuplicateRequestException;
import com.example.aroundtheworld.exception.NotFoundException;
import com.example.aroundtheworld.model.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FamilyRequestDAO {

    private static final String ID = "idRequest";
    private static final String ID_STUDENT = "idStudent";
    private static final String ID_FAMILY = "idFamily";
    private static final String ARRIVAL = "arrival";
    private static final String DEPARTURE = "departure";
    private static final String RATE = "rate";
    private static final String COMPATIBILITY = "compatibility";
    private static final String CITY = "city";
    private static final String STATUS = "status";
    private static final String SIBLINGS = "siblings";
    private static final String ANIMALS = "animals";

    private FamilyRequestDAO() {
    }

    public static void newRequest(FamilyRequest familyRequest) throws DuplicateRequestException {

        Connection connection;

        try {
            connection = ConnectionDB.getConnection();

            ResultSet resultSet = SimpleQueries.selectDistinctRequest(connection, familyRequest.getIdStudent(), familyRequest.getArrival(), familyRequest.getDeparture());

            if (!resultSet.first()) {
                CRUDQueries.insertRequest(connection, familyRequest);
                ResultSet resultSetId = SimpleQueries.getIdFamilyRequest(connection, familyRequest.getIdStudent(), familyRequest.getArrival());

                if (!resultSetId.first()) {
                    throw new NotFoundException("No request found");
                }
                resultSetId.first();

                int idRequest = resultSetId.getInt(ID);
                FamilyPreferencesDAO.addPreferencesRequest(familyRequest.getFamilyPreferences(), idRequest);
            } else {
                throw new DuplicateRequestException();
            }

        } catch (SQLException | NotFoundException e) {
            Printer.printError(e.getMessage());
        }
    }

    public static List<FamilyRequest> retrieveRequests(int id) {
        Connection connection;
        List<FamilyRequest> familyRequestsList = new ArrayList<>();
        FamilyRequest familyRequest;

        try {
            connection = ConnectionDB.getConnection();

            ResultSet resultSet = SimpleQueries.retrieveFamilyRequests(connection, id);

            if (resultSet.first()) {
                resultSet.first();
                do {
                    int requestId = resultSet.getInt(ID);
                    int studentId = resultSet.getInt(ID_STUDENT);
                    String city = resultSet.getString(CITY);
                    Date arrival = resultSet.getDate(ARRIVAL);
                    Date departure = resultSet.getDate(DEPARTURE);
                    int status = resultSet.getInt(STATUS);
                    int siblings = resultSet.getInt(SIBLINGS);
                    int animals = resultSet.getInt(ANIMALS);
                    String compatibility = resultSet.getString(COMPATIBILITY);


                    familyRequest = new FamilyRequest(city, arrival.toString(), departure.toString(), siblings, animals, studentId, status);

                    familyRequest.setCompatibility(Float.parseFloat(compatibility));
                    familyRequest.setIdFamily(id);
                    familyRequest.setId(requestId);

                    FamilyPreferences preferences = FamilyPreferencesDAO.retrievePreferencesRequest(requestId);
                    familyRequest.setFamilyPreferences(preferences);

                    familyRequestsList.add(familyRequest);

                } while (resultSet.next());
            }

            resultSet.close();

        } catch (SQLException e) {
            Printer.printError(e.getMessage());
        }

        return familyRequestsList;
    }

    public static void updateStatus(int status, int id) {
        Connection connection;
        try {
            connection = ConnectionDB.getConnection();
            CRUDQueries.updateStatusRequest(connection, status, id);
        } catch (SQLException e) {
            Printer.printError(e.getMessage());
        }
    }

    public static void deleteRequest(int id) {
        Connection connection;
        try {
            connection = ConnectionDB.getConnection();
            CRUDQueries.deleteRequest(connection, id);
        } catch (SQLException e) {
            Printer.printError(e.getMessage());
        }
    }

    public static List<FamilyRequest> retrieveStudentRequest(int idStudent) {
        Connection connection;
        List<FamilyRequest> familyRequestList = new ArrayList<>();
        FamilyRequest familyRequest;

        try {
            connection = ConnectionDB.getConnection();
            ResultSet resultSet = SimpleQueries.retrieveStudentFamilyRequests(connection, idStudent);

            if (resultSet.first()) {
                resultSet.first();
                do {
                    int requestId = resultSet.getInt(ID);
                    Date arrival = resultSet.getDate(ARRIVAL);
                    Date departure = resultSet.getDate(DEPARTURE);
                    int status = resultSet.getInt(STATUS);
                    int idFamily = resultSet.getInt(ID_FAMILY);
                    String city = resultSet.getString(CITY);

                    familyRequest = new FamilyRequest(city, arrival.toString(), departure.toString(), idStudent, idFamily, status);
                    familyRequest.setId(requestId);

                    familyRequestList.add(familyRequest);

                } while (resultSet.next());
            }

            resultSet.close();

        } catch (SQLException e) {
            Printer.printError(e.getMessage());
        }

        return familyRequestList;
    }

    public static List<FamilyRequest> retrieveTravelsByFamily(int idFamily) {
        Connection connection;
        List<FamilyRequest> familyRequestList = new ArrayList<>();
        FamilyRequest familyRequest;

        try {
            connection = ConnectionDB.getConnection();

            ResultSet resultSet = SimpleQueries.retrieveBookingsByFamily(connection, idFamily);

            if (resultSet.first()) {
                resultSet.first();
                do {
                    int requestId = resultSet.getInt(ID);
                    Date arrival = resultSet.getDate(ARRIVAL);
                    Date departure = resultSet.getDate(DEPARTURE);
                    int idStudent = resultSet.getInt(ID_STUDENT);
                    int rate = resultSet.getInt(RATE);

                    familyRequest = new FamilyRequest(null, arrival.toString(), departure.toString(), idStudent, idFamily, 2);
                    familyRequest.setId(requestId);
                    familyRequest.setRate(rate);

                    familyRequestList.add(familyRequest);

                } while (resultSet.next());
            }

            resultSet.close();

        } catch (SQLException e) {
            Printer.printError(e.getMessage());
        }

        return familyRequestList;
    }

    public static List<FamilyRequest> retrieveTravelsByStudent(int idStudent) {
        Connection connection;
        List<FamilyRequest> familyRequestList = new ArrayList<>();
        FamilyRequest familyRequest;

        try {
            connection = ConnectionDB.getConnection();

            ResultSet resultSet = SimpleQueries.retrieveFamilyBookingsByStudent(connection, idStudent);

            if (resultSet.first()) {
                resultSet.first();
                do {
                    int requestId = resultSet.getInt(ID);
                    Date arrival = resultSet.getDate(ARRIVAL);
                    Date departure = resultSet.getDate(DEPARTURE);
                    int idFamily = resultSet.getInt(ID_FAMILY);
                    int rate = resultSet.getInt(RATE);
                    String city = resultSet.getString(CITY);

                    familyRequest = new FamilyRequest(city, arrival.toString(), departure.toString(), idStudent, idFamily, 2);
                    familyRequest.setId(requestId);
                    familyRequest.setRate(rate);

                    familyRequestList.add(familyRequest);

                } while (resultSet.next());
            }

            resultSet.close();

        } catch (SQLException e) {
            Printer.printError(e.getMessage());
        }

        return familyRequestList;
    }

    public static void updateRate(int rate, int idRequest) {
        Connection connection;
        try {
            connection = ConnectionDB.getConnection();
            CRUDQueries.updateRateRequest(connection, rate, idRequest);
        } catch (SQLException e) {
            Printer.printError(e.getMessage());
        }
    }
}
