package com.example.aroundtheworld.dao;

import com.example.aroundtheworld.connection.ConnectionDB;
import com.example.aroundtheworld.dao.queries.CRUDQueries;
import com.example.aroundtheworld.dao.queries.SimpleQueries;
import com.example.aroundtheworld.engineering.Printer;
import com.example.aroundtheworld.exception.DuplicateRequestException;
import com.example.aroundtheworld.exception.NotFoundException;
import com.example.aroundtheworld.model.ResidenceRequest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ResidenceRequestDAO {
    private static final String ID = "idRequest";
    private static final String IDSTUD = "idStudent";
    private static final String IDRES = "idResidence";
    private static final String ARRIVAL = "arrival";
    private static final String DEPARTURE = "departure";
    private static final String ROOMNUMBER = "roomNumber";
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
            Printer.error(e.getMessage());
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
                    int idResidence = resultSet.getInt(IDRES);
                    int idRequest = resultSet.getInt(ID);
                    Date arrival = resultSet.getDate(ARRIVAL);
                    int status = resultSet.getInt(STATUS);
                    Date departure = resultSet.getDate(DEPARTURE);
                    int idStudent = resultSet.getInt(IDSTUD);
                    String room = resultSet.getString(ROOM);
                    int roomNum = resultSet.getInt(ROOMNUMBER);

                    String city = ResidenceDAO.retrieveResidencebyId(idResidence);
                    String studentName = StudentDAO.getNameById(idStudent);

                    residenceRequest = new ResidenceRequest(city, arrival.toString(), departure.toString(), room, idStudent, status);
                    residenceRequest.setIdResidence(idResidence);
                    residenceRequest.setId(idRequest);
                    residenceRequest.setStudentName(studentName);
                    if (roomNum != 0) {
                        residenceRequest.setRoomNumber(roomNum);
                    }
                    residenceRequestList.add(residenceRequest);

                } while (resultSet.next());
            }

            resultSet.close();

        } catch (SQLException e) {
            Printer.error(e.getMessage());
        }

        return residenceRequestList;
    }

    public static void updateRoom(int number, int id, int status) {
        Connection connection;
        try {
            connection = ConnectionDB.getConnection();
            CRUDQueries.updateResidenceRequest(connection, status, id, number);
        } catch (SQLException e) {
            Printer.error(e.getMessage());
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
                    int studentId = resultSet.getInt(IDSTUD);
                    Date arrival = resultSet.getDate(ARRIVAL);
                    Date departure = resultSet.getDate(DEPARTURE);
                    String room = resultSet.getString(ROOM);
                    int status = resultSet.getInt(STATUS);
                    int idResidence = resultSet.getInt(IDRES);
                    int roomNum = resultSet.getInt(ROOMNUMBER);
                    String city = ResidenceDAO.retrieveResidencebyId(idResidence);

                    residenceRequest = new ResidenceRequest(city, arrival.toString(), departure.toString(), room, studentId, status);
                    residenceRequest.setIdResidence(idResidence);
                    residenceRequest.setId(requestId);
                    if (roomNum != 0) {
                        residenceRequest.setRoomNumber(roomNum);
                    }

                    residenceRequestList.add(residenceRequest);

                } while (resultSet.next());
            }

            resultSet.close();

        } catch (SQLException | NotFoundException e) {
            Printer.error(e.getMessage());
        }

        return residenceRequestList;
    }

    public static void updateStatus(int id, int status) {
        Connection connection;
        try {
            connection = ConnectionDB.getConnection();
            CRUDQueries.updateResidenceRequestStatus(connection, status, id);
        } catch (SQLException e) {
            Printer.error(e.getMessage());
        }
    }

    public static void deleteRequest(int id) {
        Connection connection;
        try {
            connection = ConnectionDB.getConnection();
            CRUDQueries.deleteResidenceRequest(connection, id);
        } catch (SQLException e) {
            Printer.error(e.getMessage());
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
                    int idResidence = resultSet.getInt(IDRES);
                    String city = ResidenceDAO.retrieveResidencebyId(idResidence);

                    residenceRequest = new ResidenceRequest(city, arrival.toString(), departure.toString(), null, idStudent, 2);
                    residenceRequest.setIdResidence(idResidence);
                    residenceRequest.setId(requestId);

                    residenceRequestList.add(residenceRequest);

                } while (resultSet.next());
            }

            resultSet.close();

        } catch (SQLException | NotFoundException e) {
            Printer.error(e.getMessage());
        }

        return residenceRequestList;
    }
}
