package com.example.aroundtheworld.dao;

import com.example.aroundtheworld.connection.ConnectionDB;
import com.example.aroundtheworld.dao.queries.SimpleQueries;
import com.example.aroundtheworld.engineering.ShowExceptionSupport;
import com.example.aroundtheworld.exception.ConnectionDbException;
import com.example.aroundtheworld.exception.MessageException;
import com.example.aroundtheworld.exception.NoAvailableRoomsException;
import com.example.aroundtheworld.exception.NotFoundException;
import com.example.aroundtheworld.model.*;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {
    private static final String NUMBER = "number";
    private static final String TYPE = "type";

    private RoomDAO(){}

    public static List<Room> retrieveAvailableRooms(int idResidence, String arrival, String departure) throws IOException {
        Statement stmt;
        Room room;
        List<Room> roomList = new ArrayList<>();

        try {
            stmt = ConnectionDB.getConnection();

            ResultSet resultSet = SimpleQueries.retrieveRooms(stmt, idResidence, arrival, departure);

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


        } catch (NoAvailableRoomsException e) {
            ShowExceptionSupport.showException(e.getMessage());
        } catch (SQLException | ConnectionDbException e) {
            e.printStackTrace();
        }
            return roomList;
    }
}
