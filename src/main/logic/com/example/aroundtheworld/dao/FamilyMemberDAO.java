package com.example.aroundtheworld.dao;

import com.example.aroundtheworld.connection.ConnectionDB;
import com.example.aroundtheworld.dao.queries.CRUDQueries;
import com.example.aroundtheworld.dao.queries.SimpleQueries;
import com.example.aroundtheworld.exception.ConnectionDbException;
import com.example.aroundtheworld.exception.NotFoundException;
import com.example.aroundtheworld.model.FamilyMember;

import java.lang.reflect.Member;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.lang.Integer.parseInt;

public class FamilyMemberDAO {

    private static final String BIRTH = "birth";
    private static final String NAME = "name";
    private static final String PARENTHOOD = "parenthood";

    private FamilyMemberDAO(){}
    public static List<FamilyMember> retrieveMembers(int familyId) throws NotFoundException {
        Statement stmt;
        List<FamilyMember> familyMembers = new ArrayList<>();
        FamilyMember familyMember = null;
        int age = 0;

        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();

        try{
            stmt = ConnectionDB.getConnection();

            ResultSet resultSet = SimpleQueries.retrieveFamilyMember(stmt, familyId);

            if(!resultSet.first()) {
                throw new NotFoundException("No member found in family with id: " + familyId);
            }

            resultSet.first();
            do{
                String name = resultSet.getString(NAME);
                String year = resultSet.getString(BIRTH);
                String parenthood = resultSet.getString(PARENTHOOD);
                age = currentYear - parseInt(year);

                familyMember = new FamilyMember(name, age, parenthood);
                familyMembers.add(familyMember);

            } while(resultSet.next());

            resultSet.close();

        }catch(SQLException | ConnectionDbException e){
            e.printStackTrace();
        }
        return familyMembers;
    }

    public static void addMember(String name, int age, String parenthood, int id) {

        Statement stmt;
        String birth;
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();

        try{
            stmt = ConnectionDB.getConnection();

            birth = String.valueOf(currentYear - age);
            CRUDQueries.insertMember(stmt, id, name, birth, parenthood);

        } catch(SQLException | ConnectionDbException e) {
            e.printStackTrace();
        }

    }
}
