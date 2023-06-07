package com.example.aroundtheworld.dao;

import com.example.aroundtheworld.connection.ConnectionDB;
import com.example.aroundtheworld.dao.queries.CRUDQueries;
import com.example.aroundtheworld.dao.queries.SimpleQueries;
import com.example.aroundtheworld.engineering.Printer;
import com.example.aroundtheworld.exception.DuplicateRequestException;
import com.example.aroundtheworld.exception.NotFoundException;
import com.example.aroundtheworld.model.Residence;
import com.example.aroundtheworld.model.ResidenceRequest;
import com.example.aroundtheworld.model.Room;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ResidenceRequestDAO {
    private static final String ID = "idRequest";
    private static final String ID_STUDENT = "idStudent";
    private static final String ID_RESIDENCE = "idResidence";
    private static final String ARRIVAL = "arrival";
    private static final String DEPARTURE = "departure";
    private static final String ROOM_NUMBER = "roomNumber";
    private static final String ROOM = "room";
    private static final String STATUS = "status";

    private ResidenceRequestDAO() {
    }

    public static void newRequest(ResidenceRequest residenceRequest) throws  DuplicateRequestException{

        Connection connection;

        try {
            connection = ConnectionDB.getConnection();

            ResultSet resultSet = SimpleQueries.selectDistinctRequest(connection, residenceRequest.getIdStudent(), residenceRequest.getArrival(), residenceRequest.getDeparture());
            if(!resultSet.first()){
                CRUDQueries.insertResidenceRequest(connection, residenceRequest);
            } else {
                throw new DuplicateRequestException();
            }

        } catch (SQLException e) {
            Printer.printError(e.getMessage());
        }
    }

    public static List<ResidenceRequest> retrieveRequests() throws NotFoundException {
        Connection connection;
        List<ResidenceRequest> residenceRequestList = new ArrayList<>();
        ResidenceRequest residenceRequest;

        try {
            connection = ConnectionDB.getConnection();

            ResultSet resultSet = SimpleQueries.retrieveResidenceRequests(connection);

            if (resultSet.first()) {
                resultSet.first();
                do {
                    int idResidence = resultSet.getInt(ID_RESIDENCE);
                    int idRequest = resultSet.getInt(ID);
                    Date arrival = resultSet.getDate(ARRIVAL);
                    int status = resultSet.getInt(STATUS);
                    Date departure = resultSet.getDate(DEPARTURE);
                    int idStudent = resultSet.getInt(ID_STUDENT);
                    String requestedRoom = resultSet.getString(ROOM);
                    int roomNum = resultSet.getInt(ROOM_NUMBER);

                    Residence residence = ResidenceDAO.retrieveResidenceById(idResidence);

                    residenceRequest = new ResidenceRequest(residence.getCity(), arrival.toString(), departure.toString(), requestedRoom, idStudent, status);
                    residenceRequest.setIdResidence(idResidence);
                    residenceRequest.setId(idRequest);
                    if (roomNum != 0) {
                        Room room = RoomDAO.retrieveRoom(idResidence,roomNum);
                        residenceRequest.setReservedRoom(room);
                    }
                    residenceRequestList.add(residenceRequest);

                } while (resultSet.next());
            }

            resultSet.close();

        } catch (SQLException e) {
            Printer.printError(e.getMessage());
        }

        return residenceRequestList;
    }

    public static void updateRoom(int number, int id, int status) {
        Connection connection;
        try {
            connection = ConnectionDB.getConnection();
            CRUDQueries.updateResidenceRequest(connection, status, id, number);
        } catch (SQLException e) {
            Printer.printError(e.getMessage());
        }
    }

    public static List<ResidenceRequest> retrieveStudentResidenceRequest(int id) {
        Connection connection;
        List<ResidenceRequest> residenceRequestList = new ArrayList<>();
        ResidenceRequest residenceRequest;

        try {

            connection = ConnectionDB.getConnection();

            ResultSet resultSet = SimpleQueries.retrieveStudentResidenceRequests(connection, id);

            if(resultSet.first()) {
                resultSet.first();
                do {
                    int requestId = resultSet.getInt(ID);
                    int studentId = resultSet.getInt(ID_STUDENT);
                    Date arrival = resultSet.getDate(ARRIVAL);
                    Date departure = resultSet.getDate(DEPARTURE);
                    String requestedRoom = resultSet.getString(ROOM);
                    int status = resultSet.getInt(STATUS);
                    int idResidence = resultSet.getInt(ID_RESIDENCE);
                    int roomNum = resultSet.getInt(ROOM_NUMBER);
                    Residence residence = ResidenceDAO.retrieveResidenceById(idResidence);

                    residenceRequest = new ResidenceRequest(residence.getCity(), arrival.toString(), departure.toString(), requestedRoom, studentId, status);
                    residenceRequest.setIdResidence(idResidence);
                    residenceRequest.setId(requestId);
                    if (roomNum != 0) {
                        Room room = RoomDAO.retrieveRoom(idResidence,roomNum);
                        residenceRequest.setReservedRoom(room);
                    }

                    residenceRequestList.add(residenceRequest);

                } while (resultSet.next());
            }

            resultSet.close();

        } catch (SQLException | NotFoundException e) {
            Printer.printError(e.getMessage());
        }

        return residenceRequestList;
    }

    public static void updateStatus(int id, int status) {
        Connection connection;
        try {
            connection = ConnectionDB.getConnection();
            CRUDQueries.updateResidenceRequestStatus(connection, status, id);
        } catch (SQLException e) {
            Printer.printError(e.getMessage());
        }
    }

    public static void deleteRequest(int id) {
        Connection connection;
        try {
            connection = ConnectionDB.getConnection();
            CRUDQueries.deleteResidenceRequest(connection, id);
        } catch (SQLException e) {
            Printer.printError(e.getMessage());
        }
    }

    public static List<ResidenceRequest> retrieveTravelsByStudent(int idStudent) {
        Connection connection;
        List<ResidenceRequest> residenceRequestList = new ArrayList<>();
        ResidenceRequest residenceRequest;

        try {

            connection = ConnectionDB.getConnection();

            ResultSet resultSet = SimpleQueries.retrieveResidenceBookingsByStudent(connection, idStudent);

            if(resultSet.first()) {
                resultSet.first();
                do {
                    int requestId = resultSet.getInt(ID);
                    Date arrival = resultSet.getDate(ARRIVAL);
                    Date departure = resultSet.getDate(DEPARTURE);
                    int idResidence = resultSet.getInt(ID_RESIDENCE);
                    Residence residence = ResidenceDAO.retrieveResidenceById(idResidence);

                    residenceRequest = new ResidenceRequest(residence.getCity(), arrival.toString(), departure.toString(), null, idStudent, 2);
                    residenceRequest.setIdResidence(idResidence);
                    residenceRequest.setId(requestId);

                    residenceRequestList.add(residenceRequest);

                } while (resultSet.next());
            }

            resultSet.close();

        } catch (SQLException | NotFoundException e) {
            Printer.printError(e.getMessage());
        }

        return residenceRequestList;
    }
}
