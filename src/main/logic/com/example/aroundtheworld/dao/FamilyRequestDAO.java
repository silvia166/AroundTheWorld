package com.example.aroundtheworld.dao;

import com.example.aroundtheworld.bean.FamilyRequestBean;
import com.example.aroundtheworld.connection.ConnectionDB;
import com.example.aroundtheworld.dao.queries.CRUDQueries;
import com.example.aroundtheworld.exception.ConnectionDbException;
import com.example.aroundtheworld.model.FamilyRequest;

import java.sql.SQLException;
import java.sql.Statement;

public class FamilyRequestDAO {

    private FamilyRequestDAO(){}

    public static void newRequest(FamilyRequest familyRequest) {

        Statement stmt;

        try{
            stmt = ConnectionDB.getConnection();

            CRUDQueries.insertRequest(stmt, familyRequest);

        } catch(SQLException | ConnectionDbException e) {
            e.printStackTrace();
        }

    }
}
