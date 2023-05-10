package com.example.aroundtheworld.dao;

import com.example.aroundtheworld.connection.ConnectionDB;
import com.example.aroundtheworld.dao.queries.CRUDQueries;
import com.example.aroundtheworld.exception.ConnectionDbException;
import com.example.aroundtheworld.model.FamilyRequest;
import com.example.aroundtheworld.model.ResidenceRequest;

import java.sql.SQLException;
import java.sql.Statement;

public class ResidenceRequestDAO {

    public static void newRequest(ResidenceRequest residenceRequest) {

        Statement stmt;

        try {
            stmt = ConnectionDB.getConnection();

            CRUDQueries.insertResidenceRequest(stmt, residenceRequest);

        } catch (SQLException | ConnectionDbException e) {
            e.printStackTrace();
        }
    }

}
