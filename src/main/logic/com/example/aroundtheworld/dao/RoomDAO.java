package com.example.aroundtheworld.dao;

import com.example.aroundtheworld.connection.ConnectionDB;
import com.example.aroundtheworld.dao.queries.SimpleQueries;
import com.example.aroundtheworld.engineering.Printer;
import com.example.aroundtheworld.exception.NoAvailableRoomsException;
import com.example.aroundtheworld.exception.NotFoundException;
import com.example.aroundtheworld.model.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {
    private static final String NUMBER = "number";
    private static final String TYPE = "type";

    private RoomDAO(){}

    public static List<Room> retrieveAvailableRooms(int idResidence, String arrival, String departure) throws NoAvailableRoomsException {
        Connection connection;
        Room room;
        List<Room> roomList = new ArrayList<>();

        try {
            connection = ConnectionDB.getConnection();

            ResultSet resultSet = SimpleQueries.retrieveRooms(connection, idResidence, arrival, departure);

            if (!resultSet.first()) {
                throw new NoAvailableRoomsException();
            }

            resultSet.first();

            do {
                String type = resultSet.getString(TYPE);
                int number = resultSet.getInt(NUMBER);

                room = new Room(idResidence, number, type);
                roomList.add(room);

            } while (resultSet.next());

            resultSet.close();
        } catch (SQLException e) {
            Printer.printError(e.getMessage());
        }
            return roomList;
    }

    public static Room retrieveRoom(int idResidence, int roomNumber){
        Connection connection;
        Room room = null;
        try {
            connection = ConnectionDB.getConnection();

            ResultSet resultSet = SimpleQueries.retrieveRoomType(connection, idResidence, roomNumber);

            if (!resultSet.first()) {
                throw new NotFoundException("No room found");
            }

            resultSet.first();

            String type = resultSet.getString(TYPE);
            room = new Room(idResidence, roomNumber, type);

            resultSet.close();
        } catch (SQLException | NotFoundException e) {
            Printer.printError(e.getMessage());
        }
        return room;

    }
}
